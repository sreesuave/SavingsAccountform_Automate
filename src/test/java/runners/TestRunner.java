package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "D:\\SeleniumJavaProject\\src\\test\\resources\\features\\OpenSavingsAccount.feature", // Remove "./" for clarity
        glue = {"stepdefinitions","hooks"},
//        tags = "@RegressionTest or @SmokeTest", // Space before "@" is unnecessary
        plugin = {
                "pretty", 
                "html:target/cucumber-reports/Cucumber.html", 
                "json:target/cucumber-reports/Cucumber.json", 
                "junit:target/cucumber-reports/Cucumber.xml", 
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        dryRun = false,
        monochrome = true // Set true for better console output readability
)

public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
	return super.scenarios();
	}
}
