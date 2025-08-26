Feature: Open Savings Account Form
  As a user
  I want to open a savings account
  So that I can submit my application successfully

  Scenario: Successful submission of savings account application
    Given user is on the "Open Savings Account" page
    When user enters full name "John Doe" under Open Savings Account
    And user enters father name "Richard Doe" under Open Savings Account
    And user enters occupation "Software Engineer" under Open Savings Account
    And user enters company name "TechCorp Ltd" under Open Savings Account
    And user enters date of birth "15-08-1995" under Open Savings Account
    And user enters email address "johndoe@example.com" under Open Savings Account
    And user enters mobile number "9876543210" under Open Savings Account
    And user enters residential address "123, Elm Street, New York" under Open Savings Account
    And user enters ID proof "Passport" under Open Savings Account
    And user selects account type "Savings" under Open Savings Account
    And user enters initial deposit amount "500" under Open Savings Account
    And user clicks on Submit Application button under Open Savings Account
    Then the application should be submitted successfully under Open Savings Account
    And a confirmation message should be displayed under Open Savings Account
