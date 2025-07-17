package costar.testing.ui.tests;

import static costar.testing.ui.base.DriverFactory.getDriver;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import costar.testing.ui.base.DriverFactory;
import costar.testing.ui.service.CoStarCareerService;

public class CoStarCareerTest {
	
	private static final String HOME_URL = "https://www.costargroup.com/";
	private WebDriver driver;
	private CoStarCareerService costarCareerService;
	
	@BeforeClass
	public void setup() {
		driver = getDriver();
		costarCareerService = new CoStarCareerService();
	}
	
	@AfterClass
	public void tearDown() {
		DriverFactory.quitDriver();
	}
	
	@Test (dataProvider = "sdet_alias", testName = "Test Authentication", groups= {"smoke", "ui"})
	public void searchForSDETJob(String jobTitle) {
		driver.get(HOME_URL);
		costarCareerService.isAutomationJobAvailable(jobTitle);
	}
	
	
	@DataProvider(name="sdet_alias")
	public Object[][] sdetJobAlias() {
		return new Object[][] {{"SDET"},{"Software Automation Engineer in Test"}};
	}
	
}