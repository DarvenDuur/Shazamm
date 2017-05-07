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
    public static final char AVAILABLE = 'a',
            USE = 'u',
            SELF = 's',
            ENNEMY = 'e';

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
