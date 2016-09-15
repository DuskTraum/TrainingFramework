package com.epam.yuri_karpov.selenium.service;

import com.epam.yuri_karpov.selenium.bo.Account;
import com.epam.yuri_karpov.selenium.bo.Letter;
import com.epam.yuri_karpov.selenium.ui.pageobject.GooglePage;
import com.epam.yuri_karpov.selenium.ui.pageobject.MainPage;

/**
 * Class works with MailService
 *
 * @author Yuri Karpov
 */
public class MailService extends BaseService {

	/**
	 * Method for login to GMail
	 *
	 * @param Account
	 *
	 */
	public void loginToMail(Account account) {
		GooglePage gPage = new GooglePage();

		gPage.clickMail().clickOnSignIn()
		        .login(account)
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
		MainPage main = new MainPage();

		main.composeAndSendMail(letter);
	}

	/**
	 * Method for compose drafts
	 *
	 * @param Letter
	 *
	 */
	public void generateDrafts(Letter letter) {
		MainPage main = new MainPage();

		main.composeDraft(letter);
	}

	/**
	 * Method for log out from GMail
	 */
	public void logOut() {
		MainPage main = new MainPage();

		main.logOut();
	}

	/**
	 * Method for click on Drafts tab
	 */
	public void clickOnDraftsTab() {
		MainPage main = new MainPage();

		main.clickOnDraftsTab();
	}

	/**
	 * Method for discard Sent Mails
	 */
	public void clearSentMails() {
		MainPage main = new MainPage();

		main.discardSentMails();
	}

	/**
	 * Method for discard Trash
	 */
	public void clearTrash() {
		MainPage main = new MainPage();

		main.clearTrash();
	}

}
