package com.epam.yuri_karpov.selenium.ui.pageobject;

import org.apache.log4j.Logger;

import com.epam.yuri_karpov.selenium.bo.Account;
import com.epam.yuri_karpov.selenium.ui.elements.LoginBlock;

/**
 * Class works with Login page
 *
 * @author Yuri Karpov
 */
public class LoginPage extends AbstractPage {

	private LoginBlock loginBlock;

	private static final Logger LOG = Logger.getLogger(LoginPage.class);

	/**
	 * Method for set Login
	 *
	 * @param Account
	 *
	 */
	public LoginPage login(Account account) {
		LOG.trace("start 'setLogin'");
		LOG.trace("Login:" + account.getLogin());

		loginBlock.login(account);
		LOG.trace("finish 'setLogin'");
		return this;
	}

	/**
	 * Method for click Next button
	 *
	 */
	public LoginPage clickNext() {
		LOG.trace("start 'clickNext'");

		loginBlock.clickNext();
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

		loginBlock.setPassword(account);
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

		loginBlock.signInToMail();
		LOG.trace("finish 'signInBtn'");
		return new MainPage();
	}

}
