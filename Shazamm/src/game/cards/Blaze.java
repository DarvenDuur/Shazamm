package game.cards;

import game.Round;

/**
 * Card 10: Blaze
 */
public class Blaze extends AbstractCard {
    
 //**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 10
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public Blaze(boolean belongPlayer1) {
        super(belongPlayer1, 10);
    }
   
//**************************** OTHER *******************************************
    /**
     * Double firewall movement, no effect if it does not move
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round) {
        round.getLastBridge().setBlaze();
    }
}
