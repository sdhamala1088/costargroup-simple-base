package costar.testing.ui.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DriverFactory {
	
	private DriverFactory() {
	}
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		if (driver.get() == null) {
			initDriver("chrome");
		}
			
		return driver.get();
	}
	
	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
	

	public static void initDriver(String browser) {
		switch (browser) {

			case "chrome":
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
			    options.addArguments("--remote-allow-origins=*");
			    options.addArguments("--disable-dev-shm-usage");
			    options.addArguments("--no-sandbox");
			    options.addArguments("--allow-file-access-from-files");
			    options.setExperimentalOption("w3c", true); 
				driver.set(new ChromeDriver(options));
				driver.get().manage().timeouts().implicitlyWait(Duration.ofMillis(500));
				break;
			case "firefox":
				break;
			case "edge":
				break;
			default:
			    throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		
	}
	

}
