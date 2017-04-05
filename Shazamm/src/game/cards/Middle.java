package game.cards;

import game.Round;

/**
 * Card 5: Middle
 */
public class Middle extends AbstractCard {
    
//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 5
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public Middle(boolean belongPlayer1) {
        super(belongPlayer1, 5);
    }
    
//**************************** OTHER *******************************************
    /**
     * Replace firewall at equal distance of the two players
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round){
        int player1Location = round.getLastPlayerState1().getPosition();
        int player2Location = round.getLastPlayerState2().getPosition();
        
        round.getLastBridge().setFirewallLocation(
                (player1Location + player2Location) / 2);
    }
}
