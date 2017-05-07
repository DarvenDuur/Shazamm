package game.cards;

import game.BotPlayer;
import game.Config;
import game.PlayerState;
import game.Round;
import game.gui.Console;
import game.gui.GuiConfig;

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
     * @deprecated 
     */
    @Override
    protected void apply(Round round){
        PlayerState userPlayer = super.getUserPLayer(round);
        int availableMana = userPlayer.getMana();
        
        //get use mode from player
        boolean confirmation;
        if (userPlayer.getPlayer() instanceof BotPlayer){
            throw new UnsupportedOperationException("no AI for confirmation");
            //confirmation = ;
            
        } else {
            //graphical mode
            if (GuiConfig.guiMode) {
                //throw new UnsupportedOperationException("no gui for confirmation");
                confirmation = true;
                
            } else {
                confirmation = Console.getConfirmation(String.format(
                        GuiConfig.RECYCLE_CONFIRM, 
                        userPlayer.getPlayer().getName()));
            }
        }
        
        if(availableMana >= 5 && confirmation){
            userPlayer.setBet(userPlayer.getBet() + 5);
            userPlayer.addMana(-5);
        }
        userPlayer.setBet(availableMana - 5);
        userPlayer.addMana(5);
        
        //update attack power
        userPlayer.setAttackPower(userPlayer.getBet());  
    }
}
