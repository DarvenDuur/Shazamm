package game;

import java.util.Date;

/**
 * Contain all constant values that will be used by the program.
 * In later versions, will be loded from a file.
 */
public class Config {
    /* half the maximum total size of the bridge,
     * 0 : only the central portion remains */
    public static final int BRIDGE_MAX_SIZE = 19 / 2;

    // mana value at the begening of the turn
    public static final int START_MANA = 50;

    // number of time two cards are swapped when shuffleDeck() is used
    public static final int SHUFFLE_STEPS = 100;

    // minimal size of the hand at the start of a turn
    public static final int HAND_REFILL_SIZE = 5;

    // number of cards each player draw at the end of each turn
    public static final int END_OF_TURN_DRAW = 3;

    // number of cards each player draw at the first turn of each round
    public static final int FIRST_TURN_DRAW = 5;
    
    public static final Date DATE=new Date();
    
//*************************** BDD **********************************************
    // name of database
    public static final String BDD_NAME = "shazamm";
    
    // username for database
    public static final String BDD_USERNAME = "root";
    
    // user's password
    public static final String BDD_PASSWORD = "root";
    
    // playername for AI player
    public static final String AI_NAME="Ordinateur";
}
