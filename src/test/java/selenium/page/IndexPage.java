package selenium.page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ca.carleton.poker.game.PokerGame;
import ca.carleton.poker.game.entity.card.Hand;

import org.apache.commons.lang3.StringUtils;
import selenium.page.IndexPage;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Lazy
@Component
public class IndexPage extends  AbstractPage<IndexPage> {

	
	@Autowired
	public PokerGame game; 
	 
  //  public ChromeDriver chrome;
    
    @FindBy(id = "connect")
    public WebElement connect;

    @FindBy(id = "disconnect")
    public WebElement disconnect;

    @FindBy(id = "numberPlayers")
    public WebElement numberPlayers;

    @FindBy(id = "open")
    public WebElement open;

    @FindBy(id = "start")
    public WebElement start;

    @FindBy(id = "stay")
    public WebElement stay;

    @FindBy(id = "hit")
    public WebElement hit;
    
    @FindBy(id ="done")
    public WebElement done;

    @FindBy(id = "consoleText")
    public WebElement consoleText;

    @FindBy(id = "PlayerCard1")
    public WebElement card1;
    
    @FindBy(id = "PlayerCard2")
    public WebElement card2;
    
    @FindBy(id = "PlayerCard3")
    public WebElement card3;
    
    @FindBy(id = "PlayerCard4")
    public WebElement card4;
    
    @FindBy(id = "PlayerCard5")
    public WebElement card5;
    
    @FindBy(id = "playerHandCards")
    public WebElement playerHandCards;
    
    @FindBy(id = "otherHandCards1")
    public WebElement otherPlayer1Cards;

    @FindBy(id = "otherHandCards2")
    public WebElement otherPlayer2Cards;
    
    @FindBy(id = "otherHandCards3")
    public WebElement otherPlayer3Cards;

    @FindBy(id = "console")
    public WebElement console;
	 
    
 
    
    public int countNumberOfCardsFor(final WebElement cardList) {
        return this.getAllCardsFor(cardList).size();
    }
   
    /**
     * Fetch all the inner nodes of the given web element.
     *
     * @param cardList the list.
     * @return the list of 'li' elements.
     */
    public List<WebElement> getAllCardsFor(final WebElement cardList) {
    	return this.webDriver.findElements(By.xpath(String.format("//ul[@id='%s']/li", cardList.getAttribute("id"))));
   }
    

    
  
    /**
     * Return the player's UID if connected.
     *
     * @return the UID.
     */
    public String getPlayerUID() {
    	
    	if (!this.connect.isEnabled()) {
            final String consoleText = this.consoleText.getText();
            return consoleText.replace("Console (UID: ", "").replace(")", "").trim();
        }
       return null;
   }


    /**
     * Return the other player's UID if connected.
     *
     * @return the UID.
     */
    public String[] getOthersUID() {
    	String[] ids = new String[3];
    	ids[0] = this.otherPlayer1Cards.getText().replace("Other Player's Hand", " ");
    	ids[1] = this.otherPlayer2Cards.getText().replace("Other Player's Hand", " ");
    	ids[2] = this.otherPlayer3Cards.getText().replace("Other Player's Hand", " ");
       return ids;
   }
    /**
     * Set the number of players.
     *
     * @param number the number of players.
     */
    public void setNumberPlayers(final int number) {
        this.webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        if (this.webDriver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) this.webDriver).executeScript(
                    String.format("document.getElementById('numberPlayers').setAttribute('value', '%s')", number));
        }
        this.webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }
    
    
    public void setInnerHTML(String[] s, WebElement e){
    	List<WebElement> li = e.findElements(By.tagName("li"));
    	for (int i = 0; i < s.length; i++) {
    	((JavascriptExecutor)this.webDriver).executeScript(
    			"arguments[0].innerHTML = arguments[1];", li.get(i), s[i]);

    	}
    }



    protected String getPageName() {
       return StringUtils.EMPTY;
    }

	public boolean checkCards(String[] cards, WebElement e) {
		List<WebElement> li = e.findElements(By.tagName("li"));
    	for (int i = 0; i < cards.length; i++) {
    		
    		String s = li.get(i).getAttribute("innerHTML").replace("♥", "&hearts;").
    				replace("♣", "&clubs;").
    				replace("♠", "&spades;").
    				replace("♦", "&diams;");

    		if(!s.equals(cards[i])) return false;
    		
    	}
		return true;
	}

	public void updateCards(Hand h, int i) {
		WebElement form = null; 
		if(i == 1){
			form = this.otherPlayer1Cards; 
		}else if(i ==2 ){
			form = this.otherPlayer2Cards;
		}else if (i == 3){
			form = this.otherPlayer3Cards;
		}
		
		((JavascriptExecutor)this.webDriver).executeScript(
    			"arguments[0].setAttribute('submit', sendOtherCards(argument[1])", form, String.valueOf(i));
		
	}
	

}