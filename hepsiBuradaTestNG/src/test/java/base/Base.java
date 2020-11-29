/**
 * 
 */
package base;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collections;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import utilities.UtilityMethods;

/**
 * @author mehmet.baytekin
 *
 */
@Listeners(utilities.TestListener.class)
public class Base {

	public WebDriver driver;
	public WebDriverWait wait;
	public String myBrowser, environment;
	public Connection connection;
	private UtilityMethods utility = new UtilityMethods();

	@BeforeSuite
	@Parameters({ "environment" })
	public void dbConnection(String environment) throws IOException {
		utility.setPath();

	}

	/**
	 * Her test ba�lmadan �nce �al���r. Suite �zerinde belirtilen bilgiye g�re
	 * driver ba�lat�r.
	 * 
	 * @param myBrowser   : Suite �zerinden belirtilen browser ismi. chrome veya
	 *                    firefox
	 * @param environment : Testing ko�ulaca�� ortam
	 */
	@SuppressWarnings("deprecation")
	@BeforeTest
	@Parameters({ "myBrowser", "environment" })
	public void initDriver(String myBrowser, String environment) {

		if (System.getProperty("os.name").contains("Window")) {
			if (myBrowser.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

				DesiredCapabilities capability = new DesiredCapabilities();
				capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

				ChromeOptions options = new ChromeOptions();

				options.addArguments("--start-maximized");
				options.addArguments(new String[] { "disable-infobars" });
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				options.addArguments("--allow-running-insecure-content");
				options.addArguments("ignore-certificate-errors");
//				options.addArguments("headless");
				options.addArguments("window-size=1920,1080");
				options.addArguments("no-sandbox");
//				options.addArguments("--disable-gpu");
				capability.setCapability(ChromeOptions.CAPABILITY, options);

				driver = new ChromeDriver(capability);
				wait = new WebDriverWait(driver, 90);
				driver.manage().window().maximize();

//				driver.navigate().to(environment);
			} else if (myBrowser.equalsIgnoreCase("firefox")) {
				/*
				 * FireFox driver i�in init i�lemleri burada olacak
				 */
			}
		}
	}

	/**
	 * Her test ba��nda ortak kullan�lan methodlar�n oldu�u class� ba�lat�r
	 * 
	 * @param environment : ortam bilgisi
	 * @param username    : login olan kullan�c� ismi
	 * @param password    : login olan kullan�c� �ifresi
	 */
	@BeforeTest
	@Parameters({ "environment", "username", "password" })
	public void initGenericMethods(String environment, String username, String password) {
		this.environment = environment;
	}

	/**
	 * Her test sonunda �a��r�l�r. Yarat�lan driver'� sonland�r�r.
	 */
	@AfterTest
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getDriver() {
		return this.driver;
	}
}
