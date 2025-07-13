package costar.testing.ui.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

public class CoStarCareerPage extends BasePage {
	
	public void clickSearchJobs() {
		getContext().findElement(searchJobsButton).click();
	}

	@Override
	public SearchContext getContext() {
		return webDriver.findElement(contextLocator);
	}
	
	
	private static final By contextLocator = By.xpath(".//div[contains(@class, 'coh-container page') and .//h1[contains(text(), 'CAREERS')]]");
	private static final By searchJobsButton = By.cssSelector(".btn-cont .btn-job");
}
