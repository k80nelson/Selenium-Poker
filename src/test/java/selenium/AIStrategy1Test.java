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
	    	this.indexPage.chrome.get("http://localhost:8080");
	    	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	      	this.indexPage.connect.click();
	    	this.indexPage.open.click(); // defaults is 1 player
	    	this.indexPage.start.click();
	    	
	    	this.indexPage.game.getConnectedPlayers();
	    	
	    }
	    
	    @After
		public void tearDown(){
	    	this.indexPage.disconnect.click();
	    }
	    
	    @Test
	    public void FirstAIPlayer_Straight(ArrayList<Paper> players){
	    	/******************* set first  AI Player with a flush *******************/
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
	    
	    	  String[] cards = {c1.toHTMLString(), c2.toHTMLString(), c3.toHTMLString(), c4.toHTMLString(), c5.toHTMLString()};
	    	  //Set card
	    	  this.indexPage.setInnerHTML(cards, this.indexPage.otherPlayer1Cards);
	    	  assertTrue(this.indexPage.checkCards(cards, this.indexPage.otherPlayer1Cards));
		          
	          
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
	    	  this.indexPage.updateCards(h, 1);
	    	  String[] cards1 = {c1.toHTMLString(), c2.toHTMLString(), c3.toHTMLString(), c4.toHTMLString(), c5.toHTMLString()};
	    	  //Set cards
	    	  this.indexPage.setInnerHTML(cards1, this.indexPage.otherPlayer2Cards);
	    	  assertTrue(this.indexPage.checkCards(cards1, this.indexPage.otherPlayer2Cards));
	    	
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
	    
	    	  String[] cards2 = {c1.toHTMLString(), c2.toHTMLString(), c3.toHTMLString(), c4.toHTMLString(), c5.toHTMLString()};
	    	  //Set cards
	    	  this.indexPage.setInnerHTML(cards2, this.indexPage.otherPlayer3Cards);
	    	  assertTrue(this.indexPage.checkCards(cards2, this.indexPage.otherPlayer3Cards));

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
	   
	    	  String[] cards3 = {c1.toHTMLString(), c2.toHTMLString(), c3.toHTMLString(), c4.toHTMLString(), c5.toHTMLString()};
	    	  //Set cards
	    	  this.indexPage.setInnerHTML(cards3, this.indexPage.playerHandCards);
	    	  assertTrue(this.indexPage.checkCards(cards3, this.indexPage.playerHandCards));

	    	  
	    	  
	    	  /****************************************************************/
	    	  // wait for all AI to have played 
	          assertThat(this.indexPage.hasText("All AI players have made their choices"), is(true));
	   
	    	  // check that AI1 Stayed
	    	  assertThat(this.indexPage.hasText(""), is(true));
	    	  // Check that AI2 discarded 2 cards that were not the 3 of a kind
	    	  // check that AI4 discarded 3 cards that were not the pair
	    	  
	    }
	    
}
