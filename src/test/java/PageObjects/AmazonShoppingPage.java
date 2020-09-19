package PageObjects;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilities.BaseClass;
import Utilities.ConfigFileReader;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AmazonShoppingPage extends BaseClass {
	Wait<WebDriver> wait;

	public AmazonShoppingPage(WebDriver driver) throws MalformedURLException {
		driver = super.getAndroidDriver();
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
	}

	@FindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/sign_in_button']")
	public WebElement loginButtonExistingCustomer;

	@FindBy(xpath = "//*[@class='android.widget.EditText' and @resource-id='ap_email_login']")
	public WebElement loginEmail;

	@FindBy(xpath = "//*[@class='android.widget.Button' and @resource-id='continue']")
	public WebElement continueBtn;

	@FindBy(xpath = "//*[@class='android.widget.EditText' and @resource-id='ap_password']")
	public WebElement loginPassword;

	@FindBy(xpath = "//*[@class='android.widget.Button' and @resource-id='signInSubmit']")
	public WebElement SignInBtn;

	@FindBy(xpath = "//*[@resource-id='atfRedesign_priceblock_priceToPay']/android.widget.EditText")
	public WebElement price;

	@FindBy(xpath = "//*[@resource-id='add-to-cart-button']")
	public WebElement addToCart;

	@FindBy(xpath = "//*[@class='android.widget.TextView' and @index='10']")
	public WebElement checkoutPrice;

	@FindBy(xpath = "//*[@class='android.widget.ImageView' and @index='0']")
	public WebElement burgerIcon;

	@FindBy(xpath = "//*[@text='Proceed to checkout']")
	public WebElement proceedToCheckout;

	@FindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/loc_ux_addresses_list']")
	public WebElement pincode;

	@FindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']")
	public WebElement searchProducts;

	@FindBy(xpath = "//*[@class='android.view.ViewGroup' and @index='0']")
	public WebElement productName;

	@FindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/item_title' and @index='0']")
	public List<WebElement> productDetails;

	/***amazon login page***/
	public void Login() throws InterruptedException {

		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOf(loginButtonExistingCustomer));
		loginButtonExistingCustomer.click();
		System.out.println("Login existing customer button was clicked");

		new WebDriverWait(driver, 100).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//*[@class='android.widget.EditText' and @resource-id='ap_email_login']")));
		driver.findElement(By.xpath("//*[@class='android.widget.EditText' and @resource-id='ap_email_login']"))
				.sendKeys(ConfigFileReader.getConfigFileReader().getUsername());
		System.out.println("Username was entered");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(continueBtn));
		continueBtn.click();
		System.out.println("Continue buttonn was clicked");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(loginPassword));
		loginPassword.sendKeys(ConfigFileReader.getConfigFileReader().getPassword());
		System.out.println("Password was entered");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(SignInBtn));
		SignInBtn.click();
		System.out.println("SignIn button clicked");
	}
	
      /***Verify the user has loggedin or not ***/
	public void loginVerify() throws InterruptedException {

		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOf(burgerIcon));
		Assert.assertTrue(burgerIcon.isDisplayed());
		System.out.println("Login was success");
	}

	/***Search the product using keyword***/
	public void searchProduct() throws InterruptedException {

		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOf(searchProducts));
		searchProducts.click();

		searchProducts.sendKeys(ConfigFileReader.getConfigFileReader().getProductName());
		System.out.println("Search box value entered");
		// Thread.sleep(5000);
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		System.out.println("Product displayed");

	}

	/*** Get the details of the product***/
	public void getProductDetails() throws InterruptedException {
		Thread.sleep(5000);

		scrollDown();

		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfAllElements(productDetails));
		System.out.println("Total products in currnet page:" + productDetails.size());

		/*
		 * Random rnd = new Random(); int rndInt = rnd.nextInt(productDetails.size());
		 */
		Thread.sleep(2000);
		String randomProduct = productDetails.get(getRandomIntegerBetweenRange(1, productDetails.size())).getText();
		System.out.println("Random product is:" + randomProduct);
		Thread.sleep(2000);
		productDetails.get(getRandomIntegerBetweenRange(1, productDetails.size())).click();
		System.out.println("Product clicked successfuly");
	}

	/*** Generating the random number between 1 to product count ***/
	public static int getRandomIntegerBetweenRange(double min, double max) {
		int x = (int) ((int) (Math.random() * ((max - min) + 1)) + min);
		return 3;
	}

	/*** Get the price in product details page and checkout page. Also compare the both price are matched or not ***/ 
	@SuppressWarnings("unchecked")
	public void getProductPrice() throws Exception {

		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOf(pincode));
		Thread.sleep(1000);
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(3000);

		scrollDown();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(price));
		String prodPrice = price.getText();
		System.out.println("Selected product price is:" + prodPrice);
		prodPrice = prodPrice.substring(prodPrice.lastIndexOf("rupees" + " ") + 7);
		System.out.println("Product price=" + prodPrice);
		Thread.sleep(2000);
		// scrollDown();
		String text = "Add to Cart";
		WebElement cartElement = driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).setAsVerticalList().scrollIntoView("
						+ "new UiSelector().text(\"" + text + "\"));"));
		wait.until(ExpectedConditions.visibilityOf(addToCart));
		addToCart.click();
		System.out.println("Add cart button clicked");
		wait.until(ExpectedConditions.visibilityOf(checkoutPrice));
		String checkoutAmount = checkoutPrice.getText();
		System.out.println("Checkout Price is:" + checkoutAmount);
		checkoutAmount = checkoutAmount.substring(checkoutAmount.lastIndexOf("₹" + " ") + 3,checkoutAmount.lastIndexOf("."));
		System.out.println("Product price=" + checkoutAmount);
		wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
		proceedToCheckout.click();
		System.out.println("Checkout button clicked");
		Assert.assertEquals(prodPrice, checkoutAmount);
		System.out.println("Product details page's product price matched with checkout pages's price");
	}

	public void scrollDown() {
		int pressX = driver.manage().window().getSize().width / 2;
		int bottomY = driver.manage().window().getSize().height * 4 / 5;
		int topY = driver.manage().window().getSize().height / 8;
		scroll(pressX, bottomY, pressX, topY);
	}

	private void scroll(int fromX, int fromY, int toX, int toY) {
		TouchAction touchAction = new TouchAction(driver);
		touchAction.longPress(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release().perform();
	}

}
