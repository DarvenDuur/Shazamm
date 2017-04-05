package game.cards;

import game.Round;

/**
 * Card 11: Resiliance
 */
public class Resiliance extends AbstractCard {
 
//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 11
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public Resiliance(boolean belongPlayer1) {
        super(belongPlayer1, 11);
    }
    
//**************************** OTHER *******************************************
    /**
     * If the wall of fire were to advance towards the player, it does not move.
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round) {
        
        short winner =round.getLastTurn().getWinner();
        
        //if the player 1 use this card
        if(this.isUsedPlayer1()){
            round.getLastBridge().setRezilliancePlayer1(true);
        
        //if the player 2 use this card
        }else{
            round.getLastBridge().setRezilliancePlayer2(true);
        }
    }
}
