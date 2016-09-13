package com.epam.yuri_karpov.selenium.tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import com.epam.yuri_karpov.selenium.bo.Account;
import com.epam.yuri_karpov.selenium.bo.Letter;
import com.epam.yuri_karpov.selenium.service.MailService;
import com.epam.yuri_karpov.selenium.service.WaitService;
import com.epam.yuri_karpov.selenium.ui.pageobject.MainPage;
import com.epam.yuri_karpov.selenium.utils.ScreenShotListener;

/**
 * <p>
 * Test case #2 Testing send mails
 * <p>
 * <i>Step 1</i> - Login as User <br>
 * <i>Step 2</i> - Create 3 mails and send them <br>
 * <i>Step 3</i> - Go to 'Sent Mail' tab <br>
 * <i>Step 4</i> - Verify each sent mail <br>
 * <i>Step 5</i> - Delete all sent mails <br>
 * <p>
 * <b>Expected result</b>: Sent mails data (To, Subject, Text) received from
 * step 4, coincides with the data entered in Step 2
 *
 *
 * @author Yuri Karpov
 */
@Listeners({ HTMLReporter.class, ScreenShotListener.class })
public class SendAndCheckMailsTest extends BaseTest {
	private ResourceBundle resource = ResourceBundle.getBundle("config");
	private Account account;
	private Letter letter;
	private static final Logger LOG = Logger.getLogger(SendAndCheckMailsTest.class);
	private MailService mailService;
	private Object[][] dataToLetters;

	// assertion variables
	private ArrayList<Object[]> actualMailData;
	private Object[][] actualMailDataToObject;

	@BeforeClass
	public void preliminarySteps() {
		LOG.info("start 'LoginToGmail'");

		account = new Account(resource.getString("login"), resource.getString("password"));
		mailService = new MailService();

		mailService.loginToMail(account);
		LOG.info("finish 'LoginToGmail'");
	}

	@Test(dataProvider = "mailData")
	public void gmailsSendMessages(String to, String subj, String text) {
		LOG.info("start 'gmailsSendMsgs'");

		letter = initLetters(to, subj, text);

		mailService.sendMail(letter);
		LOG.info("finish 'gmailsSendMsgs'");
	}

	@Test(dependsOnMethods = "gmailsSendMessages")
	public void checkSentMailsTest() {
		LOG.info("start 'checkSentMailsTest'");

		MainPage mainPage = new MainPage();
		actualMailData = new ArrayList<>();
		actualMailDataToObject = new Object[3][];
		int row = 0;

		mainPage.clickOnSentMail();

		for (int i = 0; i < mainPage.getMailList().size(); i++) {
			List<String> actualMailDataInList = new ArrayList<>();

			mainPage.getMailList().get(i).click();
			WaitService.waitForVisibilityOfElement(mainPage.getSentMailComposeTo());
			actualMailDataInList.add(mainPage.getSentMailComposeTo().getText());
			actualMailDataInList.add(mainPage.getSentMailComposeSubject().getText());
			actualMailDataInList.add(mainPage.getSentMailTextField().getText());
			actualMailData.add(actualMailDataInList.toArray());
			mainPage.clickOnBackToSentMailButton();

		}

		for (Object[] data : actualMailData) {
			actualMailDataToObject[row++] = data;
		}

		mailService.clearSentMails();
		ArrayUtils.reverse(actualMailDataToObject);
		Assert.assertEquals(Arrays.deepToString(actualMailDataToObject), Arrays.deepToString(dataToLetters),
		        "Mail data from DataProvider and actualMailData in Drafts didn't equals");
		LOG.info("finish 'checkSentMailsTest'");
	}

	@DataProvider(name = "mailData")
	public Object[][] mailData() {
		List<String[]> dataList = new ArrayList<>();

		try (BufferedReader input = new BufferedReader(new FileReader("DataProvider.txt"))) {
			String line;

			while ((line = input.readLine()) != null) {
				dataList.add(line.split(","));
			}

			dataToLetters = dataList.toArray(new String[][] {});
		}
		catch (FileNotFoundException fe) {
			System.err.println(fe);
		}
		catch (IOException e) {
			System.err.println(e);
		}
		return dataToLetters;
	}

	public Letter initLetters(String to, String subj, String text) {
		Letter letter = new Letter(to, subj, text);

		return letter;
	}

}
