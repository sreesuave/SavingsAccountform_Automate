Feature: Open Savings Account Form Submission

  Scenario: User fills out and submits the account form
    Given user is on the account form page
    When user enters "John Doe" into the "Full Name" field
    And user enters "Robert Doe" into the "Father Name" field
    And user enters "Engineer" into the "Occupation" field
    And user enters "TechCorp" into the "Company Name" field
    And user enters "1990-01-01" into the "Date of Birth" field
    And user enters "john@example.com" into the "Email Address" field
    And user enters "9876543210" into the "Mobile Number" field
    And user enters "123 Main Street" into the "Residential Address" field
    And user enters "Passport" into the "ID Proof (e.g., Passport/Driver's License)" field
    And user selects "Regular Savings" from the "Account Type" dropdown
    And user enters "5000" into the "Initial Deposit Amount ($)" field
    
    And user clicks the "Submit Application" button
    
    
   
