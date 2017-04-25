/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ai.fact;

import game.cards.CardsEnum;

/**
 *
 * @author darven
 */
public class CardFact extends Fact {
    private final int CARD_ID;
    
    /* 
     * a: for available
     * u: for use
     * s: for past self
     * e: for past ennemy
     */
    private final char TYPE;

    public CardFact(int cardID, char type) {
        super(CardsEnum.CARDS[cardID + 1].getName());
        this.CARD_ID = cardID;
        this.TYPE = type;
    }
    
    /**
     * @return the CARD_ID
     */
    public int getCardID() {
        return CARD_ID;
    }

    /**
     * @return the TYPE
     */
    public char getType() {
        return TYPE;
    }
    
}
