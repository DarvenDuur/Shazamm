/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.PlayerState;
import game.Round;
import game.cards.AbstractCard;
import static game.cards.CardsEnum.CARDS;
import game.cards.Clone;
import game.cards.Mutism;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author darven
 */
public class Console {
    
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
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> acceptedInput = new ArrayList<>();
        
        ArrayList<AbstractCard> cards = player1 ?
                round.getLastPlayerState1().getCardManager().getHand() :
                round.getLastPlayerState2().getCardManager().getHand();
        
        //safety mesure for unvalid parameters
        if (cards == null || cards.isEmpty()){
            return new ArrayList<>();
        }
        
        do {
            //print all cards, and get all available IDs
            ArrayList<Integer> handIDs = new ArrayList<>();
            System.out.println("You can chose the folowing cards: ");
            for (AbstractCard card : cards){
                handIDs.add(card.getId());
                System.out.println(String.format("%s. %s",
                        card.getId(), card.getName()));
            }

            //get input
            System.out.println("Enter the ID(s) of the card(s) you want to play."
                    + " You can enter any number of IDs, separated by spaces, "
                    + "letters ... :");
            String input = sc.nextLine();

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

            //show warnings
            if (refusedInput.isEmpty()){
                System.out.println("No refused input");
            }else{
                System.out.println("Refused inputs:");
                for (Integer integer : refusedInput){
                    System.out.println(integer);
                }
            }
            
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
        Scanner sc = new Scanner(System.in);
        Integer acceptedInput = new Integer(0);
        
        ArrayList<AbstractCard> cards = !player1 ?
                round.getLastPlayerState1().getCardManager().getHand() :
                round.getLastPlayerState2().getCardManager().getHand();
        
        //safety mesure for unvalid parameters
        if (cards == null || cards.isEmpty()){
            return null;
        }
        
        do {
            //print all cards, and get all available IDs
            ArrayList<Integer> handIDs = new ArrayList<>();
            System.out.println("You can chose the folowing cards: ");
            for (AbstractCard card : cards){
                handIDs.add(card.getId());
                System.out.println(String.format("%s. %s",
                        card.getId(), card.getName()));
            }

            //get input
            System.out.println("Enter the ID of the card you want to clone."
                    + " You can enter any number of IDs, separated by spaces, "
                    + "letters ... (only the first valid one will be considered):");
            String input = sc.nextLine();

            //filter all integers
            ArrayList<Integer> splitInput = parseAllInt(input);

            //filter input
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
     * 
     * @return 
     */
    public static int askBet(){
        Scanner sc = new Scanner(System.in);
        
        String input = "";
        
        while (input.isEmpty()){
            //get input
            System.out.println("Enter a bet:");
            input = sc.nextLine();
            
            //filter input
            Pattern reg = Pattern.compile("\\d+");
            Matcher matcher = reg.matcher(input);
            
            input = matcher.group();
        }
        
        //return first found number
        return Integer.parseInt(input);
    }

    private static void printCards(ArrayList<Integer> cards, String text,
            String emptyText) {
        //show accepted inputs
        if (cards.isEmpty()){
            System.out.println(emptyText);
        }else{
            System.out.println(text);
            for (Integer integer : cards){
                System.out.println(CARDS[integer - 1].getName());
            }
        }
    }

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

}
