package com.epam.yuri_karpov.selenium.service;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.epam.yuri_karpov.selenium.ui.webdriver.DriverManager;

/**
 * Class contains the different methods which implement waitings
 *
 * @author Yuri Karpov
 */
public class WaitService extends BaseService {

	/**
	 * Method for waiting until WebElement to be clickable
	 *
	 * @param WebElement
	 */
	public static void waitUntilElementToBeClickable(WebElement element) {
		WebDriver driver = DriverManager.getDriver();
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
		        .pollingEvery(3, TimeUnit.SECONDS)
		        .ignoring(NoSuchElementException.class, ElementNotVisibleException.class);

		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Method for waiting until WebElement to be clickable
	 *
	 * @param String
	 *
	 */
	public static void waitUntilElemntToBeClickable(String stringXpath) {
		WebDriver driver = DriverManager.getDriver();

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
		        .pollingEvery(3, TimeUnit.SECONDS)
		        .ignoring(NoSuchElementException.class, ElementNotVisibleException.class);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(stringXpath)));
	}

	/**
	 * Method for waiting until presence of WebElement to be located
	 *
	 * @param String
	 *
	 */
	public static void waitUntilPresenceOfElementLocated(String stringXpath) {
		WebDriver driver = DriverManager.getDriver();

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
		        .pollingEvery(3, TimeUnit.SECONDS)
		        .ignoring(NoSuchElementException.class, ElementNotVisibleException.class);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(stringXpath)));
	}

	/**
	 * Method for waiting until visibility of WebElement to be visible
	 *
	 * @param WebElement
	 *
	 */
	public static void waitForVisibilityOfElement(WebElement element) {
		WebDriver driver = DriverManager.getDriver();

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
		        .pollingEvery(3, TimeUnit.SECONDS)
		        .ignoring(NoSuchElementException.class, ElementNotVisibleException.class);

		wait.until(
		        ExpectedConditions.visibilityOf(element));
	}


}
