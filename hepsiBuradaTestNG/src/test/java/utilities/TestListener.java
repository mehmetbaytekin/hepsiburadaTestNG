/**
 * 
 */
package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * @author mehmet.baytekin
 *
 */
public class TestListener implements ITestListener {

	private UtilityMethods utility = new UtilityMethods();

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub }
		Reporter.log(result.getName() + " isimli Test Baslatiliyor");
		System.out.println(result.getName() + " isimli Test Baslatiliyor");

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log(result.getName() + " isimli Test Basarili");
		System.out.println(result.getName() + " isimli Test Basarili");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		utility.getSS(result, utility.getPath(2));
		Reporter.log(result.getName() + " isimli Test Basarisiz");
		System.out.println(result.getName() + " isimli Test Basarisiz");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		utility.getSS(result, utility.getPath(3));

		Reporter.log(result.getName() + " isimli Test Atlandi");
		System.out.println(result.getName() + " isimli Test Atlandi");
	}

}
