
@tag
Feature: Error validation
 
  @Error
  Scenario Outline: Title of your scenario outline
  	Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

     Examples: 
      | name               | password |
      | elmoskito@coso.com | Pass123 |
