package selenium;

import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.testng.Assert.assertFalse;

import ca.carleton.poker.game.Player.*;
import ca.carleton.poker.game.entity.card.Card;
import ca.carleton.poker.game.entity.card.Hand;
import ca.carleton.poker.game.entity.card.Rank;
import ca.carleton.poker.game.entity.card.Suit;
import config.SeleniumTest;
import selenium.page.*;

@SeleniumTest
public class AIStrategy1Test extends AbstractSeleniumTest{
	
	

	    @Autowired
	    private IndexPage indexPage;
	    
	    
	    @Before
		public void setup(){
	      	
	    }
	    
	    @After
		public void tearDown(){
	    	this.indexPage.disconnect.click();
	    }
	    
	    @Test
	    public void FirstAIPlayer_Straight() throws Exception{    
	    	this.indexPage.connect.click();
	    	this.waitForDisplayed(this.indexPage.open).isEnabled();
	    	this.indexPage.open.click(); // defaults is 1 player
	    	
	    	waitFor(5);
	    	assertThat(this.indexPage.hasText("The game is now ready to begin"), is(true));
	    	/******************* set first  AI Player with a Royal flush *******************/
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
	    	  
	    	    /******************* set second  AI Player with a 3  of a kind *******************/
	    	  c1 = new Card(Rank.EIGHT, Suit.SPADES, false);
	    	  c2 = new Card(Rank.EIGHT, Suit.CLUBS, false);
	    	  c3 = new Card(Rank.EIGHT, Suit.DIAMONDS, false);
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
	    	  /******************* set third  AI Player with a pair *******************/
	    	  
	    	  c1 = new Card(Rank.JACK, Suit.SPADES, false);
	    	  c2 = new Card(Rank.JACK, Suit.CLUBS, false);
	    	  c3 = new Card(Rank.FIVE, Suit.DIAMONDS, false);
	    	  c4 = new Card(Rank.THREE, Suit.HEARTS, false);
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
	    	 
	    	  /******************* set Player with random cards *******************/
	    	
	    	  c1 = new Card(Rank.TWO, Suit.HEARTS, false);
	    	  c2 = new Card(Rank.FOUR, Suit.CLUBS, false);
	    	  c3 = new Card(Rank.NINE, Suit.CLUBS, false);
	    	  c4 = new Card(Rank.THREE, Suit.SPADES, false);
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
	    	  /****************************************************************/
	    	 
	    	
	    	  this.indexPage.updateCards();
	    	  this.indexPage.start.click();
	    	  
	    	  assertThat(this.indexPage.hasText("AI-1243513 made their turn and STAY."), is(true));
	    	  assertThat(this.indexPage.hasText("AI-1243514 made their turn and HIT"), is(true));
	    	  assertThat(this.indexPage.hasText("AI-1243515 made their turn and HIT"), is(true));
	    	  assertThat(this.indexPage.hasText("All AI players have made their choices"), is(true));  
	    	  
	    	  
	    	// Check Rankings
	    	  this.waitForDisplayed(indexPage.hit).isEnabled();
	    	  this.indexPage.hit.click();
	    	  this.indexPage.card1.click();
	    	  this.indexPage.card5.click();
	    	  this.indexPage.done.click();
	    	  this.waitForDisplayed(indexPage.start).isEnabled();
	    	  String[] ids = this.indexPage.getUsetTexts();
	   
	    	  assertThat(ids[0], is("  AI-1243513 ~ Value: ROYAL_FLUSH"));
	    	  assertThat(ids[1], is("  AI-1243514 ~ Value: THREE_OF_A_KIND"));
	    	  assertThat(ids[2], is("  AI-1243515 ~ Value: ONE_PAIR"));	    	  
	    	
	    }
	    
}
