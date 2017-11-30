package ca.carleton.poker.game.Player;

import org.springframework.web.socket.WebSocketSession;

import ca.carleton.poker.game.GameOption;
import ca.carleton.poker.game.entity.card.Hand;

public abstract class Player {
	private final Hand hand;
	private final WebSocketSession session;
	private final String uid;
	private GameOption lastOption;
	
	   public Player(final WebSocketSession session, String id) {
	        this.session = session;
	        this.hand = new Hand();
	        this.uid = id;
	    }
	   public abstract boolean isReal();
	   public abstract boolean isAI();
	   public String getuid(){
		   return this.uid;
	   }
	    public Hand getHand() {
	        return this.hand;
	    }
	    
	   public WebSocketSession getSession() {
	        return this.session;
	    }

	    public GameOption getLastOption() {
	        return this.lastOption;
	    }

	    public void setLastOption(final GameOption lastOption) {
	        this.lastOption = lastOption;
	    }

	    @Override
	    public boolean equals(final Object rhs) {
	        return rhs instanceof RealPlayer && ((RealPlayer) rhs).getHand().equals(this.hand);
	    }

}
