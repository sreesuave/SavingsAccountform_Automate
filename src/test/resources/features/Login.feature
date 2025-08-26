Feature: Login functionality

  As a registered user
  I want to login into the application
  So that I can access my account

 
  Scenario: Successful login with valid credentials
    Given user navigates to the home page
    Then user see the login page under home page
    When user enters "qatrainer" into the "User Name:" edit filed under login page
    When user enters "admin123" into the "Password:" edit filed under login page
    And user clicks on the "submit" button under login page
    Then user should be navigated to the "Welcome qatrainer" page 
    And user clicks on the "Logout" button under successful user login page
    


  
  
