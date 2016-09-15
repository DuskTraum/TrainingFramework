package com.epam.yuri_karpov.selenium.ui.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.epam.yuri_karpov.selenium.ui.webdriver.DriverManager;

import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * Class for Abstract page.
 *
 */
public class AbstractPage {
	protected WebDriver driver;

	public AbstractPage() {
		if (driver == null) {
			driver = DriverManager.getDriver();
		}
		PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
	}


}
