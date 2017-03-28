/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.Bridge;
import game.PlayerState;
import game.Round;
import game.cards.AbstractCard;
import static game.cards.CardsEnum.CARDS;
import game.cards.Clone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public static ArrayList<AbstractCard> askCards(Round round, boolean player1){
        ArrayList<Integer> acceptedInput = new ArrayList<>();
        
        PlayerState player = player1 ?
                round.getLastPlayerState1():
                round.getLastPlayerState2();
        
        ArrayList<AbstractCard> cards = player.getCardManager().getHand();
        
        //safety mesure for unvalid parameters
        if (cards == null || cards.isEmpty()){
            return new ArrayList<>();
        }
        
        do {
            //print all cards, and get all available IDs
            ArrayList<Integer> handIDs = getIDs(cards);
            printCards(handIDs, player.getPlayer().getName() + 
                    ", you can choose the folowing cards: ", 
                    player.getPlayer().getName() + "No cards can be played (shouldn't appear)"); //CHANGE*******************************************************

            //get input
            System.out.println("Enter the ID(s) of the card(s) you want to play."
                    + " You can enter any number of IDs, separated by spaces, "
                    + "letters ... :");
            String input = SCANNER.nextLine();

            //filter all integers
            ArrayList<Integer> splitInput = parseAllInt(input);

            //filter input
            //IDs not contained in the cards
            ArrayList<Integer> refusedInput = new ArrayList<>();
            for (Integer integer : splitInput){
                if (!handIDs.contains(integer)){
                    refusedInput.add(integer);
                }else{
                    acceptedInput.add(integer);
                }
            }

            //show refused input
            if (refusedInput.isEmpty()){
                System.out.println("No refused input");
            }else{
                System.out.println("Refused inputs:");
                for (Integer integer : refusedInput){
                    System.out.println(integer);
                }
            }
            
            //show accepted input and corresponding cards
            printCards(acceptedInput, "Those cards will be chosen:",
                    "No card will be chosen");
            
        }while(!getConfirmation("Do you whant to chose those cards ?"));
        
        //get card from integer
        ArrayList<AbstractCard> output = new ArrayList<>();
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
        Bridge bridge = round.getSecondLastBridge();
        
        ArrayList<AbstractCard> cards = !player1 ?
                bridge.getPlayerState1().getCardManager().getHand() :
                bridge.getPlayerState2().getCardManager().getHand();
        
        //safety mesure for unvalid parameters
        if (cards == null || cards.isEmpty()){
            System.out.println("No cards clonable.");
            return null;
        }
        
        do {
            //print all cards, and get all available IDs
            ArrayList<Integer> handIDs = getIDs(cards);
            printCards(handIDs, "You can chose the folowing cards: ", 
                    "No cards can be played (shouldn't appear)");

            //get input
            System.out.println("Enter the ID of the card you want to clone."
                    + " You can enter any number of IDs, separated by spaces, "
                    + "letters ... (only the first valid one will be considered):");
            String input = SCANNER.nextLine();

            //filter all integers
            ArrayList<Integer> splitInput = parseAllInt(input);
            
            //filter input according to available cards
            for (Integer integer : splitInput){
                if (handIDs.contains(integer)){
                    acceptedInput = integer;
                    break;
                }
            }
            
            if (acceptedInput.intValue()>0){
                System.out.println("This card will be chosen:");
                System.out.println(CARDS[acceptedInput - 1].getName());
            }else{
                System.out.println("No card will be chosen");
            }
            
        }while(!getConfirmation("Do you whant to chose this card ?"));
        
        //get card from integer
        AbstractCard output = AbstractCard.create(acceptedInput, !player1);
        
        //if card is a Clone, get cloned card
        if (output != null || output instanceof Clone){
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
        Scanner sc = new Scanner(System.in);
        String answer = "";
        
        while (!answer.equals("y") && !answer.equals("n")){
            System.out.println(question + "[Y/n]:");
            answer = sc.nextLine().toLowerCase();
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
    private static void printCards(ArrayList<Integer> cards, String text,
            String emptyText) {
        //show accepted inputs
        if (cards.isEmpty()){
            System.out.println(emptyText);
        }else{
            System.out.println(text);
            for (Integer integer : cards){
                System.out.println(integer.toString() + ". " + CARDS[integer - 1].getName());
            }
        }
    }
    
    /**
     * get all inputed cards' ID
     * @param cards
     *      cards from wich to get the ID
     * @return 
     *      list of ID
     */
    private static ArrayList<Integer> getIDs(ArrayList<AbstractCard> cards){
        ArrayList<Integer> IDs = new ArrayList<>();
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
     *      list of Integers
     */
    private static ArrayList<Integer> parseAllInt(String input) {
        //filter input
        Pattern reg = Pattern.compile("\\d+");
        Matcher matcher = reg.matcher(input);

        //add separate inputed integers
        ArrayList<Integer> splitInput = new ArrayList<>();
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
        System.out.println(string);
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
        System.out.println(string);
        ArrayList<Integer> input = new ArrayList<>();
        while(input.isEmpty()){
            input = parseAllInt(SCANNER.nextLine());
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


}
