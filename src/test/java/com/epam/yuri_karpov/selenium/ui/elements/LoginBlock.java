package com.epam.yuri_karpov.selenium.ui.elements;

import org.openqa.selenium.support.FindBy;

import com.epam.yuri_karpov.selenium.bo.Account;
import com.epam.yuri_karpov.selenium.service.WaitService;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Class works with login block <br>
 *
 * @author Yuri Karpov
 *
 */
@FindBy(xpath = "//div[contains(@class, 'card signin-card')]")
public class LoginBlock extends CommonElement {
	private static final String PASSWORD_INPUT_FIELD = "//input[@id='Passwd']";

	@Name("Login")
	@FindBy(xpath = "//input[@id='Email']")
	private TextInput loginInput;

	@Name("Password")
	@FindBy(xpath = PASSWORD_INPUT_FIELD)
	private TextInput passwordInput;

	@Name("Next button")
	@FindBy(xpath = "//input[@id='next']")
	private Button nextButton;

	@Name("Sign In button")
	@FindBy(xpath = "//input[@id='signIn']")
	private Button signInButton;

	/**
	 * Method for set Login
	 *
	 * @param Account
	 *
	 */
	public void login(Account account) {
		WaitService.waitForVisibilityOfElement(loginInput);
		loginInput.sendKeys(account.getLogin());
	}

	/**
	 * Method for click Next button
	 *
	 */
	public void clickNext() {
		WaitService.waitUntilElementToBeClickable(nextButton);
		nextButton.click();
	}

	/**
	 * Method for set Password
	 *
	 * @param Account
	 *
	 */
	public void setPassword(Account account) {
		WaitService.waitUntilPresenceOfElementLocated(PASSWORD_INPUT_FIELD);
		passwordInput.sendKeys(account.getPassword());
	}

	/**
	 * Method for click SignIn button
	 *
	 */
	public void signInToMail() {
		WaitService.waitUntilElementToBeClickable(signInButton);
		signInButton.click();
	}

}
