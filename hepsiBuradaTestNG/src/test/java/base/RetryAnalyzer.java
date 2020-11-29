/**
 * 
 */
package base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author mehmet.baytekin
 *
 */
public class RetryAnalyzer implements IRetryAnalyzer {

	private int count = 0;
	private int maxTry = 4;

	@Override
	public boolean retry(ITestResult result) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		if (count < maxTry) {
			count++;
			return true;
		} else {
			return false;
		}

	}
}