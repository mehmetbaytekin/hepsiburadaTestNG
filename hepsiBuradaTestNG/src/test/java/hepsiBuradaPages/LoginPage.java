/**
 * 
 */
package hepsiBuradaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author mehmet.baytekin
 *
 */
public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private BaseFunctions bf;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 90);
		bf = new BaseFunctions(driver);
	}

	@FindAll({ @FindBy(xpath = "//*[text()='Giriþ yap']/following::input[1]"),
			@FindBy(xpath = "//*[contains(@id,'txtUserName')]") })
	private WebElement usernameInput;

	@FindAll({ @FindBy(xpath = "//*[text()='Password']/following::input[2]"),
			@FindBy(xpath = "//*[contains(@id,'txtPassword')]") })
	private WebElement passwordInput;

	@FindAll({ @FindBy(xpath = "(//*[text()='Giriþ yap'])[2]"),
			@FindBy(xpath = "//*[text()='Þifremi unuttum']/preceding::button[1]") })
	private WebElement loginButton;
	
	@FindBy(xpath = "(//*[text()='Giriþ Yap'])[2]")
	private WebElement loginButtonScreen;
//------------------------------------------methods----------------------------------------------------------------

	public void login(String username, String password, String environment) throws InterruptedException {

		driver.navigate().to(environment);
		try {
			WebElement hover = driver.findElement(By.xpath("(//*[text()='Giriþ Yap'])[1]"));
		    Actions action = new Actions(driver);
		    action.moveToElement(hover).build().perform();
			bf.clicke(loginButtonScreen);
			Thread.sleep(2000);
			bf.write(usernameInput, username);
			bf.write(passwordInput, password);
			bf.clicke(loginButton);
		} catch (Exception e) {
			System.out.println("login ekraný fail aldý, sonraki adýma geçti.");
		}

	}

}
