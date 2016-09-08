package com.epam.yuri_karpov.selenium.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.epam.yuri_karpov.selenium.service.MailService;
import com.epam.yuri_karpov.selenium.ui.webdriver.Driver;
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

	@BeforeTest
	public WebDriver startBrowser() {
		LOG.warn("start 'startBrowser'");

		mailService = new MailService(driver);

		LOG.warn("startBrowser 'delete old screenshots'");
		ScreenShot.deleteAllScreenShots();
		driver = Driver.getWebDriverInstance();
		LOG.warn("finish 'startBrowser'");
		return driver;
	}

	@AfterTest
	public void closeBrowser() {
		LOG.warn("start 'closeBrowser'");

		mailService = new MailService(driver);

		mailService.logOut();
		driver.quit();
		LOG.warn("finish 'closeBrowser'");
	}

	public WebDriver getDriver() {
		return this.driver;
	}
}
