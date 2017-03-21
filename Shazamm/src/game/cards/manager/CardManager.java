/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards.manager;

import game.cards.*;
import java.util.ArrayList;
import java.util.LinkedList;
import static game.Config.HAND_REFILL_SIZE;
import static game.Config.SUFFLE_STEPS;
import java.util.Random;

/**
 *
 * @author darven
 */
public class CardManager {
    
    private LinkedList<AbstractCard> deck; //deck, cards available to draw to hand
    
    private LinkedList<AbstractCard> discard;//cards discarded from hand
    
    private ArrayList<AbstractCard> hand; //player's hand

//***************************** CONSTRUCTOR ************************************    
    
    /**
     * CONSTRUCTOR
     * use default initialisation of deck (see private void initDeck())
     */
    public CardManager() {
        //deck initialisation
        this.deck = new LinkedList<>();
        this.initDeck();
        this.shuffleDeck();
        
        //discard and hand initialisation
        this.discard = new LinkedList<>();
    }
    
//***************************** DECK SHUFFLE ***********************************
    
    /**
     * suffle deck by swaping random cards SUFFLE_STEPS times
     */
    private void shuffleDeck(){
        for (int i=0; i < SUFFLE_STEPS; i++) {
            this.swapDeckCards();
        }
    }
    
    /**
     * swap two random cards in deck
     */
    private void swapDeckCards(){
        //generation of two random indexes
        Random random = new Random();
        int firstIndex = random.nextInt(this.deck.size());
        int secondIndex = random.nextInt(this.deck.size());
        
        //swap the cards at the two indexes
        AbstractCard tempCard = this.deck.get(firstIndex);
        this.deck.set(firstIndex, this.deck.get(secondIndex));
        this.deck.set(firstIndex, tempCard);
    }    

//***************************** CARD MANIPULATION ******************************
    
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
    
    /**
     * remove a card from deck to add it to hand
     * @return 
     *      false if no card can be drawn (no card left)
     */
    public boolean drawCard(){
        if (this.deck.isEmpty()){
            return false;
        }else{
            AbstractCard card = this.deck.pollFirst();
            this.hand.add(card);
            return true;
        }
    }
    
    /**
     * refill hand by drawing cards untill hand has the minimam amount of cards
     *      if no cards are left, use discardToDeck() automaticaly
     *      WARNING : HAND_REFILL_SIZE has to be inferior or equal
     *      to the size of the deck
     */
    public void refillHand(){
        while  (this.hand.size() < HAND_REFILL_SIZE){
            //draw cards while hand has less than the minimum amount of cards
            boolean cardsLeft = this.drawCard();
            
            //if no ards are left in the deck
            if (!cardsLeft){
                this.discardToDeck();
            }
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
        this.shuffleDeck();
    }
    
    /**
     * clone cardManager:
     *      clone hand, deck and discard to avoid synchronisation
     * @return
     *      cloned object
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() throws CloneNotSupportedException{
        CardManager clone = (CardManager) super.clone();
        clone.deck = (LinkedList<AbstractCard>) clone.deck.clone();
        clone.discard = (LinkedList<AbstractCard>) clone.discard.clone();
        clone.hand = (ArrayList<AbstractCard>) clone.hand.clone();
        return clone;
    }

//***************************** GETTER *****************************************

    /**
     * @return 
     *      clone of hand
     */
    public ArrayList<AbstractCard> getHand(){
        return (ArrayList<AbstractCard>) this.hand.clone();
    }
}
