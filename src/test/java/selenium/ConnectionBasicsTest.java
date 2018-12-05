package selenium;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import config.SeleniumTest;
import selenium.page.IndexPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

/**
 * Test the ability to connect and disconnect to the game.
 * 
 */
@SeleniumTest
public class ConnectionBasicsTest extends AbstractSeleniumTest{

    @Autowired
    private IndexPage indexPage;
    
    @Test
    public void canConnect() {
    
    	this.indexPage.connect.click();
        assertThat(this.indexPage.hasText("Successfully connected to the game with unique "), is(true));
        assertThat(this.indexPage.hasText("You have been designated the admin for this game."), is(true));
    
        this.indexPage.disconnect.click();
        assertThat(this.indexPage.hasText("Connection closed"), is(true));
    
        
    }
    

    @Test
    public void canOpenLobby() {
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	//1: missing assertThat
    	 this.indexPage.disconnect.click(); 
    }
 
    @Test
    public void canConenctTwoPlayers() {
    	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.delay(2);
    	this.indexPage.setNumberPlayers(2);
    	
    	assertTrue(this.indexPage.hasText("Successfully connected to the game with unique id "+ this.indexPage.getPlayerUID()));
    	
    	this.delay(2);
    	this.indexPage.open.click();
    	
    	
    	ChromeDriver second = (ChromeDriver) this.quickConnectAnotherUser();
    	this.delay(2);
    	
    	WebElement console = second.findElement(By.id("consoleText"));
    	String UID = console.getText().replace("Console (UID: ", "").replace(")", "").trim();
    	
    	assertTrue(this.indexPage.hasText(UID + " has connected to the game."));
    	assertThat(this.indexPage.start.isEnabled(), is(true));
    	
    	this.disconnectSecondUser(second);
    	this.indexPage.disconnect.click();

    }
    
    @Test
    public void canConenctMultiplePlayers() {
    	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.delay(2);
    	this.indexPage.setNumberPlayers(3);
    	
    	assertTrue(this.indexPage.hasText("Successfully connected to the game with unique id "+ this.indexPage.getPlayerUID()));
    	
    	this.delay(2);
    	this.indexPage.open.click();
    	
    	
    	ChromeDriver second = (ChromeDriver) this.quickConnectAnotherUser();
    	this.delay(2);
    	
    	WebElement console = second.findElement(By.id("consoleText"));
    	String UID = console.getText().replace("Console (UID: ", "").replace(")", "").trim();
    	
    	assertTrue(this.indexPage.hasText(UID + " has connected to the game."));
    	assertThat(this.indexPage.start.isEnabled(), is(false));
    	
    	ChromeDriver third = (ChromeDriver) this.quickConnectAnotherUser();
    	this.delay(2);
    	
    	WebElement console2 = third.findElement(By.id("consoleText"));
    	String UID2 = console2.getText().replace("Console (UID: ", "").replace(")", "").trim();
    	
    	assertTrue(this.indexPage.hasText(UID2 + " has connected to the game."));
    	assertThat(this.indexPage.start.isEnabled(), is(true));
    	
    	this.disconnectSecondUser(second);
    	this.disconnectSecondUser(third);
    	this.indexPage.disconnect.click();
    	
    	// connect 
    	//5: missing code          	
    	// connect second
    	//6: missing code         
    	// connect third then disconnect
    	//7: missing code        
 }
    

    @Test
    public void canStartGame() {
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	this.indexPage.start.click();
    	//8: missing assertThat
    	this.indexPage.disconnect.click();
    }
    
   
    
}
