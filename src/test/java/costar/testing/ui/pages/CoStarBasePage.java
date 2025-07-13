package costar.testing.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

public class CoStarBasePage extends BasePage {

	public void clickOnCareerButton() {
		webDriver.findElement(careerButtonLocator).click();
	}
	
	@Override
	public SearchContext getContext() {
		return webDriver.findElement(contextLocator);
	}
	
	private static final By careerButtonLocator = By.linkText("Careers");
	private static final By contextLocator = By.xpath(".//div[contains(@class, 'coh-container page') and .//h1[contains(text(), 'A Global Leader in the Digital Transformation')]]");
}
