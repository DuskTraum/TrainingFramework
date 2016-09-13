package com.epam.yuri_karpov.selenium.ui.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.epam.yuri_karpov.selenium.bo.Account;
import com.epam.yuri_karpov.selenium.service.WaitService;

/**
 * Class works with Login page
 *
 * @author Yuri Karpov
 */
public class LoginPage extends AbstractPage {

	private static final String CONTAINS_MY_MAIL = "//a[contains(@title, 'yurii.kaprov@gmail.com')]";
	private static final String PASSWORD_INPUT_FIELD = "//input[@id='Passwd']";

	@FindBy(xpath = "//input[@id='Email']")
	private WebElement loginInput;
	@FindBy(xpath = PASSWORD_INPUT_FIELD)
	private WebElement passwordInput;
	@FindBy(xpath = "//input[@id='next']")
	private WebElement nextButton;
	@FindBy(xpath = "//input[@id='signIn']")
	private WebElement signInButton;

	private static final Logger LOG = Logger.getLogger(LoginPage.class);

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

		WaitService.waitUntilPresenceOfElementLocated(PASSWORD_INPUT_FIELD);
		passwordInput.sendKeys(account.getPassword());
		LOG.trace("finish 'setPassword'");
		return this;
	}

	/**
	 * Method for click SignIn button
	 *
	 * @return MainPage
	 */
	public MainPage signInToMail() {
		LOG.trace("start 'signInBtn'");

		new Actions(driver).click(signInButton)
		        .build()
		        .perform();
		WaitService.waitUntilPresenceOfElementLocated(CONTAINS_MY_MAIL);
		LOG.trace("finish 'signInBtn'");
		return new MainPage();
	}

	/**
	 * Method for waiting until presence of WebElement to be located
	 *
	 * @param String
	 *
	 */

}
