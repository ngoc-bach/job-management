package com.fdmgroup.RachelPlacementsTracker.util;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtilities {
	private static DriverUtilities driverUtilities;
	private WebDriver driver;

	private DriverUtilities() {
		super();
	}
	
	public static DriverUtilities getInstance() {
		if (driverUtilities == null) {
			driverUtilities = new DriverUtilities();
		}
		return driverUtilities;
	}
	
	public WebDriver getDriver() {
		if(driver == null) {
			createDriver();
		}
		return driver;
	}

	private void createDriver() {
		String driverName = getDriverName();
		switch(driverName) {
		case "google chrome":
			System.setProperty("chromedriver", "src/test/resources/chromedriver");
			this.driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("geckodriver", "src/test/resources/geckodriver");
			this.driver = new FirefoxDriver();
			break;
			
		default:
			System.out.println("Browser naem is invalid!");
			break;
		}

	}

	private String getDriverName() {
		Properties config = new Properties();
		String driverName = "";
		try {
			config.load(new FileReader("src/test/resources/config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String key : config.stringPropertyNames()) {
			if(key.equals("browser")) {
				driverName = config.getProperty(key);
			}
		}
		return driverName ;
	}
}
