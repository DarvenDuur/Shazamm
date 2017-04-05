package game.cards;

import game.Round;

/**
 * Card 9: WhoWinLose
 */
public class WhoWinLose extends AbstractCard {
    
//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 9
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public WhoWinLose(boolean belongPlayer1) {
        super(belongPlayer1, 9);
    }
   
//**************************** OTHER *******************************************
    /**
     * Invert firewall movement
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round){
        round.getLastBridge().setInvertWinLose();
    }
}
