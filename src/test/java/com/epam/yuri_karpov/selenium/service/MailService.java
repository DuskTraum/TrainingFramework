package com.epam.yuri_karpov.selenium.service;

import org.openqa.selenium.WebDriver;

import com.epam.yuri_karpov.selenium.bo.Account;
import com.epam.yuri_karpov.selenium.bo.Letter;
import com.epam.yuri_karpov.selenium.ui.pageobject.GooglePage;
import com.epam.yuri_karpov.selenium.ui.pageobject.MainPage;

/**
 * Class works with MailService
 *
 * @author Yuri Karpov
 */
public class MailService {

	private WebDriver driver;

	public MailService(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Method for login to GMail
	 *
	 * @param Account
	 *
	 */
	public void loginToMail(Account account) {
		GooglePage gPage = new GooglePage(driver);

		gPage.clickMail().clickOnSignIn()
		        .setLogin(account)
		        .clickNext()
		        .setPassword(account)
		        .signInToMail();
	}

	/**
	 * Method for login to GMail
	 *
	 * @param Letter
	 *
	 */
	public void sendMail(Letter letter) {
		MainPage main = new MainPage(driver);

		main.composeAndSendMail(letter);
	}

	/**
	 * Method for compose drafts
	 *
	 * @param Letter
	 *
	 */
	public void generateDrafts(Letter letter) {
		MainPage main = new MainPage(driver);

		main.composeDraft(letter);
	}

	/**
	 * Method for log out from GMail
	 */
	public void logOut() {
		MainPage main = new MainPage(driver);

		main.logOut();
	}

	/**
	 * Method for click on Drafts tab
	 */
	public void clickOnDraftsTab() {
		MainPage main = new MainPage(driver);

		main.clickOnDraftsTab();
	}

	/**
	 * Method for discard Sent Mails
	 */
	public void clearSentMails() {
		MainPage main = new MainPage(driver);

		main.discardSentMails();
	}

}
