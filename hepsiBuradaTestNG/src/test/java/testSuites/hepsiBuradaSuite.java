/**
 * 
 */
package testSuites;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.Base;
import base.RetryAnalyzer;
import hepsiBuradaPages.LoginPage;
import hepsiBuradaPages.ProductPage;

/**
 * @author mehmet.baytekin
 *
 */

public class hepsiBuradaSuite extends Base {

	private int step = 0;
	boolean fail1 = true;

	@Test(retryAnalyzer = RetryAnalyzer.class)
	@Parameters({ "username", "password", "searchValue" })
	public void UrunYorumTest(String username, String password, String searchValue) throws InterruptedException {

		LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
		ProductPage pp = PageFactory.initElements(driver, ProductPage.class);

		lp.login(username, password, environment);
		pp.urunArama(searchValue);

	}

}
