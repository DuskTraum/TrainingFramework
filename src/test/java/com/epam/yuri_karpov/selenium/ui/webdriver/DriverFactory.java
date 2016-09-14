package com.epam.yuri_karpov.selenium.ui.webdriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

	private static final String CHROME_DRIVER_PATH = "src/test/resources/drivers/chromedriver.exe";
	private static final String IE_DRIVER_PATH = "src/test/resources/drivers/IEDriverServer.exe";

	public static WebDriver createInstance() {

		WebDriver driver = null;
		ResourceBundle resource = ResourceBundle.getBundle("config");

		switch (resource.getString("driverType")) {
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "ie":
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();

				System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				        false);
				ieCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				driver = new InternetExplorerDriver(ieCapabilities);
				driver.manage().deleteAllCookies();

				try {
					Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 2");
				}
				catch (IOException e) {
					e.printStackTrace();
				}

				break;
			case "chrome":
				DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();

				System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
				chromeCapabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
				driver = new ChromeDriver(chromeCapabilities);
				break;
			default:
				driver = new FirefoxDriver();
				break;
		}

		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}
