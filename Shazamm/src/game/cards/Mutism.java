package game.cards;

import game.Round;

/**
 * Card 1: Mutism
 */
public class Mutism extends AbstractCard {

//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 1
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public Mutism(boolean belongPlayer1) {
        super(belongPlayer1, 1);
    }
    
//**************************** OTHER *******************************************
    /**
     * Mute the round, preventing all other cards to be applied
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round){
        round.getLastTurn().setMute();
    }
}
