
package game.ai;

import game.Config;
import game.Turn;
import game.cards.AbstractCard;
import game.cards.CardManager;
import game.cards.Clone;
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
        return this.CARDS;
    }

//***************************** asAIAction *************************************
    public AIAction(FactBase factBase, Turn turn) {
        int availableMana = turn.getPlayerState(false).getMana();
        this.BET = factBase.extractBet(availableMana);
        this.CARDS = new HashSet<>();
        this.extractCards(turn, factBase);
    }

    private void extractClone(Turn turn, FactBase factBase, int clone) {
        //if a card is cloned
        if (clone != 0) {
            CardManager botCardManager = turn.getPlayerState(false).getCardManager();
            HashSet<AbstractCard> botCards = botCardManager.getHand();
            
            CardManager playerCardManager = turn.getPlayerState(true).getCardManager();
            HashSet<AbstractCard> playerPlayedCards = playerCardManager.getLastDiscard();
            
            Clone cloneCard = (Clone) extractCard(2, botCards);
            AbstractCard clonedCard;
            
            //if cloned card is from a double clone
            if (clone < 0) {
                HashSet<AbstractCard> botPlayedCards = botCardManager.getLastDiscard();
                clonedCard = extractCard(2, playerPlayedCards);
                
                AbstractCard secondClonedCard = extractCard(-clone, botPlayedCards);
                clonedCard.setClone(secondClonedCard);
                
            //if cloned card from a single clone
            } else {
                clonedCard = extractCard(clone, playerPlayedCards);
            }
            
            cloneCard.setClone(clonedCard);
            this.CARDS.add(cloneCard);
        }
    }

    private AbstractCard extractCard(int id, HashSet<AbstractCard> availableCards) {
        for (AbstractCard card : availableCards) {
            if (card.getId() == id) {
                return card;
            }
        }
        return null;
    }

    private void extractCards(Turn turn, FactBase factBase) {
        int clone = factBase.getClone();
        int id;
        
        CardManager botCardManager = turn.getPlayerState(false).getCardManager();
        HashSet<AbstractCard> botCards = botCardManager.getHand();
        
        for (Fact fact : factBase) {
            if (fact instanceof CardFact) {
                CardFact cardFact = (CardFact) fact;
                
                if (cardFact.getType() == CardFact.USE) {
                    id = cardFact.getCardID();
                    
                    //if card is not a cloned card
                    if (id != clone && id != clone) {
                        this.CARDS.add(this.extractCard(id, botCards));
                    }
                }
            }
        }
        this.extractClone(turn, factBase, clone);
    }
}
