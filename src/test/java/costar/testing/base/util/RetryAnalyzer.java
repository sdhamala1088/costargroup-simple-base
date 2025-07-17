package costar.testing.base.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
	
	private static final int numberOfRetry = 1;
	private int retryCount = 0;
	
	@Override
	public boolean retry(ITestResult result) {
		
		while (!result.isSuccess() && retryCount < numberOfRetry) {
			retryCount = retryCount + 1;
			return true;
		}
		
		return false; 
	}
}
