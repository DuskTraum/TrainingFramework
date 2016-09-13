package com.epam.yuri_karpov.selenium.ui.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class works with Google page
 *
 * @author Yuri Karpov
 */
public class GooglePage extends AbstractPage{

	private static final Logger LOG = Logger.getLogger(GooglePage.class);

	@FindBy(xpath = "//*[@href='https://mail.google.com/mail/?tab=wm' and contains(text(),'Почта')]")
	private WebElement mailButton;

	@FindBy(xpath = "//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
	private WebElement signInButton;

	/**
	 * Method click on mail button
	 *
	 * @return GooglePage
	 */
	public GooglePage clickMail() {
		LOG.trace("start 'clickMail'");

		mailButton.click();
		LOG.trace("finish 'clickMail'");
		return new GooglePage();
	}

	/**
	 * Method click on SignIn button
	 *
	 * @return LoginPage
	 */
	public LoginPage clickOnSignIn() {
		LOG.trace("start 'clickOnSignIn'");

		if (!driver.findElements(By.xpath("//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']"))
		        .isEmpty()) {
			signInButton.click();
		}

		LOG.trace("finish 'clickOnSignIn'");
		return new LoginPage();
	}

}
