package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class OpenAccountPage {
    WebDriver driver;

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // Escape XPath string to handle apostrophes
    private String escapeXPath(String text) {
        if (text.contains("'")) {
            String[] parts = text.split("'");
            StringBuilder xpath = new StringBuilder("concat(");
            for (int i = 0; i < parts.length; i++) {
                xpath.append("'").append(parts[i]).append("'");
                if (i < parts.length - 1) {
                    xpath.append(", \"'\", ");
                }
            }
            xpath.append(")");
            return xpath.toString();
        } else {
            return "'" + text + "'";
        }
    }

    private By getInputByLabel(String labelText) {
        String safeLabel = escapeXPath(labelText);
        return By.xpath("//label[normalize-space(text())=" + safeLabel + "]/following-sibling::input");
    }

    private By getDateInputByLabel(String labelText) {
        String safeLabel = escapeXPath(labelText);
        return By.xpath("//label[normalize-space(text())=" + safeLabel + "]/following-sibling::input[@type='date']");
    }

    private By getDropdownByLabel(String labelText) {
        String safeLabel = escapeXPath(labelText);
        return By.xpath("//label[normalize-space(text())=" + safeLabel + "]/following-sibling::select");
    }

    private By getButtonByText(String buttonText) {
        String safeText = escapeXPath(buttonText);
        return By.xpath("//button[normalize-space(text())=" + safeText + "]");
    }

    // NEW: Get field locator for validation message check
    public By getFieldLocator(String labelText) {
        return getInputByLabel(labelText);
    }

    public void enterText(String label, String value) {
        driver.findElement(getInputByLabel(label)).sendKeys(value);
    }

    public void enterDate(String label, String date) {
        driver.findElement(getDateInputByLabel(label)).sendKeys(date);
    }

    public void selectDropdown(String label, String option) {
        Select dropdown = new Select(driver.findElement(getDropdownByLabel(label)));
        dropdown.selectByVisibleText(option);
    }

    public void clickButton(String label) {
        try {
            driver.findElement(getButtonByText(label)).click();
        } catch (NoSuchElementException e) {
            // Fallback: try input[type='submit'] with matching value
            try {
                driver.findElement(By.xpath("//input[@type='submit' and @value=" + escapeXPath(label) + "]")).click();
            } catch (NoSuchElementException ex) {
                throw new RuntimeException("Button with label '" + label + "' not found", ex);
            }
        }
    }

    public void verifySubmissionSuccess() {
        By successMsg = By.xpath("//*[contains(text(),'Application submitted') or contains(text(),'Thank you')]");
        if (!driver.findElement(successMsg).isDisplayed()) {
            throw new AssertionError("Form submission failed");
        }
    }
}
