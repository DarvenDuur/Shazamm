package game.cards;

import game.Config;
import game.PlayerState;
import game.Round;
import game.gui.Console;

/**
 * Card 6: Recycling
 */
public class Recycling extends AbstractCard {
    
//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 6
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public Recycling(boolean belongPlayer1) {
        super(belongPlayer1, 6);
    }
    
//**************************** OTHER *******************************************
    /**
     * Ask to the player if he want to increment attack power by five,
     *      if he refuses decrements it by five.
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round){
        PlayerState ownerPlayer=super.getUserPLayer(round);
        if(Console.getConfirmation(String.format(Config.RECYCLE_CONFIRM, ownerPlayer.getPlayer().getName()))){
            ownerPlayer.setAttackPower(ownerPlayer.getAttackPower() + 5);
        }
        ownerPlayer.setAttackPower(ownerPlayer.getAttackPower() - 5);    
    }
}
