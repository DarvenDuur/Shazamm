package game.cards;

import game.Round;

/**
 * Card 14: SuckBet
 */
public class SuckBet extends AbstractCard {

//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 14
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public SuckBet(boolean belongPlayer1) {
        super(belongPlayer1, 14);
    }
    
//**************************** OTHER *******************************************
    /**
     * The mana pool increases by the amount of the opponent's bet.
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round) {   
        super.getUserPLayer(round).addMana(
                super.getNotUserPlayer(round).getBet());
    }
}
