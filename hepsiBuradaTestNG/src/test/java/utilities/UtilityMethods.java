/**
 * 
 */
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import base.Base;

/**
 * @author mehmet.baytekin
 *
 */
public class UtilityMethods {

	private WebDriver driver;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy_HH-mm-ss");
	private Properties prop;

	public void setPath() throws FileNotFoundException, IOException {
		String path = System.getProperty("user.dir");
		path = path.replace("\\", "/");
		String now = formatter.format(LocalDateTime.now());
		path = path + "/test-output/screenshots/RUN_AT_" + now + "/";
		prop = new Properties();
		prop.put("path", path);
		prop.put("ssPath", path);
		FileWriter writer = new FileWriter("path.properties");
		prop.store(writer, "Path Settings");
	}

	public String getPath() throws FileNotFoundException, IOException {

		prop = new Properties();
		prop.load(new FileInputStream("path.properties"));
		String path = prop.getProperty("path");
		return path;
	}

	public void getSS(ITestResult Result, String path) {
		Object currentClass = Result.getInstance();
		driver = ((Base) currentClass).getDriver();
		String fullPath = null;
		try {
			fullPath = getPath() + path + Result.getName() + ".jpg";
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (driver != null) {

			File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			System.out.println(" File f");
			try {
				FileUtils.copyFile(f, new File(fullPath));
				System.out.println(Result.getName() + " SS Aldý");
			} catch (IOException e) {
				System.out.println(Result.getName() + " SS alamadý, catch!");
				e.printStackTrace();
			}

			// etc.
			System.out.println("SS OK");
			Reporter.log("<a href=\"" + fullPath + "\"><img src=data:image/jpg;base64," + getBase64(fullPath)
					+ " style=width:640px;height:480px;/>" + "1.jpg" + "</a><br/>");
		}

	}

	private String getBase64(String imagePath) {
		String base64Image = "";
		File file = new File(imagePath);
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.getEncoder().encodeToString(imageData);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		return base64Image;
	}

	public String getPath(int type) {

		String path = null;

		if (type == 1) {

			path = "Successes/_Success_At_" + formatter.format(LocalDateTime.now()) + "_";

			return path;
		} else if (type == 2) {

			path = "Fails/_Fail_AT_" + formatter.format(LocalDateTime.now()) + "_";

			return path;
		} else if (type == 3) {

			path = "Skips/_Skip_AT_" + formatter.format(LocalDateTime.now()) + "_";

			return path;
		}
		return path;
	}

}