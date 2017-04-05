package game.cards;

import game.PlayerState;
import game.Round;

/**
 * Card 12: Scrooge
 */
public class Scrooge extends AbstractCard {

//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 12
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public Scrooge(boolean belongPlayer1) {
        super(belongPlayer1, 12);
    }
    
//**************************** OTHER *******************************************
    /**
     * If this turn is lost, the bet is not removed from my Mana reserve.
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round) {
        if ((this.isUsedPlayer1() && round.getLastTurn().getWinner() == -1) || 
                (!this.isUsedPlayer1() && round.getLastTurn().getWinner() == 1)){
            
            PlayerState player = super.getUserPLayer(round);
            
            // add value of the bet to mana pool
            player.addMana(super.getUserPLayer(round).getBet());
        }
    }
}
