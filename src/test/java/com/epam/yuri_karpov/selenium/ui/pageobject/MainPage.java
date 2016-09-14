package com.epam.yuri_karpov.selenium.ui.pageobject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.epam.yuri_karpov.selenium.bo.Letter;
import com.epam.yuri_karpov.selenium.service.WaitService;

/**
 * Class works with Main page
 *
 * @author Yuri Karpov
 */
public class MainPage extends AbstractPage {

	private static final String OK_BUTTON = "//button[@name='ok']";
	private static final String THIRD_DRAFT = "//div[@role='main']//tbody/tr[3]";
	private static final String NEW_MESSAGE = "//div[contains(text(), 'New Message')]";
	private static final String VIEW_MESSAGE = "//span[contains(text(),'View message')]";
	private static final String SAVED_MESSAGE = "//span[contains(text(),'Saved')]";
	private static final Logger LOG = Logger.getLogger(MainPage.class);
	private static final String DRAFTS_HAVE_BEEN_DELETED_MSG = "//span[contains(text(), 'Drafts have been deleted')]";

	@FindBy(xpath = "//div[contains(text(), 'COMPOSE')]")
	private WebElement composeButton;

	@FindBy(xpath = "//textarea[@name='to']")
	private WebElement composeTo;

	@FindBy(xpath = "//input[@name = 'subjectbox']")
	private WebElement composeSubject;

	@FindBy(xpath = "//input[@type = 'hidden' and @name = 'subject']")
	private WebElement hiddenSubject;

	@FindBy(xpath = "//table[@role='presentation']//h2")
	private WebElement sentMailComposeSubject;

	@FindBy(xpath = "//div[@role='listitem']//div[@dir='ltr']")
	private WebElement sentMailTextField;

	@FindBy(xpath = "//div[@role='complementary']//td[@dir='ltr']/span[@title]")
	private WebElement sentMailComposeTo;

	@FindBy(xpath = "//div[@aria-label = 'Message Body']")
	private WebElement draftTextField;

	@FindBy(xpath = "//div[@style]/span[@email]")
	private WebElement draftComposeToField;

	@FindBy(xpath = "//h2/div[@style]")
	private WebElement draftComposeSubject;

	@FindBy(xpath = "//img[@alt = 'Close']")
	private WebElement closeComposeButton;

	@FindBy(xpath = NEW_MESSAGE)
	private WebElement newMessageBox;

	@FindBy(xpath = "//a[contains(text(), 'Drafts')]")
	private WebElement draftsButton;

	@FindBy(xpath = "//div[@gh]//span[@role='checkbox' and @dir = 'ltr']")
	private WebElement selectAllDraftsOrMails;

	@FindBy(xpath = "//div[contains(text(), 'Discard drafts')]")
	private WebElement discardDrafts;

	@FindBy(xpath = "//a[contains(@title, 'yurii.kaprov@gmail.com')]")
	private WebElement myMailButton;

	@FindBy(xpath = "//a[contains(text(), 'Sign out')]")
	private WebElement signOutButton;

	@FindBy(xpath = "//span[contains(text(), 'View message')]")
	private WebElement sendMessageConfirm;

	@FindBy(xpath = "//div[contains(text(), 'Send')]")
	private WebElement sendMessage;

	@FindBy(xpath = "//div[@title = 'Back to Sent Mail']")
	private WebElement navigateToBackToSentMailButton;

	@FindBy(xpath = "//div[@aria-label = 'Back to Sent Mail']")
	private WebElement backToSentMailButton;

	@FindBy(xpath = "//a[contains(text(),'Sent Mail')]")
	private WebElement sentMail;

	@FindBy(xpath = "//img[@data-tooltip='Show details']")
	private WebElement messageInfo;

	@FindBy(xpath = "//div[@data-tooltip='Delete']")
	private WebElement deleteSentMails;

	@FindBy(xpath = OK_BUTTON)
	private WebElement okButton;

	@FindBy(xpath = "//div[@role='main']//tbody/tr")
	private List<WebElement> mailOrDraftList;

	@FindBy(xpath = "//div[@data-tooltip='Refresh']")
	private WebElement refreshButton;

	@FindBy(xpath = "//div[@title = 'Refresh']")
	private WebElement navigateToRefreshButton;

	@FindBy(xpath = "//div[@data-tooltip='Settings']")
	private WebElement settingsButton;

	@FindBy(xpath = "//a[@title='Trash']")
	private WebElement trashButton;

	@FindBy(xpath = "//span[contains(text() , 'Empty Trash now')]")
	private WebElement emptyTrashButton;

	@FindBy(xpath = "//div[contains(text(),'Click here to')]")
	private WebElement textEnterField;

	/**
	 * Method for compose draft
	 *
	 * @param Letter
	 *
	 */
	public void composeDraft(Letter letter) {
		LOG.trace("start 'composeMsg'");
		LOG.trace(letter.toString());

		WaitService.waitUntilElementToBeClickable(composeButton);
		composeButton.click();
		WaitService.waitUntilPresenceOfElementLocated(NEW_MESSAGE);
		composeTo.sendKeys(letter.getTo());
		composeSubject.sendKeys(letter.getSubject());
		draftTextField.sendKeys(letter.getText());
		WaitService.waitUntilPresenceOfElementLocated(SAVED_MESSAGE);
		closeComposeButton.click();
		LOG.trace("finish 'composeMsg'");
	}

