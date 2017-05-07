package game.ai;

import game.Turn;
import game.cards.AbstractCard;
import game.cards.CardManager;
import game.cards.Clone;
import java.util.HashSet;

/**
 * Contains cards to play and bet to bet
 */
public class AIAction {
    //Cards to play (with clones already defined)
    private final HashSet<AbstractCard> CARDS;
    
    //Bet to play
    private final int BET;

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

    /**
     * Generate AIAction from fact base and turn
     * @param factBase
     *      fact base to analyse
     * @param turn 
     *      reference turn for cards
     */
    public AIAction(FactBase factBase, Turn turn) {
        //extract mana
        int availableMana = turn.getPlayerState(false).getMana();
        this.BET = factBase.extractBet(availableMana);
        
        //extract cards
        this.CARDS = new HashSet<>();
        this.extractCards(turn, factBase);
    }

    /**
     * extract cloned card if any
     * @param turn
     *      turn from wich extract the cards
     * @param clone 
     *      cloned card id (if negative connsider double clone)
     */
    private void extractClone(Turn turn, int clone) {
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

    /**
     * extract card from given set of card
     * @param id
     *      id of the card to extract
     * @param availableCards
     *      available cards
     * @return 
     *      extracted card
     */
    private AbstractCard extractCard(int id, HashSet<AbstractCard> availableCards) {
        for (AbstractCard card : availableCards) {
            if (card.getId() == id) {
                return card;
            }
        }
        return null;
    }

    /**
     * extract all cards from turn depending on fact base
     * @param turn
     *      turn from wich extract the cards
     * @param factBase 
     *      fact base to analyse
     */
    private void extractCards(Turn turn, FactBase factBase) {
        int clone = factBase.getClone();
        int id;
        
        CardManager botCardManager = turn.getPlayerState(false).getCardManager();
        HashSet<AbstractCard> botCards = botCardManager.getHand();
        
        // browse all fact for CardFacts
        for (Fact fact : factBase) {
            if (fact instanceof CardFact) {
                CardFact cardFact = (CardFact) fact;
                
                // if fact describes the use of a card
                if (cardFact.getType() == CardFact.USE) {
                    id = cardFact.getCardID();
                    
                    /* if card is not a cloned card, it's added dirrectly to the 
                    cards to play */
                    if (id != clone && id != clone) {
                        this.CARDS.add(this.extractCard(id, botCards));
                    }
                }
            }
        }
        
        // extract cloned card if any
        this.extractClone(turn, clone);
    }
}
