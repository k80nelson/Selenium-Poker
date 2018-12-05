package selenium;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;

import ca.carleton.poker.game.entity.card.Card;
import ca.carleton.poker.game.entity.card.Hand;
import ca.carleton.poker.game.entity.card.Rank;
import ca.carleton.poker.game.entity.card.Suit;
import config.SeleniumTest;
import selenium.page.IndexPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.concurrent.TimeUnit;

/**
 * 
 */
@SeleniumTest
public class RankingTests extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;
    
    
    @Test
    public void Rig1(){
    	/* This hand is rigged with :
    	 *  - O1: Royal Flush
    	 *  - O2: two pair
    	 *  - 03: straight
    	 *  - p: HighCard
    	 */
    	
    	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.delay(2);
    	this.indexPage.open.click(); // defaults is 1 player
    	this.waitForDisplayed(this.indexPage.rig).isEnabled();
    	this.indexPage.rig.click();
    	
    	/************************* Set initial hands ************************/
    	// Player  highcard 
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-3 spades, rank-4 clubs, rank-9 spades, rank-k clubs");
    	a.accept();
    	
    	this.waitForAlert();
    	// other 1 Royal Flush
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-j hearts, rank-q hearts, rank-k hearts, rank-a hearts");
    	a.accept();
    	
    	this.waitForAlert();
    	// other 2 Two Pair
    	a =this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 clubs, rank-6 hearts, rank-6 clubs, rank-q spades, rank-q diams");
    	a.accept();
    	
    	this.waitForAlert();
    	// other 3 Straight
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 diams, rank-3 spades, rank-4 diams, rank-5 clubs, rank-6 diams");
    	a.accept();
     

    	assertThat(this.indexPage.hasText("Setting all cards"), is(true));     
    	/****************************** check user options ************************/
    	
    	
    	this.waitForAlert();
      	//Handle prompt to improve user 2 with "0:rank-a clubs"
    	//13: missing code
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-a clubs");
    	a.accept();
  
    	
    	String[] setTexts = this.indexPage.getUsetTexts();
    	// user 1 chooses to stay  
    	//14: missing code  
    	this.delay(5);
    	
    	
    	assertThat(this.indexPage.hasText(setTexts[0] + " chose to STAY"), is(true));
    	
    	
    	//user 2 chooses to hit   	
    	//15: missing code  
    	assertThat(this.indexPage.hasText(setTexts[1] + " chose to HIT"), is(true));
    	
    	// user 3 chooses to Stay
    	//16:missing code  
    	assertThat(this.indexPage.hasText(setTexts[2] + " chose to STAY"), is(true));


    	    	
    	// Player hits and gets 
	// “0:rank-q diams, 1:rank-j clubs, 2:rank-4 spades, 3:rank-8 clubs,  4:rank-2 clubs");
	//17a: missing code
    	this.delay(5);
    	this.waitForDisplayed(this.indexPage.hit).isEnabled();
    	
    	this.indexPage.hit.click();
    	this.indexPage.card1.click();
    	this.indexPage.card2.click();
    	this.indexPage.card3.click();
    	this.indexPage.card4.click();
    	this.indexPage.card5.click();
    	this.delay(2);
    	this.waitForDisplayed(this.indexPage.done).isEnabled();
    	this.indexPage.done.click();
    	//17b: missing code
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-q diams, 1:rank-j clubs, 2:rank-4 spades, 3:rank-8 clubs,  4:rank-2 clubs");
    	a.accept();
    	
    	assertThat(this.indexPage.hasText("You decided to HIT"), is(true));
    	 
    	/*****************  Check results ******************/
    	this.delay(5);
    	// user 1 wins with a royal flush: ranked 1
    	//18: missing code  
    	assertThat(this.indexPage.hasText(setTexts[0] + " won with a score of ROYAL_FLUSH, ranked 1!"), is(true));
    	
    	//user 2 loses with two pairs, ranked 3
    	//19: missing code 
    	assertThat(this.indexPage.hasText(setTexts[1] + " lost with a score of TWO_PAIR, ranked 3!"), is(true));
    	
    	// user 3 loses with straight, ranked 2
    	//20: missing code
    	assertThat(this.indexPage.hasText(setTexts[2] + " lost with a score of STRAIGHT, ranked 2!"), is(true));
    	    	
    	// Player loses with high card, ranked 4
    	//21: missing code
    	assertThat(this.indexPage.hasText(setTexts[3] + " lost with a score of HIGH_CARD, ranked 4!"), is(true));
    
    }


    @Test
    public void Rig2(){
    	/*This had is rigged with :
       	 * 	- O1: Four of a Kind
       	 *  - O2: Full House
       	 *  - 03: One pair
       	 *  - p:  flush
       	 */
    	
    	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.delay(2);
    	this.indexPage.open.click(); // defaults is 1 player
    	this.waitForDisplayed(this.indexPage.rig).isEnabled();
    	this.indexPage.rig.click();
    	
    	
    	/************************* Set initial hands ************************/
    	// Player  flush 
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-4 hearts, rank-7 hearts, rank-9 hearts, rank-k hearts");
    	a.accept();
    	
    	// other 1 Four of a kind
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-10 clubs, rank-10 diams, rank-10 spades, rank-a hearts");
    	a.accept();
    	
    	// other 2 one full house
    	this.waitForAlert();
    	a =this.webDriver.switchTo().alert();
    	a.sendKeys("rank-j clubs, rank-j spades, rank-3 clubs, rank-3 spades, rank-3 diams");
    	a.accept();
    	
    	
    	// other 3 one Pair
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 clubs, rank-2 spades, rank-6 clubs, rank-q spades, rank-q diams");
    	a.accept();
     

    	assertThat(this.indexPage.hasText("Setting all cards"), is(true)); 
    	this.delay(5);
    	/****************************** check user options ************************/
    	
    	//Handle prompt to improve user 3 with "2:rank-a clubs,3:rank-q spades,4:rank-5 spades"
    	//22: missing code  

    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("2:rank-a clubs,3:rank-q spades,4:rank-5 spades");
    	a.accept();
    	
    	
    	
	//user 1 stays, user 2 stays, user 3 hits, player stays 
       //23: missing code  
    	
    	String[] setTexts = this.indexPage.getUsetTexts();
    	this.delay(5);
    	
    	this.waitForDisplayed(this.indexPage.stay).isEnabled();
    	this.indexPage.stay.click();
    	
    	
    	this.delay(5);
    	assertThat(this.indexPage.hasText(setTexts[0] + " chose to STAY"), is(true));
    	

    	assertThat(this.indexPage.hasText(setTexts[1] + " chose to STAY"), is(true));
    	

    	assertThat(this.indexPage.hasText(setTexts[2] + " chose to HIT"), is(true));
    	
    	assertThat(this.indexPage.hasText("You decided to STAY"), is(true));
    	
    	/*****************  Check results ******************/
    	
        //24: missing code for user 1 wins, 3 others lose
    	
    	assertThat(this.indexPage.hasText(setTexts[0] + " won with a score of FOUR_OF_A_KIND, ranked 1!"), is(true));
    	
    	
    	assertThat(this.indexPage.hasText(setTexts[1] + " lost with a score of FULL_HOUSE, ranked 2!"), is(true));
    	
    	
    	assertThat(this.indexPage.hasText(setTexts[2] + " lost with a score of ONE_PAIR, ranked 4!"), is(true));
    	    	
    	
    	assertThat(this.indexPage.hasText(setTexts[3] + " lost with a score of FLUSH, ranked 3!"), is(true));
    }
    
    
    
    
    @Test
    public void Rig3(){
    	/* This had is rigged with :
    	 *  - O1: One pair
    	 *  - O2: 3 of a kind
    	 *  - 03: straight flush
    	 *  - p:  high card
       	 */
    	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.delay(2);
    	this.indexPage.open.click(); // defaults is 1 player
    	this.waitForDisplayed(this.indexPage.rig).isEnabled();
    	this.indexPage.rig.click();
    	
    	/************************* Set initial hands ************************/
    	// Player  High Card 
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-4 diams, rank-7 spades, rank-9 diams, rank-k hearts");
    	a.accept();
    	
    	// other 1 one pair
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-10 clubs, rank-6 diams, rank-2 spades, rank-a hearts");
    	a.accept();
    	
    	// other 2, 3 of a kind
    	this.waitForAlert();
    	a =this.webDriver.switchTo().alert();
    	a.sendKeys("rank-j clubs, rank-j spades, rank-j spades, rank-3 diams, rank-8 spades");
    	a.accept();
    	
    	
    	// other 3 straight flush
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 clubs, rank-3 clubs, rank-4 clubs, rank-5 clubs, rank-6 clubs");
    	a.accept();
     

    	assertThat(this.indexPage.hasText("Setting all cards"), is(true));     
    	/****************************** check user options ************************/
    	
    	//Handle prompt to improve user 1
    	// with “0:rank-7 clubs,1:rank-j clubs,4:rank-5 diams"
    	//25: missing code
    	
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-7 clubs,1:rank-j clubs,4:rank-5 diams");
    	a.accept();
    	
    	
       	//Handle prompt to improve user 2
       	//with ”0:rank-4 spades,1:rank-a clubs"
       	//26: missing code 
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-4 spades,1:rank-a clubs");
    	a.accept();
    	
    	String[] setTexts = this.indexPage.getUsetTexts();
    	this.delay(5);

	//user 1, user 2 hit and user 3 stays
    	//27: missing code  
    	
    	assertThat(this.indexPage.hasText(setTexts[0] + " chose to HIT"), is(true));
    	

    	assertThat(this.indexPage.hasText(setTexts[1] + " chose to HIT"), is(true));
    	

    	assertThat(this.indexPage.hasText(setTexts[2] + " chose to STAY"), is(true));
    	
    	    	
    	// Player gets
	//“0:rank-4 spades, 1:rank-7 clubs, 2:rank-a clubs,3:rank-q spades,4:rank-5 spades")
    	//28: missing code 
    	
    	this.delay(5);
    	this.waitForDisplayed(this.indexPage.hit).isEnabled();
    	
    	this.indexPage.hit.click();
    	this.indexPage.card1.click();
    	this.indexPage.card2.click();
    	this.indexPage.card3.click();
    	this.indexPage.card4.click();
    	this.indexPage.card5.click();
    	this.delay(2);
    	this.waitForDisplayed(this.indexPage.done).isEnabled();
    	this.indexPage.done.click();
    	//17b: missing code
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-4 spades, 1:rank-7 clubs, 2:rank-a clubs,3:rank-q spades,4:rank-5 spades");
    	a.accept();
    	
    	
    	assertThat(this.indexPage.hasText("You decided to HIT"), is(true));

    	/*****************  Check results ******************/
    	
	//29: missing code for the outcome
    	
    	assertThat(this.indexPage.hasText(setTexts[0] + " lost with a score of ONE_PAIR, ranked 3!"), is(true));
    	
    	
    	assertThat(this.indexPage.hasText(setTexts[1] + " lost with a score of THREE_OF_A_KIND, ranked 2!"), is(true));
    	
    	
    	assertThat(this.indexPage.hasText(setTexts[2] + " won with a score of STRAIGHT_FLUSH, ranked 1!"), is(true));
    	
    	
    	assertThat(this.indexPage.hasText(setTexts[3] + " lost with a score of HIGH_CARD, ranked 4!"), is(true));
    
    }






 
 
 

  
    
    
    
    
}
