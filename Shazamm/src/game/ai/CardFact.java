package game.ai;

import game.cards.CardsEnum;

public class CardFact extends Fact {
    private final int CARD_ID;
    
    /* 
     * a: available, card available in hand
     * u: use, card to use during the turn
     * s: past self, card played during last turn by self
     * e: past ennemy, card played during last turn by ennemy
     */
    private final char TYPE;

    public CardFact(int cardID, char type) {
        super(CardsEnum.CARDS[cardID - 1].getName());
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
