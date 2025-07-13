package costar.testing.ui.pages;


import java.time.Duration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import costar.testing.ui.utils.WaitUtil;


public class CoStarJobsPage extends BasePage {
	
	// Configure it with Lombok
	Logger logger = LogManager.getRootLogger();

	public void searchForJob(String jobTitle) {
		getContext().findElement(searchBox).sendKeys(jobTitle);
		getContext().findElement(goButton).click();
		
		WaitUtil.waitTillInvisibilityOf(webDriver.findElement(loadingSpinner), Duration.ofMillis(5000));
	}
	
	@Override
	public SearchContext getContext() {
		return webDriver.findElement(contextLocator);
	}
	
	public int getJobsCount() {
		logger.log(Level.INFO, String.format("There are %s jobs available", 
				Integer.parseInt(getContext().findElement(numberOfJobsLocator).getText().split(" ")[0])));
		
		return Integer.parseInt(getContext().findElement(numberOfJobsLocator).getText().split(" ")[0]);
	}
	
	private static final By contextLocator = By.xpath(".//div[@role='main' and .//h1[contains(text(), 'Jobs at CoStar Group')]]");
	private static final By searchBox = By.cssSelector("#main-search-box");
	private static final By goButton = By.className("go-button");
	private static final By numberOfJobsLocator = By.cssSelector(".message-top-container strong");
	private static final By searchResultLocator = By.cssSelector(".accordion+.search-results-main-container");
	private static final By loadingSpinner = By.cssSelector(".flexbox .spinner");
}
