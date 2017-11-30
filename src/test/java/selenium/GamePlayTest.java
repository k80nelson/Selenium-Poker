package selenium;

import config.SeleniumTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import selenium.page.IndexPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test our game options 
 * Created by Cheryl  on 11/27/2017.
 */
@SeleniumTest
public class GamePlayTest extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;
    
    
    @Test
    public void canUseHitOption() {
        this.indexPage.quickStart();
        assertThat(this.waitForDisplayed(this.indexPage.hit).isEnabled(), is(true));
        final int numberCards = this.indexPage.countNumberOfCardsFor(this.indexPage.playerCards);
        this.indexPage.hit.click();
        assertThat(this.indexPage.hasText("You decided to HIT. Sending to server - please wait for your next turn."),
                is(true));
        assertThat(this.indexPage.countNumberOfCardsFor(this.indexPage.playerCards), is(numberCards + 1));
        this.indexPage.disconnect();
    }

    @Test
    public void canSelectCards(){
    	this.indexPage.quickStart();
    	assertThat(this.waitForDisplayed(this.indexPage.done).isEnabled(), is(true));
    	this.waitForDisplayed(this.indexPage.card1).click();
    	assertThat(this.indexPage.hasText("Selected "+1), is(true));
    	this.indexPage.disconnect();
    }
    
    @Test 
    public void canSelectedMultipleCards(){
    	this.indexPage.quickStart();
    	assertThat(this.waitForDisplayed(this.indexPage.done).isEnabled(), is(true));
    	this.waitForDisplayed(this.indexPage.card1).click();
    	assertThat(this.indexPage.hasText("Selected "+1), is(true));
    	this.waitForDisplayed(this.indexPage.card3).click();
    	assertThat(this.indexPage.hasText("Selected "+3), is(true));
    	this.waitForDisplayed(this.indexPage.card1).click();
    	assertThat(this.indexPage.hasText("Selected "+4), is(true));
    	this.indexPage.disconnect();
    }
    
    
    
    @Test
    public void canUseStayOption() {
        this.indexPage.quickStart();
        assertThat(this.waitForDisplayed(this.indexPage.stay).isEnabled(), is(true));
        final int numberCards = this.indexPage.countNumberOfCardsFor(this.indexPage.playerCards);
        this.indexPage.stay.click();
        assertThat(this.indexPage.hasText("You decided to STAY. Sending to server - please wait for your next turn."),
                is(true));
        assertThat(this.indexPage.countNumberOfCardsFor(this.indexPage.playerCards), is(numberCards));
        this.indexPage.disconnect();
    }

    
    @Test
    public void canPressDone() {
        this.indexPage.quickStart();
        assertThat(this.waitForDisplayed(this.indexPage.stay).isEnabled(), is(true));
        final int numberCards = this.indexPage.countNumberOfCardsFor(this.indexPage.playerCards);
        this.indexPage.stay.click();
        assertThat(this.indexPage.hasText("You decided to STAY. Sending to server - please wait for your next turn."),
                is(true));
        assertThat(this.indexPage.countNumberOfCardsFor(this.indexPage.playerCards), is(numberCards));
        this.indexPage.disconnect();
    }

 
   
}
