/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards.manager;

import game.cards.AbstractCard;
import java.util.ArrayList;

/**
 *
 * @author darven
 */
public class Deck {
    ArrayList<AbstractCard> cards;
    
    /**
     * 
     * @return first card of the deck
     */
    public AbstractCard giveCard(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void shuffle(){
        
    }
    
    private void swapCards(int firstIndex, int secondIndex){
        
    }
    
    public boolean add(AbstractCard card){
        return true;
    }
}
