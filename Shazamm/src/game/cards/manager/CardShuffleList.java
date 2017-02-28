/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards.manager;

import static game.Config.SUFFLE_STEPS;
import game.cards.AbstractCard;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

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
        for (int i=0; i < SUFFLE_STEPS; i++) {
            this.swapCards();
        }
    }
    
    private void swapCards(){
        Random random = new Random();
        int firstIndex = random.nextInt(super.size());
        int secondIndex = random.nextInt(super.size());
        AbstractCard tempCard = this.get(firstIndex);
        this.set(firstIndex, this.get(secondIndex));
        this.set(firstIndex, tempCard);
    }
}
