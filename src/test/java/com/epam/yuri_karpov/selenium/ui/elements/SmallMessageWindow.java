package com.epam.yuri_karpov.selenium.ui.elements;

import org.openqa.selenium.support.FindBy;

import com.epam.yuri_karpov.selenium.service.WaitService;

import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Class works with small message window <br>
 *
 * @author Yuri Karpov
 *
 */
@FindBy(xpath = "//div[@role='dialog']")
public class SmallMessageWindow extends CommonElement{

	private static final String NEW_MESSAGE = "//div[contains(text(), 'New Message')]";
	private static final String SAVED_MESSAGE = "//span[contains(text(),'Saved')]";

	@FindBy(xpath = "//textarea[@name='to']")
	private CommonElement composeTo;

	@FindBy(xpath = "//input[@name = 'subjectbox']")
	private TextInput composeSubject;

	@FindBy(xpath = "//div[@aria-label = 'Message Body']")
	private CommonElement composeTextField;

	@FindBy(xpath = "//div[contains(text(), 'Send')]")
	private Button sendLetterButton;

	@FindBy(xpath = "//img[@alt = 'Close']")
	private Button closeComposeButton;

	@FindBy(xpath = "//div[@style]/span[@email]")
	private CommonElement draftComposeToField;

	@FindBy(xpath = "//h2/div[@style]")
	private TextBlock draftComposeSubject;


	public void fillAndSaveLetter(String to, String subj, String text) {

		WaitService.waitUntilPresenceOfElementLocated(NEW_MESSAGE);
		composeTo.sendKeys(to);
		composeSubject.sendKeys(subj);
		composeTextField.sendKeys(text);
		WaitService.waitUntilPresenceOfElementLocated(SAVED_MESSAGE);
		closeComposeButton.click();
	}

	public void fillAndSendLetter(String to, String subj, String text) {

		WaitService.waitUntilPresenceOfElementLocated(NEW_MESSAGE);
		composeTo.sendKeys(to);
		composeSubject.sendKeys(subj);
		composeTextField.sendKeys(text);
		sendLetterButton.click();
	}

	public CommonElement getComposeTextField() {
		return composeTextField;
	}

	public CommonElement getDraftComposeToField() {
		return draftComposeToField;
	}

	public TextBlock getDraftComposeSubject() {
		return draftComposeSubject;
	}

	public Button getCloseComposeButton() {
		return closeComposeButton;
	}





}
