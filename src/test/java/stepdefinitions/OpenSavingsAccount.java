package stepdefinitions;

import io.cucumber.java.en.*;
import base.DriverFactory;
import pages.OpenAccountPage;
import utils.ConfigReader;
import utils.screenshot;
import reports.ExtentReportManager;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class OpenSavingsAccount {

    WebDriver driver = DriverFactory.getDriver();
    OpenAccountPage accountPage = new OpenAccountPage(driver);

    @Given("user is on the account form page")
    public void user_is_on_form_page() {
        String formUrl = ConfigReader.getProperty("formPageUrl");
        driver.get(formUrl);
        ExtentReportManager.getInstance()
            .createTest("Navigate to Form Page")
            .log(Status.INFO, "Navigated to form page: " + formUrl);
    }

    @When("user enters {string} into the {string} field")
    public void enter_text(String value, String label) {
        accountPage.enterText(label, value);
        ExtentReportManager.getInstance()
            .createTest("Enter Text")
            .log(Status.INFO, "Entered '" + value + "' into '" + label + "' field");
    }

    @When("user enters {string} into the {string} date field")
    public void enter_date(String value, String label) {
        accountPage.enterDate(label, value);
        ExtentReportManager.getInstance()
            .createTest("Enter Date")
            .log(Status.INFO, "Entered date '" + value + "' into '" + label + "' field");
    }

    @When("user selects {string} from the {string} dropdown")
    public void select_dropdown(String option, String label) {
        accountPage.selectDropdown(label, option);
        ExtentReportManager.getInstance()
            .createTest("Select Dropdown")
            .log(Status.INFO, "Selected '" + option + "' from '" + label + "' dropdown");
    }

    @When("user clicks the {string} button")
    public void click_button(String label) {
        accountPage.clickButton(label);
        ExtentReportManager.getInstance()
            .createTest("Click Button")
            .log(Status.INFO, "Clicked '" + label + "' button");
    }

    @Then("form should be submitted successfully")
    public void form_should_be_submitted_successfully() {
        try {
            String screenshotPath = screenshot.captureScreenshot(driver, "FormSubmissionSuccess");

            ExtentReportManager.getInstance()
                .createTest("Form Submission")
                .log(Status.PASS, "Form submitted successfully")
                .info("Screenshot attached",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        } catch (Exception e) {
            ExtentReportManager.getInstance()
                .createTest("Form Submission")
                .log(Status.FAIL, "Form submission failed: " + e.getMessage());

            throw new RuntimeException("Form submission validation failed", e);
        }
    }
   
    }