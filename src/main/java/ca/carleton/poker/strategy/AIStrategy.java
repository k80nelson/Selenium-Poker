package ca.carleton.poker.strategy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.carleton.poker.game.GameOption;
import ca.carleton.poker.game.Player.AIPlayer;
import ca.carleton.poker.game.entity.card.Card;
import ca.carleton.poker.game.entity.card.Hand;
import ca.carleton.poker.game.entity.card.PokerHand;
@Service
public interface AIStrategy {
	
	 static final Logger LOG = LoggerFactory.getLogger(AIStrategy1.class);
	  public static  List<Card> doStrategy(AIPlayer player, ArrayList<AIPlayer>players){
		  boolean has3 = true;
		  boolean first = true;
		  PokerHand handvalue = player.getHand().getPokerValue();
		  ArrayList<AIPlayer> others = new ArrayList<>();
		  
		  for(AIPlayer p : players){
			if(!p.equals(player)){
				LOG.info("AI checking others {}, {}", player.getuid(), p.getuid());
				others.add(p);
				has3 = p.getHand().visibleHand().isThreeOfAKind() ||p.getHand().visibleHand().isThreeOfASuit();
				if(p.getLastOption() != null){
					first = false;
				}	 
			}
				
		  }
		  
		 
		  
		  //  If royal flush we do not improve any cards
		  if(handvalue.equals(PokerHand.ROYAL_FLUSH)){
	        	LOG.info("AI Staying for Royal Flush ");
	        	return null;
		  }
		  // if straight we do not improve cards
	      else if(handvalue.equals(PokerHand.STRAIGHT)){
	        	LOG.info("AI Staying for Straight ");
	        	return null;
	      }
		  /*
			  * if player is not the first to player and another user has a visible 3 of a kind
			  */
	      else if(has3 && !first){
			  LOG.info("AI is using Strategy 2 becuase they see a visible 3 of a kind ");
			  AIStrategy2 ai = new AIStrategy2(player, others);
			  return ai.chooseCard();
			  
		  }
		  /* 
		   * if players have a > straight 
		   * or first player
		   * or other Players have 3 cards of the same suit or same rank use Strategy 1
		  */		
		  else{
			  LOG.info("AI is using Strategy 1");
			  AIStrategy1 ai =  new AIStrategy1(player);
			  return ai.chooseCard(); 
		  }
	    }		
}
