package com.epam.yuri_karpov.selenium.ui.webdriver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver newDriver) {
		driver.set(newDriver);
	}

	public static void removeDriver() 	{
		driver.get().quit();
		driver.remove();
	}

}
