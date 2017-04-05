package game;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    /* For eaach of folowing parameters, please refer to Log.toString and 
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
    
    public static final String DRAW = "Egalité";
    
//**************************** TEXT USED FOR CONSOLE ***************************
    // see askCards()
    public static final String MUTISM_WARNING = "Warning, %s is active.";
    
    // see askCards()
    public static final String CARDS_INPUT = 
            "Enter the ID(s) of the card(s) you want to play. "
            + "You can enter any number of IDs, separated by spaces, "
            + "letters ... :";
    
    // see askClone()
    public static final String CLONE_INPUT = 
            "Enter the ID of the card you want to clone. "
            + "You can enter any number of IDs, separated by spaces, "
            + "letters ... (only the first valid one will be considered):";
    
    // see askCards()
    public static final String CARDS_CONFIRM = 
            "Do you whant to use those cards ?";
    
    // see askClone()
    public static final String CLONE_CONFIRM = 
            "Do you whant to clone this card ?";
    
    // see Recycling.apply()
    public static final String RECYCLE_CONFIRM = 
            "%s, do you want to add yourself 5 attack power "
            + "(if you refuse, you will lose 5 attack power)?";
    
    // see printRefused()
    public static final String[] REFUSED_INPUT = 
            {"Refused inputs:", "No refused input"};
    
    // see askCards()
    public static final String[] AVAILABLE_CARDS = 
            {"%s, you can choose the following cards: ", 
                "No cards can be played (shouldn't appear)"};
    
    // see askCards()
    public static final String[] AVAILABLE_CLONES = 
            {"You can clone the following cards: ", 
                "No cards can be cloned"};
    
    // see askCards()
    public static final String[] CHOSEN_CARDS = 
            {"Those cards will be chosen:", "No card will be chosen"};
    
    // see printChosenCard()
    public static final String[] CHOSEN_CARD = 
            {"This card will be chosen:", "No card will be chosen"};
    
    // Date format for printing
    public static final DateFormat DATE_FORMAT = 
            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    // see getConfirmation()
    public static final String[] Y_N_CHAR = {"y","n"};
    
    // see getConfirmation(), if true, can press enter dirrectly to accept
    public static final boolean Y_N_DIRECT_ACCEPT = true;

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
