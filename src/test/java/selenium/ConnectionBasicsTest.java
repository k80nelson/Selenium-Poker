package selenium;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import selenium.page.IndexPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test the ability to connect and disconnect to the game.
 * Created by Cheryl 
 */
public class ConnectionBasicsTest extends AbstractSeleniumTest{

    @Autowired
    private IndexPage indexPage;

    @Test
    public void canConnect() {
    	this.indexPage.chrome = new ChromeDriver();
    	this.indexPage.chrome.get("http://localhost:8080");
    	this.indexPage.connect.click();
        assertThat(this.indexPage.hasText("Successfully connected to the game with unique "), is(true));
        assertThat(this.indexPage.hasText("You have been designated the admin for this game."), is(true));
        this.indexPage.disconnect.click();
        assertThat(this.indexPage.hasText("Connection closed"), is(true));
        this.indexPage.disconnect.click();
        this.indexPage.chrome.quit();
    }

    @Test
    public void canOpenLobby() {
       	this.indexPage.chrome = new ChromeDriver();
    	this.indexPage.chrome.get("http://localhost:8080");
    	this.indexPage.connect.click();
    	this.indexPage.open.click();
    	assertThat(this.indexPage.hasText("Opening the lobby with specified settings."), is(true));
    	this.indexPage.disconnect.click();
    	this.indexPage.chrome.quit();
    	
    
    }

    @Test
    public void canConenctTwoPlayers() {
    	// connect
       	this.indexPage.chrome = new ChromeDriver();
    	this.indexPage.chrome.get("http://localhost:8080");
    	this.indexPage.connect.click();
    	this.indexPage.setNumberPlayers(2);
    	this.indexPage.open.click();
    	//second connect
    	ChromeDriver second = new ChromeDriver();
    	second.get("http://localhost:8080");
    	assertThat(this.indexPage.hasText("Successfully connected to the game with unique id"), is(true));
    	assertThat(this.indexPage.start.isEnabled(), is(true));
    	// quit
    	second.quit();
    	this.indexPage.disconnect.click(); 
    	
    	
    }
    @Test
    public void canConenctMultiplePlayers() {
    	// connect 
    	this.indexPage.chrome = new ChromeDriver();
    	this.indexPage.chrome.get("http://localhost:8080");
    	this.indexPage.connect.click();
    	this.indexPage.setNumberPlayers(3);
    	this.indexPage.open.click();
    	
    	// connect second
    	ChromeDriver second = new ChromeDriver();
    	assertThat(this.indexPage.hasText("Successfully connected to the game with unique id "), is(true));
    
    	// connect third
    	ChromeDriver third =  new ChromeDriver();
    	assertThat(this.indexPage.hasText("Successfully connected to the game with unique id "), is(true));
    	assertThat(this.indexPage.start.isEnabled(), is(true));
    	
    	second.quit();
    	third.quit();
    	this.indexPage.disconnect.click();  
    	this.indexPage.chrome.quit();
    	
    }
    @Test
    public void canStartGame() {
    	this.indexPage.chrome = new ChromeDriver();
       this.indexPage.connect.click();
       this.indexPage.open.click();
       this.indexPage.start.click();
       assertThat(this.indexPage.hasText("The game has started! Please wait for your turn."), is(true));
       this.indexPage.disconnect.click();
       this.indexPage.chrome.quit();
    }
    
    
    
}
