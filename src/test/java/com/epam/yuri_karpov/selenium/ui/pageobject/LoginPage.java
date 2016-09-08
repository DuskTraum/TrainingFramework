package com.epam.yuri_karpov.selenium.ui.pageobject;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.epam.yuri_karpov.selenium.bo.Account;

/**
 * Class works with Login page
 *
 * @author Yuri Karpov
 */
public class LoginPage {

	private static final String CONTAINS_MY_MAIL = "//a[contains(@title, 'yurii.kaprov@gmail.com')]";
	private static final String PASSWORD = "//input[@id='Passwd']";

	@FindBy(xpath = "//input[@id='Email']")
	private WebElement loginInput;
	@FindBy(xpath = PASSWORD)
	private WebElement passwordInput;
	@FindBy(xpath = "//input[@id='next']")
	private WebElement nextButton;
	@FindBy(xpath = "//input[@id='signIn']")
	private WebElement signInButton;

	private WebDriver driver;
	private static final Logger LOG = Logger.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	/**
	 * Method for set Login
	 *
	 * @param Account
	 *
	 */
	public LoginPage setLogin(Account account) {
		LOG.trace("start 'setLogin'");
		LOG.trace("Login:" + account.getLogin());

		new Actions(driver).sendKeys(loginInput, account.getLogin())
		        .build()
		        .perform();
		LOG.trace("finish 'setLogin'");
		return this;
	}

	/**
	 * Method for click Next button
	 *
	 */
	public LoginPage clickNext() {
		LOG.trace("start 'clickNext'");

		new Actions(driver).click(nextButton)
		        .build()
		        .perform();
		LOG.trace("finish 'clickNext'");
		return this;
	}

	/**
	 * Method for set Password
	 *
	 * @param Account
	 *
	 */
	public LoginPage setPassword(Account account) {
		LOG.trace("start 'setPassword'");

		waitUntilPresenceOfElementLocated(PASSWORD);
		passwordInput.sendKeys(account.getPassword());
		LOG.trace("finish 'setPassword'");
		return this;
	}

	/**
	 * Method for click SignIn button
	 *@return MainPage
	 */
	public MainPage signInToMail() {
		LOG.trace("start 'signInBtn'");

		new Actions(driver).click(signInButton)
		        .build()
		        .perform();
		waitUntilPresenceOfElementLocated(CONTAINS_MY_MAIL);
		LOG.trace("finish 'signInBtn'");
		return new MainPage(this.driver);
	}

	/**
	 * Method for waiting until presence of WebElement to be located
	 *
	 * @param String
	 *
	 */
	public void waitUntilPresenceOfElementLocated(String stringXpath) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
		        .pollingEvery(3, TimeUnit.SECONDS)
		        .ignoring(NoSuchElementException.class, ElementNotVisibleException.class);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(stringXpath)));
	}

}
