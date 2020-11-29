/**
 * 
 */
package hepsiBuradaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author mehmet.baytekin
 *
 */
public class ProductPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private BaseFunctions bf;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 90);
		bf = new BaseFunctions(driver);
	}

	@FindBy(xpath = "//*[text()='ARA']/preceding::input[1]")
	private WebElement productSearch;

	@FindBy(xpath = "//*[text()='ARA']")
	private WebElement searchButton;

	@FindBy(xpath = "//*[text()='En y�ksek fiyat']/following::li[1]")
	private WebElement firstProduct;

	@FindBy(xpath = "(//*[contains(text(),'Taksit Se�enekleri')])/preceding::td[1]")
	private WebElement rating;

	@FindBy(xpath = "(//*[text()='Bu de�erlendirme faydal� m�?'])[1]/following::div[3]")
	private WebElement like;
	
	@FindBy(xpath = "(//*[text()='Te�ekk�r Ederiz.'])[1]")
	private WebElement message;

	public void urunArama(String searchValue) throws InterruptedException {
		bf.write(productSearch, searchValue);
		bf.clicke(searchButton);
		Actions actions = new Actions(driver);
		actions.moveToElement(firstProduct);
		bf.clicke(firstProduct);

		try {
			bf.ScrollElement(rating, driver);
			bf.clicke(rating);
			try {
				bf.clicke(like);
				bf.xpathMessageCheck(message, "Te�ekk�r Ederiz.", driver);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Zaten Be�enilmi� Bu Yorum...");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("yorum bulunamad�.");
		}
	}
}