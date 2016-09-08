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
import com.epam.yuri_karpov.selenium.ui.pageobject.MainPage;
import com.epam.yuri_karpov.selenium.utils.ScreenShotListener;

/**
 * <p>
 * Test case #1 Testing create drafts
 * <p>
 * <i>Step 1</i> - Login as User <br>
 * <i>Step 2</i> - Create 3 drafts <br>
 * <i>Step 3</i> - Go to 'Drafts' tab <br>
 * <i>Step 4</i> - Verify each draft <br>
 * <i>Step 5</i> - Delete all created drafts <br>
 * <p>
 * <b>Expected result</b>: Drafts data (To, Subject, Text) received from step 4,
 * coincides with the data entered in Step 2
 *
 *
 * @author Yuri Karpov
 */
@Listeners({ HTMLReporter.class, ScreenShotListener.class })
public class CreateAndCheckDraftsTest extends BaseTest {
	private ResourceBundle resource = ResourceBundle.getBundle("config");
	private Account account;
	private MailService mailService;
	private Letter letter;
	private static final Logger LOG = Logger.getLogger(CreateAndCheckDraftsTest.class);
	private Object[][] dataToLetters;

	// assertion variables
	private ArrayList<Object[]> actualMailData;
	private List<String> actualMailDataInList;
	private Object[][] actualMailDataToObject;

	@BeforeClass
	public void preliminarySteps() {
		LOG.info("start 'LoginToGmail'");

		account = new Account(resource.getString("login"), resource.getString("password"));
		mailService = new MailService(driver);

		driver.get(resource.getString("mainURL"));
		mailService.loginToMail(account);
		LOG.info("finish 'LoginToGmail'");
	}

	@Test(dataProvider = "mailData")
	public void generateDrafts(String to, String subj, String text) {
		LOG.info("start 'generateDrafts'");

		letter = initLetters(to, subj, text);

		mailService.generateDrafts(letter);
		LOG.info("finish 'generateDrafts'");
	}

	@Test(dependsOnMethods = "generateDrafts")
	public void checkDraftsTest() {
		LOG.info("start 'checkDraftsTest'");

		MainPage mainPage = new MainPage(driver);
		actualMailData = new ArrayList<>();
		actualMailDataToObject = new Object[3][];
		int row = 0;

		mailService.clickOnDraftsTab();

		for (int i = 0; i < mainPage.getMailList().size(); i++) {
			actualMailDataInList = new ArrayList<>();

			mainPage.getMailList().get(i).click();
			actualMailDataInList.add(mainPage.getDraftComposeToField().getText());
			actualMailDataInList.add(mainPage.getDraftComposeSubject().getText());
			actualMailDataInList.add(mainPage.getDraftTextField().getText());
			actualMailData.add(actualMailDataInList.toArray());
			mainPage.getCloseComposeButton().click();
		}

		for (Object[] data : actualMailData) {
			actualMailDataToObject[row++] = data;
		}

		ArrayUtils.reverse(actualMailDataToObject);
		mainPage.discardDrafts();
		Assert.assertEquals(Arrays.deepToString(actualMailDataToObject), Arrays.deepToString(dataToLetters),
		        "Mail data from DataProvider and actualMailData in Drafts didn't equals");
		LOG.info("finish 'checkDraftsTest'");
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