	/**
	 * Method for compose and send mail
	 *
	 * @param Letter
	 *
	 */
	public void composeAndSendMail(Letter letter) {
		LOG.trace("start 'composeAndSendMsg'");
		LOG.trace(letter.toString());

		WaitService.waitUntilElementToBeClickable(composeButton);
		composeButton.click();
		WaitService.waitUntilPresenceOfElementLocated(NEW_MESSAGE);
		composeTo.sendKeys(letter.getTo());
		composeSubject.sendKeys(letter.getSubject());
		draftTextField.sendKeys(letter.getText());
		sendMessage.click();
		WaitService.waitUntilPresenceOfElementLocated(VIEW_MESSAGE);
		LOG.trace("finish 'composeAndSendMsg'");
	}

	/**
	 * Method for click on Drafts tab
	 */
	public void clickOnDraftsTab() {
		LOG.trace("start 'clickOnDraftsTab'");

		draftsButton.click();
		WaitService.waitUntilElementToBeClickable(THIRD_DRAFT);
		selectAllDraftsOrMails.click();
		WaitService.waitUntilPresenceOfElementLocated(THIRD_DRAFT);
		LOG.trace("finish 'clickOnDraftsTab'");
	}

	/**
	 * Method for click on Sent Mail tab
	 */
	public void clickOnSentMail() {
		LOG.trace("start 'clickOnSentMail'");

		WaitService.waitUntilElementToBeClickable(sentMail);
		sentMail.click();
		WaitService.waitUntilElementToBeClickable(THIRD_DRAFT);
		WaitService.waitUntilElementToBeClickable(selectAllDraftsOrMails);
		WaitService.waitUntilPresenceOfElementLocated(THIRD_DRAFT);
		LOG.trace("finish 'clickOnSentMail'");
	}

	/**
	 * Method for click on BackToSentMailButton from sent mail
	 */
	public void clickOnBackToSentMailButton() {
		LOG.trace("start 'clickOnBackToSentMailButton'");

		WaitService.waitUntilElementToBeClickable(navigateToBackToSentMailButton);
		new Actions(driver).moveToElement(navigateToBackToSentMailButton).build().perform();
		WaitService.waitUntilElementToBeClickable(backToSentMailButton);
		new Actions(driver).click(backToSentMailButton).build().perform();
		WaitService.waitUntilElementToBeClickable(THIRD_DRAFT);
		LOG.trace("finish 'clickOnBackToSentMailButton'");

	}

	/**
	 * Method for discard Drafts
	 */
	public void discardDrafts() {
		LOG.trace("start 'discardDrafts'");

		discardDrafts.click();
		WaitService.waitUntilPresenceOfElementLocated(DRAFTS_HAVE_BEEN_DELETED_MSG);
		LOG.trace("finish 'discardDrafts'");
	}

	/**
	 * Method for discard Sent Mails
	 */
	public void discardSentMails() {
		LOG.trace("start 'discardSentMails'");

		sentMail.click();
		WaitService.waitUntilElementToBeClickable(selectAllDraftsOrMails);
		selectAllDraftsOrMails.click();
		WaitService.waitUntilElementToBeClickable(deleteSentMails);
		deleteSentMails.click();
		WaitService.waitUntilPresenceOfElementLocated(OK_BUTTON);
		okButton.click();
		LOG.trace("finish 'discardSentMails'");
	}

	/**
	 * Method for log out from GMail
	 */
	public void logOut() {
		LOG.trace("start 'logOut'");
		JavascriptExecutor ex = (JavascriptExecutor) driver;

		ex.executeScript("arguments[0].click();", myMailButton);
		WaitService.waitUntilElementToBeClickable(signOutButton);
		signOutButton.click();
		LOG.trace("finish 'logOut'");
	}

	/**
	 * Method for discard Trash
	 */
	public void clearTrash() {
		LOG.trace("finish 'clearTrash'");

		WaitService.waitUntilElementToBeClickable(trashButton);
		trashButton.click();

		if (!driver.findElements(By.xpath("//span[contains(text() , 'Empty Trash now')]"))
		        .isEmpty()) {
			emptyTrashButton.click();
			WaitService.waitUntilPresenceOfElementLocated(OK_BUTTON);
			okButton.click();
		}
		LOG.trace("start 'clearTrash'");
	}

	public WebElement getDraftTextField() {
		return draftTextField;
	}

	public WebElement getDraftComposeToField() {
		return draftComposeToField;
	}

	public WebElement getDraftComposeSubject() {
		return draftComposeSubject;
	}

	public WebElement getComposeButton() {
		return composeButton;
	}

	public List<WebElement> getMailList() {
		return mailOrDraftList;
	}

	public WebElement getCloseComposeButton() {
		return closeComposeButton;
	}

	public WebElement getSentMailComposeSubject() {
		return sentMailComposeSubject;
	}

	public WebElement getSentMailTextField() {
		return sentMailTextField;
	}

	public WebElement getSentMailComposeTo() {
		return sentMailComposeTo;
	}

	public WebElement getSentMail() {
		return sentMail;
	}

	public WebElement getDeleteSentMails() {
		return deleteSentMails;
	}

	public WebElement getTextEnterField() {
		return textEnterField;
	}

}
