package com.epam.yuri_karpov.selenium.service;

import org.openqa.selenium.WebDriver;

import com.epam.yuri_karpov.selenium.ui.webdriver.DriverManager;

public class BaseService {
	protected WebDriver driver;

	public BaseService() {
		if (driver == null) {
			driver = DriverManager.getDriver();
		}
	}

}
