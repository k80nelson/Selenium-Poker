package selenium;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;

import config.SeleniumTest;
import selenium.page.IndexPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.concurrent.TimeUnit;

/**
 * Created by Cheryl 
 */
@SeleniumTest
public class GameTest extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;

    
    @Before
    public void setup(){
    	this.indexPage.chrome = new ChromeDriver();
    	this.indexPage.chrome.get("http://localhost:8080");
    	this.indexPage.connect.click();
    	this.indexPage.open.click(); // defaults is 1 player
    	this.indexPage.start.click();
    	
    	
    }
    
    @After
    public void tearDown(){
    	this.indexPage.disconnect.click();
    	this.indexPage.chrome.quit();
    }
    @Test
    public void processAI(){
    	// tests that the AI are processed first
        this.indexPage.waitForDisplayed(this.indexPage.stay).isEnabled();
        assertThat(this.indexPage.hasText("All AI players have made their choices"), is(true));
    }

    @Test
    public void canUseStayOption(){
    	// checks that the use can choose to stay
        this.indexPage.waitForDisplayed(this.indexPage.stay).isEnabled();
        this.indexPage.stay.click();
        assertThat(this.indexPage.hasText("made their turn and STAY"), is(true));
    }
    
    @Test
    public void canUseHitOption() {
    	// checks that the use can choose to stay
        this.indexPage.waitForDisplayed(this.indexPage.hit).isEnabled();
        this.indexPage.hit.click();
        assertThat(this.indexPage.hasText("You decided to HIT. Sending to server"), is(true));
    }
    
    
    @Test
    public void canImproveCards() {
    	// checks that the use can choose to stay
        assertThat(this.indexPage.waitForDisplayed(this.indexPage.hit).isEnabled(), is(true));
        this.indexPage.hit.click();
        assertThat(this.indexPage.waitForDisplayed(this.indexPage.done).isEnabled(), is(true));
        assertThat(this.indexPage.hasText("Select all cards you want to discard."), is(true));
        this.indexPage.card1.click();
        this.indexPage.card3.click();
        this.indexPage.done.click();
        assertThat(this.indexPage.hasText("You decided to improve card"), is(true));
    }
    
    
   
    
    
    
    
    
}
