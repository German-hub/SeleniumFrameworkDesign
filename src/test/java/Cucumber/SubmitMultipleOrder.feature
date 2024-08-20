
@tag
Feature: Purchase the order with multiple products from Ecommerce Website

  Background:
  Given I landed on Ecommerce Page
  
  @SubmitOrder
  Scenario Outline: Positive Test of Submitting the order
  
    Given Logged in with username <name> and password <password>
    When I add multiple products <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name               | password | productName |
      | elmoskito@coso.com | Pass123! | ZARA COAT 3, ADIDAS ORIGINAL, IPHONE 13 PRO |