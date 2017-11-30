package selenium.page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The page for the main game.
 * Created by Mike on 11/8/2015.
 */
@Lazy
@Component
public class IndexPage extends AbstractPage<IndexPage> {

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

    @FindBy(id = "playerHandCards")
    public WebElement playerCards;
    
    @FindBy(id ="1")
    public WebElement card1;
    
    @FindBy(id ="2")
    public WebElement card2;
    
    @FindBy(id ="3")
    public WebElement card3;
    
    @FindBy(id ="4")
    public WebElement card4;
    
    @FindBy(id ="5")
    public WebElement card5;
    
    
    @FindBy(id = "otherHandCards1")
    public WebElement otherPlayer1Cards;

    @FindBy(id = "otherHandCards2")
    public WebElement otherPlayer2Cards;
    
    @FindBy(id = "otherHandCards3")
    public WebElement otherPlayer3Cards;

    @FindBy(id = "console")
    public WebElement console;

    /**
     * Quick start the game by connecting, opening the lobby, and starting the game.
     */
    public void quickStart() {
        this.connect.click();
        this.numberPlayers.sendKeys("1");
        this.open.click();
        this.start.click();
    }
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
     * Connect to the game.
     */
    public void connect() {
        this.connect.click();
    }

    /**
     * Disconnect from the game.
     */
    public void disconnect() {
        this.disconnect.click();
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


    @Override
    protected String getPageName() {
        return StringUtils.EMPTY;
    }
}
