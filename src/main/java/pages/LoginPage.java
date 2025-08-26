package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import base.DriverFactory;

import utils.AppLogger;
import utils.ElementUtil;

public class LoginPage {

     WebDriver driver;
     ElementUtil webelements = new ElementUtil(DriverFactory.getDriver());
     
     private String textBoxXpath = "//label[normalize-space(text())='%s']//following::input";
     private String buttonXpath = "//input[@type='submit' or @value='%s' or @name='%s']";
     private String linksXpath = "//a[normalize-space(text())='%s' or contains(text(),'%s') or contains(@href,'%s')]";
     private String welcomemessage = "//h5[normalize-space(text())='%s' or contains(text(),'%s') or contains(@href,'%s')]";
	
	public LoginPage(WebDriver driver) {
		this.driver = DriverFactory.getDriver();
		 PageFactory.initElements(driver, this);
	
		}
	
	
	public void insertUserName(String field,String value) {
		
			if(driver.getPageSource().contains("User Name:")) {
			String dynamicXpath = String.format(textBoxXpath, field);   // replaces %s with label text
	        WebElement element = driver.findElement(By.xpath(dynamicXpath));
	        element.sendKeys(value);
	        AppLogger.info("user enter user name successfully");
	        
	       	}else
	       	{
	       		AppLogger.info("Step failed");
	       	}
	}
	
	
	public void insertPassword(String field,String value) {
		
		if(driver.getPageSource().contains("Password:")) {
			String dynamicXpath = String.format(textBoxXpath, field);   // replaces %s with label text
	        WebElement element = driver.findElement(By.xpath(dynamicXpath));
	        element.sendKeys(value);
		
			}
	}
	
	
	
	public void clickSubmitButton(String buttonName) {
		
			if(buttonName.equals("submit")) {
			String dynamicXpath = String.format(buttonXpath, buttonName, buttonName);
		    WebElement submitBtton =  driver.findElement(By.xpath(dynamicXpath));
		    webelements.clickButton(submitBtton);
			}
	}
	
	
	
	
	public void verifyHomePage(String welcomeText) {
		
		if(welcomeText.equals("Welcome qatrainer")) {
		String dynamicTextXpath = String.format(welcomemessage, welcomeText, welcomeText,welcomeText);
		WebElement welcome =  driver.findElement(By.xpath(dynamicTextXpath));
		webelements.verifyText(welcome, welcomeText);
	    
		}
    }
	
	
	public void clickLogoutButton(String logout) {
		
		
		if(logout.equals("Logout")) {
			
		String dynamicLinksXpath = String.format(linksXpath, logout, logout,logout);
		WebElement logout1 =  driver.findElement(By.xpath(dynamicLinksXpath));
		webelements.clickButton(logout1);
		}
}
	
	
	
	
	
	

}
