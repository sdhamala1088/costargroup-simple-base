package costar.testing.ui.utils;

import static costar.testing.ui.base.DriverFactory.getDriver;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	
	private static final int DEFAULT_TIMEOUT_MILLI = 10000;

	
	public static void waitTillVisibilityOf(WebElement element) {
		waitTillVisibilityOf(element, Duration.ofMillis(DEFAULT_TIMEOUT_MILLI));
	}
	
	public static void waitTillVisibilityOf(WebElement element, Duration duration) {
		WebDriverWait  webDriverWait = new WebDriverWait(getDriver(), duration);
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitTillInvisibilityOf(WebElement element, Duration duration) {
		WebDriverWait  webDriverWait = new WebDriverWait(getDriver(), duration);
		webDriverWait.until(ExpectedConditions.invisibilityOf(element));
	}

}
