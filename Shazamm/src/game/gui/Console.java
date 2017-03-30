/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.Bridge;
import game.PlayerState;
import game.Round;
import game.Turn;
import game.cards.AbstractCard;
import game.cards.CardsEnum;
import static game.cards.CardsEnum.CARDS;
import game.cards.Clone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author darven
 */
public class Console {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static DateFormat DATE_FORMAT =
            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final static Date DATE = new Date();    
    
    
    /**
     * Get input choice from user (with confirmation)
     * @param round
     *      round concerned by query (used to access precedent turns and 
     *      adversary's cards)
     * @param player1
     *      true if player 1 is the one asked
     * @return 
     *      Cards chosen by the user
     */
    public static HashSet<AbstractCard> askCards(Round round, boolean player1){
        HashSet<Integer> acceptedInput = new HashSet<>();
        
        //get player
        PlayerState player = player1 ?
                round.getLastPlayerState1():
                round.getLastPlayerState2();
        
        HashSet<AbstractCard> cards = player.getCardManager().getHand();
        
        //safety mesure for unvalid parameters
        if (cards == null || cards.isEmpty()){
            return new HashSet<>();
        }
        
        do {
            acceptedInput.clear();
            if (round.getLastTurn().isMute()){
                println("Warning, " + CardsEnum.Mutism.getName() + " is active.");
            }
            //print all cards, and get all available IDs
            HashSet<Integer> handIDs = getIDs(cards);
            printCards(handIDs, player.getPlayer().getName() + 
                    ", you can choose the following cards: ", 
                    "No cards can be played (shouldn't appear)");

            //get input
            println("Enter the ID(s) of the card(s) you want to play."
                    + " You can enter any number of IDs, separated by spaces, "
                    + "letters ... :");
            String input = SCANNER.nextLine();

            //filter all integers
            LinkedList<Integer> splitInput = parseAllInt(input);

            //filter input
            //IDs not contained in the cards
            HashSet<Integer> refusedInput = new HashSet<>();
            for (Integer integer : splitInput){
                if (!handIDs.contains(integer)){
                    refusedInput.add(integer);
                }else{
                    acceptedInput.add(integer);
                }
            }

            //show refused input
            if (refusedInput.isEmpty()){
                println("No refused input");
            }else{
                println("Refused inputs:");
                for (Integer integer : refusedInput){
                    println(integer);
                }
            }
            
            //show accepted input and corresponding cards
            printCards(acceptedInput, "Those cards will be chosen:",
                    "No card will be chosen");
            
        }while(!getConfirmation("Do you whant to chose those cards ?"));
        
        //get card from integer
        HashSet<AbstractCard> output = new HashSet<>();
        for (AbstractCard card : cards){
            if (acceptedInput.contains(card.getId())){
                output.add(card);
                
                //if card is a Clone, get cloned card
                if (card instanceof Clone){
                    ((Clone) card).setClone(askClone(round, player1));
                }
            }
        }
        
        return output;
    }
    
