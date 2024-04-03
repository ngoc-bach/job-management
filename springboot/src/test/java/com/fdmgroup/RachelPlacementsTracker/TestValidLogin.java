package com.fdmgroup.RachelPlacementsTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fdmgroup.RachelPlacementsTracker.util.DriverUtilities;

public class TestValidLogin {
	private DriverUtilities driverUtilities;
	private WebDriver driver;
	
	@BeforeEach
	void setUp() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
		driver.manage().window().maximize(); // maximize the window
		driver.get("http://localhost:5173/"); // load the page
	}
	
	@Test
	void testValidLogin() {
		navigateToLoginPage();
		login("rachel.bach", "rb123");
		assertHomePageDisplayed();
	}
	
	@AfterEach
	void destroy() {
            driver.quit();
	}
	
	private void navigateToLoginPage() {
		WebElement signupButton = driver.findElement(By.id("signup_button")); //1. By Id
		signupButton.click();
		WebElement signinLink = driver.findElement(By.partialLinkText("Login")); //4. By partial linktext
		signinLink.click();
		assertEquals("Login", driver.findElement(By.tagName("h1")).getText());
	}

	private void login(String username, String password) {
		driver.findElement(By.name("username")).sendKeys(username); //5. By name
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("login_button")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	
	private void assertHomePageDisplayed() {
		String actualHomepageLabel = driver.findElement(By.id("dash_board")).getText();
		assertEquals("Dash Board", actualHomepageLabel);
	}
	
}
