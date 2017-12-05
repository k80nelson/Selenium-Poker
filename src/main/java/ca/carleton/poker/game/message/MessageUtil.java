package ca.carleton.poker.game.message;

/**
 * Stores message strings to use across the application.
 * <p/>
 * Created by Mike on 10/7/2015.
 * - > modified by Cheryl 11/26/2017
 */
public class MessageUtil {

    public enum Message {

        PLAYER_CONNECTED("|CONNECTED|Successfully connected to the game with unique id %s."),
        OTHER_PLAYER_CONNECTED("|OTHER+CONNECTED|%s has connected to the game."),
        PLAYER_DISCONNECTED("|DISCONNECTED|Disconnected to the game with unique id %s."),
        OTHER_PLAYER_DISCONNECTED(
                "|OTHER+DISCONNECTED|%s has disconnected from the game. He will be replaced by an AI if he isn't the admin."),
        ADMIN_SET(
                "|ADMIN|You have been designated the admin for this game. Enter the number of players/AI and open the lobby."),
        NOT_ACCEPTING("|NOT+ACCEPTING|The game isn't accepting connections. You will be disconnected."),
        STARTING_GAME("|GAME+START|The game has started! Please wait for your turn."),
        DEALING_CARDS("|DEALING+CARDS|Updated cards."),
        ADD_PLAYER_CARD("|ADD+PLAYER+CARD|%s"),
        ADD_DEALER_CARD("|ADD+DEALER+CARD|%s"),
        // Send index 0 or 1 so we know which one to fill out, as well as their ID to set.
        ADD_OTHER_PLAYER_CARD("|ADD+OTHER+PLAYER+CARD|%s|%s|%s"),
        PLAYER_VALUE("|PLAYER+VALUE|%s"),
        DEALER_VALUE("|DEALER+VALUE|%s"),
          // index, value
        OTHER_VALUE("|OTHER+VALUE|%s|%s"),
        READY_TO_START("|READY+TO+START|The game is now ready to begin. Press start when ready."),
        OTHER_READY_TO_START("|OTHER+READY+TO+START|The game is now ready to begin. Waiting on admin to start."),
        YOUR_TURN("|YOUR+TURN|It is your turn, please select one of the two available game options."),
        IMPROVE_CARD("|IMPROVE+CARD|Select all cards you want to discard.  Press done to finish turn"),
        SKIPPING("|SKIP|Skipping %s's turn because they are : %s."),
        MOVE_MADE("|OTHER+MOVE|%s made their turn and %s."),
        PROCESSING_AI("|AI+TURN|Processing all AI decisions"),
        PROCESSING_Player("|AI+TURN|All AI players have made their choices. Now processing Real Player decisions."),
        WINNER("|WINNER|%s won with a score of %s!"),
        LOSER("|LOSER|%s lost, better luck next time!"),
        RESET("|RESET|Please wait for the administrator to start the next round."),
        RESET_ADMIN("|RESET+ADMIN|To start another round, press the start button."),
        ALL_QUIT("|ALL+QUIT|The administrator has left. Current sessions will be disconnected.");
        private final String content;

        Message(final String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }
    }

    private static final String SERVER_UID = "Server";

    public static MessageBuilder message(final Message message, final Object... formatArgs) {
        return new MessageBuilder(message.getContent())
                .withFormat(formatArgs)
                .withSender(SERVER_UID);
    }
}
