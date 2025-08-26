package utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.DriverFactory;

public class ElementUtil {
	
	private  WebDriver driver;
    private final WebDriverWait wait;
    private  WaitUtils waitUtil = new WaitUtils(driver,10);
    private static final Logger logger = LogManager.getLogger(ElementUtil.class);
    

    // default constructor uses 10s wait
    public ElementUtil(WebDriver driver) {
        this(driver, Duration.ofSeconds(10));
    }
    
    
    public ElementUtil(WebDriver driver, Duration timeout) {
        if (driver == null) throw new IllegalArgumentException("WebDriver cannot be null");
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }
    
    private WebElement waitUntilVisible(By locator) throws Exception {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new Exception("Element not visible within timeout: " + locator, e);
        } catch (NoSuchElementException e) {
            throw new Exception("Element not found: " + locator, e);
        }
    }

    private WebElement waitUntilClickable(By locator) throws Throwable  {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new Exception("Element not clickable within timeout: " + locator, e);
        }
    }
    
    
    
    public boolean isTextBoxVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            System.out.println("Textbox not visible: " + e.getMessage());
            return false;
        }
    }

    /**
     * Waits until textbox is clickable (enabled)
     */
    public boolean isTextBoxEnabled(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return element.isEnabled();
        } catch (Exception e) {
            System.out.println("Textbox not enabled: " + e.getMessage());
            return false;
        }
    }

    /**
     * Validates if textbox contains text
     */
    public boolean hasText(WebElement element) {
        try {
            String value = element.getAttribute("value");
            return value != null && !value.trim().isEmpty();
        } catch (Exception e) {
            System.out.println("Textbox text check failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Clear and type text into textbox with validation
     */
    public void enterText(WebElement element, String text) {
        if (isTextBoxVisible(element) && isTextBoxEnabled(element)) {
            element.clear();
            element.sendKeys(text);
            System.out.println("Entered text: " + text);
            logger.info("Entered text: " + text);
        } else {
            throw new RuntimeException("Textbox is not ready for input");
        }
    }

    /**
     * Get placeholder (if present)
     */
    public String getPlaceholder(WebElement element) {
        return element.getAttribute("placeholder");
    }
    
    
    public boolean isEnabled(By locator) {
        try {
            WebElement ele = waitUntilVisible(locator);
            return ele.isEnabled();
        } catch (Exception e) {
            return false;
        }
    
    
    }
  
    
    public boolean clickWebElement(WebElement element, String methodName) {
        boolean flag = false;
        int retries = 2;

        try {
            // Ensure element is present before attempting
        	waitUtil.waitForClickability(element);

            for (int i = 0; i < retries; i++) {
                try {
                    // Preferred: normal click, fallback to JS
                    try {
                        element.click();
                    } catch (Exception e) {
                       e.printStackTrace();
//                    	ConcurrentEngines.getEngine().getWebActions().clickElementJS(element);
                    }
                    flag = true;
                    break;
                } catch (StaleElementReferenceException sere) {
                    if (i == retries - 1) {
                        System.err.println("[ERROR] StaleElementReferenceException in method: " + methodName);
                        throw sere;
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("[ERROR] Failed to click element in method: " + methodName);
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }


    
    public void clickButton(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            System.out.println("Clicked on button: " + element.getText());
        } catch (Exception e) {
            throw new RuntimeException("Failed to click button: " + e.getMessage());
        }
    }
    
    public void highlightAndClick(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; background: yellow;');", element);
            Thread.sleep(500);
            clickButton(element);
        } catch (Exception e) {
            throw new RuntimeException("Failed to highlight and click button: " + e.getMessage());
        }
    }
    
    
    //Highlighting element
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            // Save original style
            String originalStyle = element.getAttribute("style");

            // Apply highlight
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, "border: 2px solid red; background: yellow;");

            // Pause to see effect
            Thread.sleep(500);

            // Revert back to original style
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, originalStyle);
            logger.info("Element highlighted"+originalStyle);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
	 * To Verify text value
	 *
	 * @param element:Element to get actual text, textValue: Expected string value
	 * @Created on:
	 * @Modified on:
	 */
	public boolean verifyText(WebElement element, String textValue) {
		boolean flag = false;
		try {
			waitUtil.waitForPresence(element);
			String text = element.getText().trim();
			if (text.equals(textValue)) {
				flag = true;
				AppLogger.info("Text verified Successfully ." + text);
				return flag;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return flag;
	}

    
	
	public static String getCurrentDate(String format) {
        // Example format: "yyyy-MM-dd_HH-mm-ss"
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }
    

}
