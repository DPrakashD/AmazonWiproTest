package Utilities;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.AmazonShoppingPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;



public class BaseClass {

	//public static WebDriver driver;
	public static AppiumDriver driver;
	public static WebDriverWait wait;


	public AppiumDriver getAndroidDriver() throws MalformedURLException
	{
 		if(driver==null)
		{
		DesiredCapabilities Capabilities = new DesiredCapabilities ();
		Capabilities.setCapability("deviceName", ConfigFileReader.getConfigFileReader().getDeviceName());
		Capabilities.setCapability("platformName", "Android");
		Capabilities.setCapability("appPackage",ConfigFileReader.getConfigFileReader().getPackageName());
		Capabilities.setCapability("appActivity", ConfigFileReader.getConfigFileReader().getAppActivity());
		//Capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);

	    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), Capabilities);

		System.out.println("Initialize: "+driver);
		
		PageFactory.initElements(driver, AmazonShoppingPage.class);
		return driver;
		}
		else
		{
			return driver;
		
}
	}
}
