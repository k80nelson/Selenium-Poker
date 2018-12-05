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
 * 
 */
@SeleniumTest
public class Strategy1Tests extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;
    
    
    // Royal flush, hold
    
    // straight flush, hold
    
    // four kind, hold
    
    // full house, hold
    
    // flush, hold
    
    // straight, hold
    
    
    // exchange all non pairs or 3 kinds
    
    // three kind
    
    // two pairs
    
    // one pair
    
    // no pairs
    






   
}
