/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards.manager;

import static game.Config.HAND_MAX_SIZE;
import game.cards.*;
import java.util.ArrayList;

/**
 *
 * @author darven
 */
public class CardManager {
    
    private CardShuffleList deck; // TO DO
    
    private CardShuffleList discard;// TO DO
    
    private ArrayList<AbstractCard> hand; // TO DO

//***************************** CONSTRUCTOR ************************************    
    
    /**
     * CONSTRUCTOR
     * use default initialisation of deck (see private void initDeck())
     */
    public CardManager() {
        //deck initialisation
        this.deck = new CardShuffleList();
        this.initDeck();
        this.deck.shuffle();
        
        //discard and hand initialisation
        this.discard = new CardShuffleList();
    }
    
//******************************************************************************
    
    /**
     * remove a card from deck to add it to hand
     * @return false if no card can be drawn (hand already full, no card left)
     */
    public boolean drawCard(){
        if (this.hand.size() < HAND_MAX_SIZE){
            AbstractCard card = this.deck.pollFirst();
            this.hand.add(card);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * discard card at index
     * @param index
     *      index of the card to discard, use modulo if out of hand's range
     * @return false if no card can be discarded (hand empty)
     */
    public boolean discardCard(int index){
        if (!this.hand.isEmpty()){
            index = index % this.hand.size();
            AbstractCard card = this.hand.get(index);
            this.hand.remove(index);
            this.discard.add(card);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * remove all cards from discard and put them back in deck, then shuffle the
     *      deck
     */
    public void discardToDeck(){
        this.deck.addAll(this.discard);
        this.discard.clear();
        this.deck.shuffle();
    }
    
    /**
     * @return 
     *      clone of hand
     */
    public ArrayList<AbstractCard> getHand(){
        return (ArrayList<AbstractCard>) this.hand.clone();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        CardManager clone = (CardManager) super.clone();
        clone.deck = (CardShuffleList) clone.deck.clone();
        clone.discard = (CardShuffleList) clone.discard.clone();
        clone.hand = (ArrayList<AbstractCard>) clone.hand.clone();
        return clone;
    }

    /**
     * initialize deck with a single exemplary of each card
     */
    private void initDeck() {
        this.deck.add(new AttackBoost());
        this.deck.add(new Blaze());
        this.deck.add(new Clone());
        this.deck.add(new DoubleDose());
        this.deck.add(new EndOfRound());
        this.deck.add(new Middle());
        this.deck.add(new Mutism());
        this.deck.add(new Recycling());
        this.deck.add(new Rezilliance());
        this.deck.add(new Scrooge());
        this.deck.add(new StockBoost());
        this.deck.add(new SuckBet());
        this.deck.add(new Theft());
        this.deck.add(new WhoWinLose());
    }

}
