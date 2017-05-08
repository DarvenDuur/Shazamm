package game.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Reiji
 */
public class GuiConfig {
    
    //if true, will launch gui, otherwise will use console
    public static boolean guiMode = false;
    
    public final static String PATH_IMG = "../Graphique/img/";
    
    public final static String AI_MODE_ASK = "Do you wish to play against AI?"
            + " Otherwise you will play with two players.",
            
            GUI_MODE_ASK = "Do you wish to run Shazamm in graphical mode?"
            + "Otherwise the game will run in console mode.";
    
    

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
}