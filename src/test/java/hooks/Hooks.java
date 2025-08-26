package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.DriverFactory;
import io.cucumber.java.*;
import reports.ExtentReportManager;
import utils.ConfigReader;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private static ExtentReports extent;
    private static ExtentTest scenarioTest;

    @BeforeAll
    public static void before_all() {
        extent = ExtentReportManager.getInstance();
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        scenarioTest = extent.createTest(scenario.getName());

        String browser = ConfigReader.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }
        DriverFactory.initDriver(browser);
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) return;

        try {
            String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/"
                    + scenario.getName().replaceAll(" ", "_") + ".png";

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(srcFile, destFile);

            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", scenario.getName());

            if (scenario.isFailed()) {
                scenarioTest.log(Status.FAIL, "Step failed").addScreenCaptureFromPath(screenshotPath);
            } else {
                scenarioTest.log(Status.PASS, "Step passed").addScreenCaptureFromPath(screenshotPath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            scenarioTest.log(Status.FAIL, "Scenario failed: " + scenario.getName());
        } else {
            scenarioTest.log(Status.PASS, "Scenario passed: " + scenario.getName());
        }

        DriverFactory.quitDriver();
    }

    @AfterAll
    public static void after_all() {
        extent.flush();
    }
}
