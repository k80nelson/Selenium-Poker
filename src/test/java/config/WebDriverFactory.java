package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * created by cheryl 
 */
public class WebDriverFactory {

    public WebDriverFactory() {}

    public WebDriver getWebDriver() {
        final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setJavascriptEnabled(true);

        final ChromeDriver chrome = new ChromeDriver(capabilities);
        chrome.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return chrome;
    }
}
