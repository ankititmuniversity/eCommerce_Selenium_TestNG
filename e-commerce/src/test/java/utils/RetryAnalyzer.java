package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
	int retryCount = 0;
	int maxRetry = 2;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(retryCount<=maxRetry) {
			retryCount++;
			return true;
		}
		return false;
	}

}
