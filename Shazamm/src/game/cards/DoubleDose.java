package game.cards;

import game.PlayerState;
import game.Round;

/**
 * Card 8: DoubleDose
 */
public class DoubleDose extends AbstractCard {
    
//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 8
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public DoubleDose(boolean belongPlayer1) {
        super(belongPlayer1, 8);
    }
   
//**************************** OTHER *******************************************
    /**
     * Double the user's attack power
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */ 
    @Override
    protected void apply(Round round){
        PlayerState player = super.getUserPLayer(round);
        player.setAttackPower(player.getAttackPower() * 2);
    }
}
