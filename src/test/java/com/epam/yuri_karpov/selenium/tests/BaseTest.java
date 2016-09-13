package com.epam.yuri_karpov.selenium.tests;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.epam.yuri_karpov.selenium.service.MailService;
import com.epam.yuri_karpov.selenium.ui.webdriver.DriverFactory;
import com.epam.yuri_karpov.selenium.ui.webdriver.DriverManager;
import com.epam.yuri_karpov.selenium.utils.ScreenShot;

/**
 * Class contains methods Before and After tests
 *
 * @author Yuri Karpov
 */
public class BaseTest {
	private static final Logger LOG = Logger.getLogger(BaseTest.class);
	private MailService mailService;
	protected WebDriver driver;
	private ResourceBundle resource = ResourceBundle.getBundle("config");

	@BeforeTest
	public void startBrowser() {
		LOG.warn("start 'startBrowser'");

		WebDriver driver = DriverFactory.createInstance();
		DriverManager.setDriver(driver);

		LOG.warn("startBrowser 'delete old screenshots'");
		ScreenShot.deleteAllScreenShots();

		driver.get(resource.getString("mainURL"));

		LOG.warn("finish 'startBrowser'");
	}

	@AfterTest
	public void closeBrowser() {
		LOG.warn("start 'closeBrowser'");

		mailService = new MailService();

		mailService.logOut();
		DriverManager.removeDriver();
		LOG.warn("finish 'closeBrowser'");
	}

	public WebDriver getDriver() {
		return this.driver;
	}
}
