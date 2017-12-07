package selenium;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.Alert;
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
 * Created by Cheryl 
 */
@SeleniumTest
public class RankingTests extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;
    


    /*@Test
    public void Rig1(){
    	 This had is rigged with :
    	 * 	- O1: Royal Flush
    	 *  - O2: two pair
    	 *  - 03: straight
    	 *  - p: HighCard
    	 
    	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.indexPage.open.click(); // defaults is 1 player
    	
    	waitFor(5);
    	assertThat(this.indexPage.hasText("The game is now ready to begin"), is(true));
    	*//******************* set first  AI Player with a flush *******************//*
    	  Hand h = new Hand();
    	  Card c1 = new Card(Rank.TEN, Suit.HEARTS, false);
    	  Card c2 = new Card(Rank.JACK, Suit.HEARTS, false);
    	  Card c3 = new Card(Rank.QUEEN, Suit.HEARTS, false);
    	  Card c4 = new Card(Rank.KING, Suit.HEARTS, false);
    	  Card c5 = new Card(Rank.ACE_HIGH, Suit.HEARTS, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
    	//Set card
    	  String[] cards = {c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	  this.indexPage.setInnerHTML(cards, this.indexPage.otherPlayer1Cards);
    	  waitFor(5);
    	  
    	    *//******************* set second  AI Player with a 2  pair *******************//*
    	  c1 = new Card(Rank.EIGHT, Suit.SPADES, false);
    	  c2 = new Card(Rank.EIGHT, Suit.CLUBS, false);
    	  c3 = new Card(Rank.SIX, Suit.DIAMONDS, false);
    	  c4 = new Card(Rank.SIX, Suit.HEARTS, false);
    	  c5 = new Card(Rank.TWO, Suit.SPADES, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
    
    	  String[] cards1 = {c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	   //Set cards
    	  this.indexPage.setInnerHTML(cards1, this.indexPage.otherPlayer2Cards);
    	  waitFor(5);
    	  *//******************* set third  AI Player with a straight *******************//*
    	  
    	  c1 = new Card(Rank.FIVE, Suit.SPADES, false);
    	  c2 = new Card(Rank.SIX, Suit.CLUBS, false);
    	  c3 = new Card(Rank.SEVEN, Suit.DIAMONDS, false);
    	  c4 = new Card(Rank.EIGHT, Suit.HEARTS, false);
    	  c5 = new Card(Rank.NINE, Suit.SPADES, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
    
    	  String[] cards2 = {c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	   //Set cards
    	  this.indexPage.setInnerHTML(cards2, this.indexPage.otherPlayer3Cards);
    	  waitFor(5);
    	 
    	  *//******************* set Player with a High Card *******************//*
    	
    	  c1 = new Card(Rank.JACK, Suit.DIAMONDS, false);
    	  c2 = new Card(Rank.FOUR, Suit.CLUBS, false);
    	  c3 = new Card(Rank.SIX, Suit.CLUBS, false);
    	  c4 = new Card(Rank.NINE, Suit.SPADES, false);
    	  c5 = new Card(Rank.ACE_HIGH, Suit.DIAMONDS, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
   
    	  String[] cards3 ={c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	   //Set cards
    	  this.indexPage.setInnerHTML(cards3, this.indexPage.playerHandCards);   	  
    	  waitFor(5);
    	  *//****************************************************************//*
    	 
    	
    	  this.indexPage.updateCards();
    	  this.indexPage.start.click();
    	  
    	 
    	  // Check Rankings
    	  this.waitForDisplayed(indexPage.stay).isEnabled();
    	  this.indexPage.stay.click();
    	  this.waitForDisplayed(indexPage.start).isEnabled();
    	  String playerID = this.indexPage.getPlayerUID();
    	  String[] ids = this.indexPage.getUsetTexts();
   
    	  assertThat(ids[0], is("  AI-1243513 ~ Value: ROYAL_FLUSH"));
    	  assertThat(ids[1], is("  AI-1243514 ~ Value: TWO_PAIR"));
    	  assertThat(ids[2], is("  AI-1243515 ~ Value: STRAIGHT"));
    	  assertThat(ids[3], is("  "+ playerID + (" ~ Value: HIGH_CARD")));
    	
    	   
    	  assertThat(this.indexPage.hasText("  won with a score of  ROYAL_FLUSH"), is(true));
    	  assertThat(this.indexPage.hasText("  lost with a score of  TWO_PAIR"), is(true));
    	  assertThat(this.indexPage.hasText("  lost with a score of  STRAIGHT"), is(true));
    	  assertThat(this.indexPage.hasText("  lost with a score of  HIGH_CARD"), is(true));
      	
        //disconnect
        this.indexPage.disconnect.click();        
    }
 
    
    @Test
    public void Rig2(){
    	 This had is rigged with :
    	 * 	- O1: Four of a Kind
    	 *  - O2: Full House
    	 *  - 03: One pair
    	 *  - p:  flush
    	 
    	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.indexPage.open.click(); // defaults is 1 player
    	
    	waitFor(5);
    	assertThat(this.indexPage.hasText("The game is now ready to begin"), is(true));
    	*//******************* set first  AI Player with a Four of a kind *******************//*
    	  Hand h = new Hand();
    	  Card c1 = new Card(Rank.TEN, Suit.HEARTS, false);
    	  Card c2 = new Card(Rank.TEN, Suit.CLUBS, false);
    	  Card c3 = new Card(Rank.TEN, Suit.DIAMONDS, false);
    	  Card c4 = new Card(Rank.TEN, Suit.SPADES, false);
    	  Card c5 = new Card(Rank.EIGHT, Suit.HEARTS, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
    	//Set card
    	  String[] cards = {c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	  this.indexPage.setInnerHTML(cards, this.indexPage.otherPlayer1Cards);
    	  waitFor(5);
    	  
    	    *//******************* set second  AI Player with a full house *******************//*
    	  c1 = new Card(Rank.EIGHT, Suit.SPADES, false);
    	  c2 = new Card(Rank.EIGHT, Suit.CLUBS, false);
    	  c3 = new Card(Rank.EIGHT, Suit.DIAMONDS, false);
    	  c4 = new Card(Rank.SIX, Suit.HEARTS, false);
    	  c5 = new Card(Rank.SIX, Suit.SPADES, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
    
    	  String[] cards1 = {c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	   //Set cards
    	  this.indexPage.setInnerHTML(cards1, this.indexPage.otherPlayer2Cards);
    	  waitFor(5);
    	  *//******************* set third  AI Player with a pair *******************//*
    	  
    	  c1 = new Card(Rank.JACK, Suit.SPADES, false);
    	  c2 = new Card(Rank.JACK, Suit.CLUBS, false);
    	  c3 = new Card(Rank.THREE, Suit.DIAMONDS, false);
    	  c4 = new Card(Rank.FIVE, Suit.HEARTS, false);
    	  c5 = new Card(Rank.QUEEN, Suit.CLUBS, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
    
    	  String[] cards2 = {c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	   //Set cards
    	  this.indexPage.setInnerHTML(cards2, this.indexPage.otherPlayer3Cards);
    	  waitFor(5);
    	 
    	  *//******************* set Player with a flush *******************//*
    	
    	  c1 = new Card(Rank.TWO, Suit.CLUBS, false);
    	  c2 = new Card(Rank.FOUR, Suit.CLUBS, false);
    	  c3 = new Card(Rank.NINE, Suit.CLUBS, false);
    	  c4 = new Card(Rank.THREE, Suit.CLUBS, false);
    	  c5 = new Card(Rank.ACE_HIGH, Suit.CLUBS, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
   
    	  String[] cards3 ={c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	   //Set cards
    	  this.indexPage.setInnerHTML(cards3, this.indexPage.playerHandCards);   	  
    	  waitFor(5);
    	  *//****************************************************************//*
    	 
    	
    	  this.indexPage.updateCards();
    	  this.indexPage.start.click();
    	  
    	 // Check Rankings
    	  this.waitForDisplayed(indexPage.stay).isEnabled();
    	  this.indexPage.stay.click();
    	  this.waitForDisplayed(indexPage.start).isEnabled();
    	  
    	  String playerID = this.indexPage.getPlayerUID();
    	  String[] ids = this.indexPage.getUsetTexts();
   
    	  assertThat(ids[0], is("  AI-1243516 ~ Value: FOUR_OF_A_KIND"));
    	  assertThat(ids[1], is("  AI-1243517 ~ Value: FULL_HOUSE"));
    	  assertThat(ids[2], is("  AI-1243518 ~ Value: ONE_PAIR"));
    	  assertThat(ids[3], is("  "+ playerID + (" ~ Value: FLUSH")));
    	  
    	  
    	  assertThat(this.indexPage.hasText("AI-1243513 won with a score of FOUR_OF_A_KIND"), is(true));
    	  assertThat(this.indexPage.hasText("AI-1243514 lost with a score of FULL_HOUSE"), is(true));
    	  assertThat(this.indexPage.hasText("AI-1243515 lost with a score of ONE_PAIR"), is(true));
    	  assertThat(this.indexPage.hasText(playerID +"lost with a score of FLUSH"), is(true));
      	
        //disconnect
        this.indexPage.disconnect.click();        
    }
    
    
    
    @Test
    public void Rig3(){
    	 This had is rigged with :
    	 * 	- O1: straight flush
    	 *  - O2: 3 of a kind
    	 *  - 03: One pair
    	 *  - p:  high card
    	 
    	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.indexPage.open.click(); // defaults is 1 player
    	
    	waitFor(5);
    	assertThat(this.indexPage.hasText("The game is now ready to begin"), is(true));
    	*//******************* set first  AI Player with a straight flush *******************//*
    	  Hand h = new Hand();
    	  Card c1 = new Card(Rank.THREE, Suit.CLUBS, false);
    	  Card c2 = new Card(Rank.FOUR, Suit.CLUBS, false);
    	  Card c3 = new Card(Rank.FIVE, Suit.CLUBS, false);
    	  Card c4 = new Card(Rank.SIX, Suit.CLUBS, false);
    	  Card c5 = new Card(Rank.SEVEN, Suit.CLUBS, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
    	//Set card
    	  String[] cards = {c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	  this.indexPage.setInnerHTML(cards, this.indexPage.otherPlayer1Cards);
    	  waitFor(5);
    	  
    	    *//******************* set second  AI Player with a 3  of a kind *******************//*
    	  c1 = new Card(Rank.EIGHT, Suit.SPADES, false);
    	  c2 = new Card(Rank.EIGHT, Suit.CLUBS, false);
    	  c3 = new Card(Rank.EIGHT, Suit.DIAMONDS, false);
    	  c4 = new Card(Rank.NINE, Suit.HEARTS, false);
    	  c5 = new Card(Rank.ACE_LOW, Suit.SPADES, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
    
    	  String[] cards1 = {c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	   //Set cards
    	  this.indexPage.setInnerHTML(cards1, this.indexPage.otherPlayer2Cards);
    	  waitFor(5);
    	  *//******************* set third  AI Player with a pair *******************//*
    	  
    	  c1 = new Card(Rank.TWO, Suit.SPADES, false);
    	  c2 = new Card(Rank.TWO, Suit.DIAMONDS, false);
    	  c3 = new Card(Rank.SIX, Suit.DIAMONDS, false);
    	  c4 = new Card(Rank.NINE, Suit.HEARTS, false);
    	  c5 = new Card(Rank.QUEEN, Suit.SPADES, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
    
    	  String[] cards2 = {c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	   //Set cards
    	  this.indexPage.setInnerHTML(cards2, this.indexPage.otherPlayer3Cards);
    	  waitFor(5);
    	 
    	  *//******************* set Player with a high card *******************//*
    	
    	  c1 = new Card(Rank.TWO, Suit.HEARTS, false);
    	  c2 = new Card(Rank.FOUR, Suit.DIAMONDS, false);
    	  c3 = new Card(Rank.NINE, Suit.SPADES, false);
    	  c4 = new Card(Rank.THREE, Suit.SPADES, false);
    	  c5 = new Card(Rank.QUEEN, Suit.DIAMONDS, false);
    	  h.addCard(c1);
    	  h.addCard(c2);
    	  h.addCard(c3);
    	  h.addCard(c4);
    	  h.addCard(c5);
   
    	  String[] cards3 ={c1.toFormHTML(), c2.toFormHTML(), c3.toFormHTML(), c4.toFormHTML(), c5.toFormHTML()};
    	   //Set cards
    	  this.indexPage.setInnerHTML(cards3, this.indexPage.playerHandCards);   	  
    	  waitFor(5);
    	  *//****************************************************************//*
    	 
    	
    	  this.indexPage.updateCards();
    	  this.indexPage.start.click();
    	  
    	  
    	// Check Rankings
    	  this.waitForDisplayed(indexPage.stay).isEnabled();
    	  this.indexPage.stay.click();
    	  this.waitForDisplayed(indexPage.start).isEnabled();
    	 
    	  String playerID = this.indexPage.getPlayerUID();
    	  String[] ids = this.indexPage.getUsetTexts();
   
    	  assertThat(ids[0], is("  AI-1243519 ~ Value: STRAIGHT_FLUSH"));
    	  assertThat(ids[1], is("  AI-1243520 ~ Value: THREE_OF_A_KIND"));
    	  assertThat(ids[2], is("  AI-1243521 ~ Value: ONE_PAIR"));
    	  assertThat(ids[3], is("  "+ playerID + (" ~ Value: HIGH_CARD")));
    	  
    	  assertThat(this.indexPage.hasText("AI-1243513 won with a score of STRAIGHT_FLUSH"), is(true));
    	  assertThat(this.indexPage.hasText("AI-1243514 lost with a score of THREE_OF_A_KIND"), is(true));
    	  assertThat(this.indexPage.hasText("AI-1243515 lost with a score of ONE_PAIR"), is(true));
    	  assertThat(this.indexPage.hasText(playerID +"lost with a score of HIGH_CARD !"), is(true));
    	  
      	
        //disconnect
        this.indexPage.disconnect.click();        
    }
 
 */
    
    @Test
    public void Rig1(){
    	/* This had is rigged with :
    	 * 	- O1: Royal Flush
    	 *  - O2: two pair
    	 *  - 03: straight
    	 *  - p: HighCard
    	 */
    	
    	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.indexPage.open.click(); // defaults is 1 player
    	this.waitForDisplayed(this.indexPage.rig).isEnabled();
    	this.indexPage.rig.click();
    	
    	//this.waitForAlert();
    	/************************* Set inital hands ************************/
    	// Player
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-3 spades, rank-9 spades, rank-4 clubs, rank-k clubs, rank-2, hearts");
    	a.accept();
    	
    	// other 1
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-j hearts, rank-q hearts, rank-k hearts, rank-a hearts");
    	a.accept();
    	
    	// other 2
    
    	this.waitForAlert();
    	a =this.webDriver.switchTo().alert();
    	a.sendKeys("rank-6 hearts, rank-6 clubs, rank-q spades, rank-k diams, rank-2 clubs");
    	a.accept();
    	
    	
    	// other 3
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys(" 1 rank-2 diams, rank-3 diams, rank-4 diams, rank-5 diams, rank-6 diams");
    	a.accept();
    	assertThat(this.indexPage.hasText(" Setting all cards"), is(true));
    
    	/********************************* Catch Improvements ********************/ 
    	
    	// user 1 will stay 
    	String[] ids = this.indexPage.getUsetTexts();
    	assertThat(this.indexPage.hasText(ids[0] + " made their move and STAY"), is(true));
    	
    	this.waitForDisplayed(this.indexPage.stay).isEnabled();
    	
    
    	/*this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("3 rank-3 spades, rank-9 spades, rank-4 clubs, rank-k clubs, rank-2, hearts");
    	a.accept();
    	*/
    	
    
    }





 
 
 

  
    
    
    
    
}
