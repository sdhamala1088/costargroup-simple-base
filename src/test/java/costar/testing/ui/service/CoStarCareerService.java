package costar.testing.ui.service;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import costar.testing.ui.pages.CoStarBasePage;
import costar.testing.ui.pages.CoStarCareerPage;
import costar.testing.ui.pages.CoStarJobsPage;
import costar.testing.ui.utils.WaitUtil;

public class CoStarCareerService {
	
	private CoStarBasePage coStarBasePage = new CoStarBasePage();
	private CoStarCareerPage coStarCareerPage = new CoStarCareerPage();
	private CoStarJobsPage coStarJobsPage = new CoStarJobsPage();

	public void isAutomationJobAvailable(String jobTitle) {
		coStarBasePage.clickOnCareerButton();
		coStarCareerPage.clickSearchJobs();
		
		WaitUtil.waitTillVisibilityOf((WebElement )coStarJobsPage.getContext());
		
		coStarJobsPage.searchForJob(jobTitle);
		Assert.assertTrue(coStarJobsPage.getJobsCount() > 0);
	}

}
