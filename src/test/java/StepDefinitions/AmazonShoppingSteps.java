package StepDefinitions;

import PageObjects.AmazonShoppingPage;
import Utilities.BaseClass;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AmazonShoppingSteps extends BaseClass {
	AmazonShoppingPage amazonShop;

	@Given("^Launch the app$")
	public void launch_the_app() throws Throwable {
		driver=super.getAndroidDriver();
	}

	@When("^the user enters username and password$")
	public void the_user_enters_username_and_password() throws Throwable {
		amazonShop=new AmazonShoppingPage(driver);
		
	}

	@Then("^click login$")
	public void click_login() throws Throwable {
		amazonShop.Login();
	}

	@Then("^Login should be success$")
	public void login_should_be_success() throws Throwable {
		amazonShop.loginVerify();
	}

@When("^the user search the product by entering product name$")
public void the_user_search_the_product_by_entering_product_name() throws Throwable {
	amazonShop=new AmazonShoppingPage(driver); 
	amazonShop.searchProduct();
}


@Then("^Get the details of the product$")
public void get_the_details_of_the_product() throws Throwable {
	amazonShop.getProductDetails();
	
}
@Then("^compare the price of products details page with checkout page$")
public void compare_the_price_of_products_details_page_with_checkout_page() throws Throwable {
	amazonShop.getProductPrice();
}

}
