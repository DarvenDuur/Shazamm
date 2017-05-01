
package game.ai;

import game.cards.AbstractCard;
import java.util.HashSet;

/**
 * Contains cards to play and bet to ... bet
 */
public class AIAction {
    //Cards to play (with clones already defined)
    private final HashSet<AbstractCard> CARDS;
    
    //Bet to play
    private final int BET;

    /**
     * @param CARDS
     *      Cards to play this turn
     * @param BET 
     *      Mana to bet this turn
     */
    public AIAction(HashSet<AbstractCard> CARDS, int BET) {
        this.CARDS = CARDS;
        this.BET = BET;
    }
    
    /**
     * @return 
     *      Mana to bet this turn
     */
    public int getBet(){
        return this.BET;
    }
    
    /**
     * @return 
     *      Cards to play this turn
     */
    public HashSet<AbstractCard> getCards(){
        return this.getCards();
    }
}
