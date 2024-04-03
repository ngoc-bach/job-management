package com.fdmgroup.RachelPlacementsTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.fdmgroup.RachelPlacementsTracker.util.DriverUtilities;

public class TestLoadLandingPageWhenNotLoggedIn {
	private DriverUtilities driverUtilities;
	private WebDriver driver;
	
	@BeforeEach
	void setUp() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
		driver.manage().window().maximize(); // maximize the window
		driver.get("http://localhost:5173/"); // 1. load a page
	}
	
	@Test
	void testLoadLandingPageWhenNotLoggedIn() {
		System.out.println("URL is: " + driver.getCurrentUrl());
		assertEquals("http://localhost:5173/landing", driver.getCurrentUrl());
	}
	
	@AfterEach
	void destroy() {
		driver.quit();
	}
}
