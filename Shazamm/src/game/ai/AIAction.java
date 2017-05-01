
package game.ai;

import game.cards.AbstractCard;
import java.util.HashSet;

/**
 * Contains cards to play and bet to ... bet
 */
public class AIAction {
    private final HashSet<AbstractCard> CARDS;
    private final int BET;

    public AIAction(HashSet<AbstractCard> CARDS, int BET) {
        this.CARDS = CARDS;
        this.BET = BET;
    }
    
    public int getBet(){
        return this.BET;
    }
    public HashSet<AbstractCard> getCards(){
        return this.getCards();
    }
}
