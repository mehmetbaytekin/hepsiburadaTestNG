/**
 * 
 */
package hepsiBuradaPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import base.Base;

/**
 * @author mehmet.baytekin
 *
 */
public class BaseFunctions extends Base {

	private WebDriver driver;
	private WebDriverWait wait;

	public BaseFunctions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 45);
	}

	public void waitLoad() throws InterruptedException {
		Thread.sleep(500);

		Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {

			public boolean apply(WebDriver input) {
				return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
			}

		};
	}

	public void clicke(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		waitLoad();
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void write(WebElement element, String text) throws InterruptedException {
		Thread.sleep(1000);
		waitLoad();
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(text);
	}

	public void scrollDown() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollUp() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,-document.body.scrollHeight)");

	}

	public void waitElement(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		waitLoad();
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void ScrollElement(WebElement element, WebDriver driver) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(element));
		waitLoad();
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);

	}

	public void xpathMessageCheck(WebElement element, String mesaj, WebDriver driver) throws Exception {

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			waitLoad();
			wait.until(ExpectedConditions.textToBePresentInElement(element, mesaj));
			System.out.println(mesaj + " " + " görüldü");

		}

		catch (final WebDriverException exception) {

			waitLoad();
			throw new WebDriverException(exception.getMessage());

		}

		catch (final Exception exception) {
			Thread.sleep(1500);
			waitLoad();
			System.out.println("Menu elemani gorulmemistir.");
			driver.quit();
			try {
				throw new Exception(exception.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
