package selenium;

import config.SeleniumTest;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import selenium.page.IndexPage;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test the ability to connect and disconnect without getting any weird errors.
 * <p/>
 * Created by Mike on 11/8/2015.
 */
@SeleniumTest
public class ConnectionBasicsTest extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;

    @Test
    public void canConnect() {
       
    }

    @Test
    public void canOpenLobby() {
   
    }

    @Test
    public void canStartGame() {
       
    }

    @Test
    public void canSeeCardsAfterStart() {
      
    }
}
