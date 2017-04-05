package game.cards;

import game.Round;

/**
 * Card 13: StockBoost
 */
public class StockBoost extends AbstractCard {

//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 13
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public StockBoost(boolean belongPlayer1) {
        super(belongPlayer1, 13);
    }
    
//**************************** OTHER *******************************************
    /**
     * Mana pool increases by 13 points.
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round) {
        super.getUserPLayer(round).addMana(13);
    }
}
