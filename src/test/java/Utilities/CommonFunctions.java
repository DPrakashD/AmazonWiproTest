package Utilities;

import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions {
	public static void waitForPageLoad(WebDriver driver) {

		try
		{
			Wait<WebDriver> wait = new WebDriverWait(driver, 120);
			wait.until(new Function<WebDriver, Boolean>() {
	 			public Boolean apply(WebDriver driver) {
					System.out.println("Current Window State       : "
							+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
					return String
							.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
							.equals("complete");
				}
			});
		}
		catch(Exception e)
		{
			System.out.println("Wait for page Load error "+ e);

		}
	}
}
