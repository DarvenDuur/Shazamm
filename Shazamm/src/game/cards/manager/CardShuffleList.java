/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards.manager;

import game.cards.AbstractCard;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author darven
 */
public class CardShuffleList extends LinkedList<AbstractCard>{

    public CardShuffleList() {
        super();
    }

    public CardShuffleList(Collection<AbstractCard> collection) {
        super();
        this.addAll(collection);
    }
    
    public void shuffle(){
        
    }
    
    private void swapCards(int firstIndex, int secondIndex){
        
    }
}
