/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.PlayerState;
import game.cards.AbstractCard;
import static game.cards.CardsEnum.CARDS;
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
     * @param cards
     *      Cards available for choice
     * @return 
     *      Cards chosen by the user
     */
    public static ArrayList<AbstractCard> askCards(ArrayList<AbstractCard> cards){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> acceptedInput = new ArrayList<>();
        
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

            //filter input
            Pattern reg = Pattern.compile("\\d+");
            Matcher matcher = reg.matcher(input);

            //add separate inputed integers
            ArrayList<Integer> splitInput = new ArrayList<>();
            while (matcher.find()){
                splitInput.add(Integer.parseInt(matcher.group()));
            }

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
            
            //show confirmation
            if (acceptedInput.isEmpty()){
                System.out.println("No card will be chosen");
            }else{
                System.out.println("Those cards will be chosen:");
                for (Integer integer : acceptedInput){
                    System.out.println(CARDS[integer - 1].getName());
                }
            }
            
        }while(!getConfirmation("Do you whant to chose those cards ?"));
        
        //get card from integer
        ArrayList<AbstractCard> output = new ArrayList<>();
        for (AbstractCard card : cards){
            if (acceptedInput.contains(card.getId())){
                output.add(card);
            }
        }
        
        return output;
    }
    
    /**
     * Warps askCards(hand) and displays 
     * @param playerState
     *      PlayerState of player to ask
     * @return 
     *      Cards from hand chosen by the user
     * @see askCards(hand)
     */
    public static ArrayList<AbstractCard> askCards(PlayerState playerState){
        
        ArrayList<AbstractCard> hand = playerState.getCardManager().getHand();
        
        //only if hand is not empty
        if (!hand.isEmpty()){
            String.format("Player %s (%s), please chose a card from your hand.",
                    playerState.getPlayer().getColor(),
                    playerState.getPlayer().getName());
            
            return askCards(hand);
            
        }else{
            return new ArrayList<>();
        }
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
    
    public static void main(String[] args) {
        ArrayList<AbstractCard> hand = new ArrayList<>();
        hand.add(new Mutism());
        askCards(hand);
    }

}