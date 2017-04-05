package game.cards;

import game.Round;

/**
 * Card 3: Theft
 */
public class Theft extends AbstractCard {
    
//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 3
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public Theft(boolean belongPlayer1) {
        super(belongPlayer1, 3);
    }
   
//**************************** OTHER *******************************************
    /**
     * Enable theft for user player, putting all cards used by the ennemy under 
     *      user's control
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round) {
        if (this.isUsedPlayer1()){
            round.getLastTurn().setPlayer1Theft();
        }else{
            round.getLastTurn().setPlayer2Theft();
        }
    }
}
