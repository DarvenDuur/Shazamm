package game.gui;

import game.Bridge;
import game.Config;
import game.PlayerState;
import game.Round;
import game.Turn;
import game.cards.AbstractCard;
import game.cards.CardsEnum;
import static game.cards.CardsEnum.CARDS;
import game.cards.Clone;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class Console {
    // Scanner for all user inputs
    private final static Scanner SCANNER = new Scanner(System.in);

//***************************** PRINT ONLY *************************************
    /**
     * wraps System.out.println
     * @param line
     *      line to print
     */
    public static void println(String line) {
        System.out.println(line);
    }

    /**
     * wraps System.out.println
     * @param o
     *      object to print
     */
    public static void println(Object o) {
        System.out.println(o);
    }

    /**
     * prints a number (see Config) of empty line to simulate a console clearing
     */
    public static void clear() {
        for (int i = 0; i < 50; i++) {
            println("");
        }
    }
    
    /**
     * print all cards and a specific message according to the number of cards
     * @param cards 
     *      cards to print
     * @param text 
     *      text to print if input is not empty
     * @param emptyText 
     *      text to print if input is empty
     */
    public static void printCards(HashSet<Integer> cards, String text,
            String emptyText) {
        //show accepted inputs
        if (cards.isEmpty()) {
            println(emptyText);
        } else {
            println(text);
            for (Integer integer : cards) {
                println(integer.toString() + ". " + CARDS[integer - 1].getName());
            }
        }
    }

    /**
     * print integers as refused, if empty set print specific message
     * @param refusedInput 
     *      integers to print ass refused
     */
    private static void printRefused(HashSet<Integer> refusedInput) {
        printCards(refusedInput, Config.REFUSED_INPUT[0], 
                Config.REFUSED_INPUT[1]);
    }

    /**
     * Print card corresponding to an ID as chosen card
     * @param acceptedInput 
     *      ID of the card to print
     */
    private static void printChosenCard(Integer acceptedInput) {
        HashSet<Integer> set = new HashSet<>();
        set.add(acceptedInput);
        printCards(set, Config.CHOSEN_CARD[0],  Config.CHOSEN_CARD[1]);
    }

    /**
     * print the winner of the turn
     * @param turn
     *      turn to analyze
     */
    public static void printWinner(Turn turn) {
        switch (turn.getWinner()) {
            case -1:
                println(turn.getBridge().getPlayer1().getName()
                        + " won the turn.");
                break;

            case 1:
                println(turn.getBridge().getPlayer2().getName()
                        + " won the turn.");
                break;

            default:
                System.out.println("No winner in this turn");
        }
    }

    /**
     * print the winner of the round
     * @param round
     *      round to analyze
     */
    public static void printWinner(Round round) {
        switch (round.getWinner()) {
            case -1:
                println(round.getLastBridge().getPlayer1().getName()
                        + " won the round.");
                break;

            case 1:
                println(round.getLastBridge().getPlayer2().getName()
                        + " won the round.");
                break;

            default:
                println("No winner in this round");
        }
    }
    
//***************************** PRINT AND INPUT ********************************
    /**
     * Get next inputed line from scanner after printing string
     * @param string 
     *      text to print before getting the input
     * @return 
     *      inputed line
     */
    public static String getInput(String string) {
        println(string);
        return SCANNER.nextLine();
    }

    /**
     * Get first integer of next inputed line
     * @param string 
     *      text to print before getting the input
     * @return 
     *      inputed Integer
     */
    public static Integer getIntInput(String string) {
        LinkedList<Integer> input = new LinkedList<>();

        while (input.isEmpty()) {
            input = parseAllInt(getInput(string));
        }
        return input.get(0);
    }
    
    /**
     * Get input cards from user (with confirmation)
     * @param round 
     *      round concerned by query 
     *      (used to access precedent turns and adversary's cards)
     * @param player1
     *      true if player 1 is the one asked
     * @return 
     *      Cards chosen by the user
     */
    public static HashSet<AbstractCard> askCards(Round round, boolean player1) {

        PlayerState player = round.getLastPlayerState(player1);
        HashSet<Integer> acceptedInput = new HashSet<>();

        HashSet<AbstractCard> cards = player.getCardManager().getHand();
        //safety mesure for unvalid parameters
        if (cards == null || cards.isEmpty()) {
            return new HashSet<>();
        }

        do {
            acceptedInput.clear();

            if (round.getLastTurn().isMute()) {
                println(String.format(Config.MUTISM_WARNING, CardsEnum.Mutism.getName()));
            }
            //print all cards, and get all available IDs
            HashSet<Integer> handIDs = getIDs(cards);
            printCards(handIDs, String.format(Config.AVAILABLE_CARDS[0], 
                    player.getPlayer().getName()), Config.AVAILABLE_CARDS[1]);

            String input = getInput(Config.CARDS_INPUT);

            //filter all integers
            LinkedList<Integer> splitInput = parseAllInt(input);
            //filter input IDs not contained in the cards
            HashSet<Integer> refusedInput = new HashSet<>();

            filterIntegers(splitInput, acceptedInput, refusedInput, handIDs);

            printRefused(refusedInput);
            //show accepted input and corresponding cards
            printCards(acceptedInput, Config.CHOSEN_CARDS[0],
                    Config.CHOSEN_CARDS[1]);

        } while (!getConfirmation(Config.CARDS_CONFFIRM));

        return getCardFromInteger(cards, acceptedInput, round, player1);
    }
    
    /**
     * Get input clone from user (with confirmation)
     * @param round 
     *      round concerned by query 
     *      (used to access precedent turns and adversary's cards)
     * @param player1 
     *      true if player 1 is the one asked
     * @return 
     *      Card chosen by the user
     */
    public static AbstractCard askClone(Round round, boolean player1) {

        Bridge bridge = round.getLastBridge();

        //get last discarded cards of ennemy player
        HashSet<AbstractCard> cards = !player1
                ? bridge.getPlayerState1().getCardManager().getLastDiscard()
                : bridge.getPlayerState2().getCardManager().getLastDiscard();

        //safety mesure for unvalid parameters
        if (cards == null || cards.isEmpty()) {
            println(Config.AVAILABLE_CLONES[1]);
            return null;
        }

        Integer acceptedInput;
        do {
            acceptedInput = new Integer(0);
            //print all cards, and get all available IDs
            HashSet<Integer> handIDs = getIDs(cards);
            printCards(handIDs, Config.AVAILABLE_CLONES[0],
                    Config.AVAILABLE_CLONES[1]);

            //get input
            String input = getInput(Config.CLONE_INPUT);

            //filter all integers
            LinkedList<Integer> splitInput = parseAllInt(input);

            //filter input according to available cards
            for (Integer integer : splitInput) {
                if (handIDs.contains(integer)) {
                    acceptedInput = integer;
                    break;
                }
            }

            printChosenCard(acceptedInput);

        } while (!getConfirmation(Config.CLONE_CONFFIRM));

        //get card from integer
        AbstractCard output = AbstractCard.create(acceptedInput, !player1);

        //if card is a Clone, get cloned card
        if (output != null && output instanceof Clone) {
            ((Clone) output).setClone(askClone(round, !player1));
        }
        return output;
    }

    /**
     * Ask a Yes/No question
     * @param question 
     *      Text to display before getting answer.
     *      Will be folowed by "[Y/n]:"
     * @return 
     *      user's answer (true = yes)
     */
    public static boolean getConfirmation(String question) {
        String answer = "";

        while (!answer.equalsIgnoreCase(Config.Y_N_CHAR[0])
                && !answer.equalsIgnoreCase(Config.Y_N_CHAR[1])) {
            println(question + "[" + Config.Y_N_CHAR[0].toUpperCase() + "/" 
                    + Config.Y_N_CHAR[1].toLowerCase() + "]:");
            answer = SCANNER.nextLine();
            answer += answer.trim().isEmpty() && Config.Y_N_DIRECT_ACCEPT ?
                    Config.Y_N_CHAR[0] : "";
        }
        return answer.equalsIgnoreCase(Config.Y_N_CHAR[0]);
    }

//***************************** OTHER ******************************************
    
    /**
     * Filter integers between valid and not valid, according to appartenance to
     *      valid parameter
     * @param integers
     *      integers to filter
     * @param accepted
     *      set to which add accepted integers
     * @param refused
     *      set to which add refused integers
     * @param valid
     *      acceptable integers
     */
    private static void filterIntegers(LinkedList<Integer> integers,
            HashSet<Integer> accepted, HashSet<Integer> refused,
            HashSet<Integer> valid) {

        for (Integer integer : integers) {
            if (!valid.contains(integer)) {
                refused.add(integer);
            } else {
                accepted.add(integer);
            }
        }
    }

    /**
     * Extract cards corresponding to parameter integers
     * @param cards
     *      cards available
     * @param integers
     *      integers to transform to cards
     * @param round
     *      round concerned by query 
     *      (used to access precedent turns and adversary's cards)
     * @param player1
     *      cards' user
     * @return
     *      user's cards corresponding to integers
     */
    private static HashSet<AbstractCard> getCardFromInteger(
            HashSet<AbstractCard> cards, HashSet<Integer> integers, Round round,
            boolean player1) {

        HashSet<AbstractCard> output = new HashSet<>();
        for (AbstractCard card : cards) {
            if (integers.contains(card.getId())) {
                output.add(card);

                //if card is a Clone card, get cloned card
                if (card instanceof Clone) {
                    ((Clone) card).setClone(askClone(round, player1));
                }
            }
        }
        return output;
    }

    /**
     * Extract IDs from cards
     * @param cards 
     *      cards from wich to get the ID
     * @return 
     *      set of IDs
     */
    private static HashSet<Integer> getIDs(Collection<AbstractCard> cards) {
        HashSet<Integer> IDs = new HashSet<>();
        for (AbstractCard card : cards) {
            IDs.add(card.getId());
        }
        return IDs;
    }

    /**
     * Parse all integers from inputed string
     * @param input
     *      string to filter
     * @return 
     *      set of Integers
     */
    private static LinkedList<Integer> parseAllInt(String input) {
        //filter input
        Pattern reg = Pattern.compile("\\d+");
        Matcher matcher = reg.matcher(input);

        //add separate inputed integers
        LinkedList<Integer> splitInput = new LinkedList<>();
        while (matcher.find()) {
            splitInput.add(Integer.parseInt(matcher.group()));
        }
        return splitInput;
    }

    /**
     * return the current date and time in format spacified in DATE_FORMAT.
     * @return 
     *      the current date
     */
    public static String getCurrentDate() {
        return Config.DATE_FORMAT.format(new Date());
    }
}
