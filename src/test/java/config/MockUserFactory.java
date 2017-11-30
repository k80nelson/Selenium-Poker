package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Create a user
 * Created by Cheryl.
 */
@Service
public class MockUserFactory {

    public WebDriver getSecondUser(final String location) {
        return this.buildNewUser(location);
    }

    private WebDriver buildNewUser(final String location) {
        final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setJavascriptEnabled(true);

        final WebDriver chromeDriver = new ChromeDriver(capabilities);
        chromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        chromeDriver.get(location);
        
        
        return chromeDriver;
    }
}
