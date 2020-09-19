#Feature
@AmazonShopping
Feature: Shopping the Tv in amazon app

@Login
Scenario: Login into amazon app
Given Launch the app
When the user enters username and password
Then click login
And Login should be success

Scenario: Searching the product
When the user search the product by entering product name
Then Get the details of the product
And compare the price of products details page with checkout page