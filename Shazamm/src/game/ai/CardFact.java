package game.ai;

import game.cards.CardsEnum;

/**
 * Fact representing a card and a kind of information about it 
 *      (if it's available, used last turn by player, by its ennemy, 
 *      or if it's labbeled to use during turn)
 */
public class CardFact extends Fact {
    
    // id of card represented by this
    private final int CARD_ID;
    
    /* 
     * a: available, card available in hand
     * u: use, card to use during the turn
     * s: past self, card played during last turn by self
     * e: past ennemy, card played during last turn by ennemy
     */
    private final char TYPE;
    
    //quick access to correct char
    public static final char AVAILABLE = 'a',
            USE = 'u',
            SELF = 's',
            ENNEMY = 'e';

    /**
     * @param cardID
     *      id of the card to represent represent
     * @param type 
     *      type of information represented
     * @see AIAction.AVAILABLE
     * @see AIAction.USE
     * @see AIAction.SELF
     * @see AIAction.ENNEMY
     */
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.CARD_ID;
        hash = 83 * hash + this.TYPE;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CardFact other = (CardFact) obj;
        if (this.CARD_ID != other.CARD_ID) {
            return false;
        }
        if (this.TYPE != other.TYPE) {
            return false;
        }
        return true;
    }
    
}
