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
 * 
 */
@SeleniumTest
public class GameTest extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;
    
    @Before
    public void setup() {
    	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	this.waitForDisplayed(this.indexPage.stay);
    }
    
    @After
    public void tearDown() {
    	this.indexPage.disconnect.click();
    }

    @Test
    public void processAI(){
    	//connect
    	//9: missing code  including check all AI players have made their choices   
    	
    	assertThat(this.indexPage.stay.isEnabled(), is(true));
        assertThat(this.indexPage.hasText("All AI players have made their choices"), is(true));
    }
 
    

 
	@Test
    public void canUseStayOption(){
	//10: missing code for connect then selecting stay then disconnect
		assertThat(this.indexPage.stay.isEnabled(), is(true));
		this.indexPage.stay.click();
		this.delay(1);
        assertThat(this.indexPage.hasText("You decided to STAY"), is(true));
    }


    @Test
    public void canUseHitOption() {
    	//11: missing code similar to 11    
    	assertThat(this.indexPage.stay.isEnabled(), is(true));
    	this.indexPage.hit.click();
    	this.waitForDisplayed(this.indexPage.done).isEnabled();
        this.indexPage.done.click();
    	
        assertThat(this.indexPage.hasText("You decided to HIT"), is(true));
        
    }
    
    
 
    @Test
    public void canImproveCards(){
    	//12: missing code for connect, improve and disconnect     
    	assertThat(this.indexPage.stay.isEnabled(), is(true));
    	this.indexPage.hit.click();
    	this.waitForDisplayed(this.indexPage.done).isEnabled();
    	this.indexPage.card2.click();
        this.indexPage.card3.click();
        this.indexPage.done.click();
    	
        assertThat(this.indexPage.hasText("You decided to HIT"), is(true));
        assertThat(this.indexPage.hasText("You decided to improve card"), is(true));
    }
    
    
   
  
    
    
    
    
}
