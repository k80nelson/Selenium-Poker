package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Web ca.carleton.poker.driver factory.
 *
 * Created by Cheryl  on 10/6/2015.
 */
public class WebDriverFactory {

    public WebDriverFactory() {}

    public WebDriver getWebDriver() {
        final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        capabilities.setJavascriptEnabled(true);
      

        final WebDriver chrome = new ChromeDriver(capabilities);
        chrome.get("http://www.google.com/xhtml");
        chrome.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
       
       
        return chrome;
    }
}
