/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

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
     * 
     * @param hand
     * @return 
     *      Cards form hand 
     */
    public static ArrayList<Integer> askCards(ArrayList<AbstractCard> hand){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> output = new ArrayList<>();
        
        //safety mesure for unvalid parameters
        if (hand == null || hand.isEmpty()){
            return new ArrayList<>();
        }
        
        do {
            //print all cards, and get all available IDs
            ArrayList<Integer> handIDs = new ArrayList<>();
            System.out.println("You can use the folowing cards: ");
            for (AbstractCard card : hand){
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
            //IDs not contained in the hand
            ArrayList<Integer> refusedInput = new ArrayList<>();
            for (Integer integer : splitInput){
                if (!handIDs.contains(integer)){
                    refusedInput.add(integer);
                }else{
                    output.add(integer);
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
            if (output.isEmpty()){
                System.out.println("No card will be used");
            }else{
                System.out.println("Those cards will be used:");
                for (Integer integer : output){
                    System.out.println(CARDS[integer - 1].getName());
                }
            }
            
        }while(!getConfirmation("Do you whant to use those cards ?"));
        
        return output;
    }
    
    /**
     * allow to ask a Yes/No question
     * @param question
     *      text to display before getting answer; will be folowed by "[Y/n]:"
     * @return 
     *      user's answer
     */
    private static boolean getConfirmation(String question) {
        Scanner sc = new Scanner(System.in);
        String answer = "";
        
        while (!answer.equals("y") && !answer.equals("n")){
            System.out.println(question + "[Y/n]:");
            answer = sc.nextLine().toLowerCase();
        }
        return answer.equals("y");
    }
    
    
    public static void main(String[] args) {
        ArrayList<AbstractCard> hand = new ArrayList<>();
        hand.add(new Mutism());
        askCards(hand);
    }

}
