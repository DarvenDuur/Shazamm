package game;

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

//**************************** TEXT USED FOR LOG PRINTING **********************
    /* for eaach of folowing parameters, please refer to Log.toString and 
     * overwriting methodes */
    public static final String ATTACK_POWER = "Force d'attaque:";

    public static final String BET = "Mise:";

    public static final String BET_OVERVIEW_STRING = "BILAN DES MISES";

    public static final String BET_STRING = "a parié.";

    public static final String WINNER = "Gagnant: ";

    public static final String MANA_SPENT = "Perte de mana:";

    public static final String MANA_STOCK_END = "Réserve après:";

    public static final String MANA_STOCK_START = "Réserve avant:";

    public static final String ROUND_STRING = "MANCHE N° ";

    public static final String SPELLS = "Sorts: ";

    public static final String TURN_STRING = "TOUR N° ";

    public static final String TURN_WINNER = "Le gagnant du tour est ";

//**************************** CONSTRUCOR AND LOADING [WIP] ********************
    /**
     * @deprecated 
     * @param aInt
     * @param aInt0
     * @param aInt1
     * @param aInt2
     * @param aInt3
     * @param aInt4
     */
    public Config(int aInt, int aInt0, int aInt1, int aInt2, int aInt3, int aInt4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @deprecated
     */
    public Config() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
