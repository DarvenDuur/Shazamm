/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards.manager;

import static game.Config.HAND_MAX_SIZE;
import game.cards.AbstractCard;

/**
 *
 * @author darven
 */
public class CardManager {
    
    private CardShuffleList deck; // TO DO
    
    private CardShuffleList discard;// TO DO
    
    private CardShuffleList hand; // TO DO

//***************************** CONSTRUCTOR ************************************    
    
    // TO DO
    public CardManager() {
        deck.shuffle();
        discard.shuffle();
        hand.shuffle();
    }
    
//******************************************************************************
    
    /**
     * remove a card from deck to add it to hand
     * @return false if no card can be drawn (hand already full, no card left)
     */
    public boolean drawCard(){
        if (this.hand.size() < HAND_MAX_SIZE){
            AbstractCard card = deck.getFirst();
            deck.removeFirst();
            hand.add(card);
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        CardManager clone = (CardManager) super.clone();
        clone.deck = (CardShuffleList) clone.deck.clone();
        clone.discard = (CardShuffleList) clone.discard.clone();
        clone.hand = (CardShuffleList) clone.hand.clone();
        return clone;
    }

}
