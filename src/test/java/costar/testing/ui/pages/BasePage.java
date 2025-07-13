package costar.testing.ui.pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import costar.testing.ui.base.DriverFactory;

public class BasePage {
	
	protected WebDriver webDriver;
	
	public BasePage() {
		this.webDriver = DriverFactory.getDriver();	
	}
	
	public SearchContext getContext() {
		return webDriver;
	}
}