    /**
     * Get input choice from user (with confirmation)
     * @param round
     *      round concerned by query (used to access precedent turns and 
     *      adversary's cards)
     * @param player1
     *      true if player 1 is the one asked
     * @return 
     *      Card chosen by the user
     */
    public static AbstractCard askClone(Round round, boolean player1){
        Integer acceptedInput = new Integer(0);
        Bridge bridge = round.getLastBridge();
        
        //get last discarded cards of ennemy player
        HashSet<AbstractCard> cards = !player1 ?
                bridge.getPlayerState1().getCardManager().getLastDiscard():
                bridge.getPlayerState2().getCardManager().getLastDiscard();
        
        //safety mesure for unvalid parameters
        if (cards == null || cards.isEmpty()){
            println("No cards clonable.");
            return null;
        }
        
        do {
            
            acceptedInput = new Integer(0);
            //print all cards, and get all available IDs
            HashSet<Integer> handIDs = getIDs(cards);
            printCards(handIDs, "You can clone the following cards: ", 
                    "No cards can be played (shouldn't appear)");

            //get input
            println("Enter the ID of the card you want to clone."
                    + " You can enter any number of IDs, separated by spaces, "
                    + "letters ... (only the first valid one will be considered):");
            String input = SCANNER.nextLine();

            //filter all integers
            LinkedList<Integer> splitInput = parseAllInt(input);
            
            //filter input according to available cards
            for (Integer integer : splitInput){
                if (handIDs.contains(integer)){
                    acceptedInput = integer;
                    break;
                }
            }
            
            if (acceptedInput.intValue()>0){
                println("This card will be chosen:");
                println(CARDS[acceptedInput - 1].getName());
            }else{
                println("No card will be chosen");
            }
            
        }while(!getConfirmation("Do you whant to chose this card ?"));
        
        //get card from integer
        AbstractCard output = AbstractCard.create(acceptedInput, !player1);
        
        //if card is a Clone, get cloned card
        if (output != null && output instanceof Clone){
            ((Clone) output).setClone(askClone(round, !player1));
        }
        
        return output;
    }
    
    /**
     * allow to ask a Yes/No question
     * @param question
     *      text to display before getting answer; will be folowed by "[Y/n]:"
     * @return 
     *      user's answer
     */
    public static boolean getConfirmation(String question) {
        String answer = "";
        
        while (!answer.equals("y") && !answer.equals("n")){
            println(question + "[Y/n]:");
            answer = SCANNER.nextLine().toLowerCase();
            answer +=answer.trim().isEmpty()? "y" : "";
        }
        return answer.equals("y");
    }

    /**
     * print all cards
     * @param cards
     *      cards to print
     * @param text
     *      text to print if input is not empty
     * @param emptyText 
     *      text to print if input is empty
     */
    private static void printCards(HashSet<Integer> cards, String text,
            String emptyText) {
        //show accepted inputs
        if (cards.isEmpty()){
            println(emptyText);
        }else{
            println(text);
            for (Integer integer : cards){
                println(integer.toString() + ". " + CARDS[integer - 1].getName());
            }
        }
    }
    
    /**
     * get all inputed cards' ID
     * @param cards
     *      cards from wich to get the ID
     * @return 
     *      set of ID
     */
    private static HashSet<Integer> getIDs(Collection<AbstractCard> cards){
        HashSet<Integer> IDs = new HashSet<>();
        for (AbstractCard card : cards){
            IDs.add(card.getId());
        }
        return IDs;
    }

    /**
     * parse all integers from inputed string
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
        while (matcher.find()){
            splitInput.add(Integer.parseInt(matcher.group()));
        }
        return splitInput;
    }

    /**
     * get next inputed line
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
     * get first integer of next inputed line
     * @param string
     *      text to print before getting the input
     * @return 
     *      inputed Integer
     */
    public static Integer getIntInput(String string) {
        LinkedList<Integer> input = new LinkedList<>();
        while(input.isEmpty()){
            input = parseAllInt(getInput(string));
        }
        return input.get(0);
    }
    
    /**
     * return the date and the time current in french format.
     * @return the currentDate 
     */
    public static String getCurrentDate(){
           return DATE_FORMAT.format(DATE);

    }

    /**
     * print the winner of the turn
     * @param turn
     *      turn to analyze
     */
    public static void printWinner(Turn turn) {
        switch (turn.getWinner()){
            case -1:
                println(turn.getBridge().getPlayer1().getName() +
                        " won the turn.");
                break;
                
            case 1:
                println(turn.getBridge().getPlayer2().getName() +
                        " won the turn.");
                break;
                
            default:
                System.out.println("No winner in this turn");
        }
    }

    /**
     * print the winner of the turn
     * @param turn
     *      turn to analyze
     */
    public static void printWinner(Round round) {
        switch (round.getWinner()){
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
    
    /**
     * warps System.out.println
     * @param line 
     */
    public static void println(String line){
        System.out.println(line);
    }
    
    public static void println(Object o){
        System.out.println(o);
    }
}
