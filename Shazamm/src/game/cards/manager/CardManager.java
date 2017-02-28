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
    
    private CardShuffleList deck; // TO DO
    
    private CardShuffleList discard;// TO DO
    
    private CardShuffleList hand; // TO DO

//***************************** CONSTRUCTOR ************************************    
    
    // TO DO
    public CardManager() {
    }
    
//******************************************************************************
    
    /**
     * remove a card from deck to add it to hand
     * @return false if no card can be drawn (hand already full, no card left)
     */
    public boolean drawCard(){
        throw new UnsupportedOperationException("drawCard not supproted yet");
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
