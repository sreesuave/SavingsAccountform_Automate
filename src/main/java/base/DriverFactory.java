package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class DriverFactory {

    // ThreadLocal to handle parallel execution safely
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browserName
     * @param browser Browser name (chrome/firefox/edge)
     * @return WebDriver instance
     */
    public static WebDriver initDriver(String browser) {

        System.out.println("Launching browser: " + browser);

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
        	ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");  // open in max window
            options.addArguments("--incognito");
            options.addArguments("--remote-allow-origins=*");
            tlDriver.set(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase("firefox")) {
        	WebDriverManager.firefoxdriver().setup();
        	tlDriver.set(new FirefoxDriver());

        } else if (browser.equalsIgnoreCase("edge")) {
            tlDriver.set(new EdgeDriver());

        } else {
            throw new RuntimeException("Invalid browser: " + browser);
        }

        // Common setup
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return getDriver();
    }

    /**
     * Get WebDriver instance (thread-safe)
     */
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * Quit WebDriver instance
     */
    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }
}
