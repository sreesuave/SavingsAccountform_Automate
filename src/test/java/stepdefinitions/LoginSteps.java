package stepdefinitions;

import io.cucumber.java.en.*;
import pages.LoginPage;
import utils.AppLogger;
import utils.ConfigReader;
import base.DriverFactory;

public class LoginSteps {

    public LoginPage loginPage ;
    
    ConfigReader readProp = new ConfigReader();
	 
  

    @Given("user navigates to the home page")
    public void user_navigates_to_the_home_page() {
    		DriverFactory.initDriver("chrome");
    	    DriverFactory.getDriver().get(readProp.getProperty("browserurl")); // replace with actual URL
    	    AppLogger.info("Launching browser");
    	    loginPage = new LoginPage(DriverFactory.getDriver());
    	    
    }

   

    @When("user enters {string} into the {string} edit filed under login page")
    public void user_enters_in_edit_filed_under_login_page(String value, String field) throws InterruptedException {
        
    	loginPage.insertUserName(field, value);
  
}
    
    
    @Then("user see the login page under home page")
    public void user_see_the_login_page_under_home_page() {
    	
    }

    @When("user clicks on the {string} button under login page")
    public void user_clicks_on_the_button_under_login_page(String button)  {
    	loginPage.clickSubmitButton(button);
    	
    }

    @Then("user should be navigated to the {string} page")
    public void user_should_be_navigated_to_the_page(String welcomeMessage) {
    	loginPage.verifyHomePage(welcomeMessage);
    }

   
    @Then("user clicks on the {string} button under successful user login page")
    public void user_clicks_on_the_button_under_successful_user_login_page(String logout) throws InterruptedException {
    	
    	loginPage.clickLogoutButton(logout);
    }

    
    
    
    
    
    
    
    
    

}