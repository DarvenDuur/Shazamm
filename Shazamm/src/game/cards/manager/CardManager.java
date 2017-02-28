/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards.manager;

/**
 *
 * @author darven
 */
public class CardManager {
    CardShuffleList deck;
    CardShuffleList discard;
    CardShuffleList hand;
    
    /**
     * remove a card from deck to add it to hand
     * @return false if no card can be drawn (hand already full, no card left)
     */
    public boolean drawCard(){
        return true;
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
