package game.cards;

import game.Round;

/**
 * Card 4: EndOfRound
 */
public class EndOfRound extends AbstractCard {
    
//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 4
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public EndOfRound(boolean belongPlayer1) {
        super(belongPlayer1, 4);
    }
    
//**************************** OTHER *******************************************
    /**
     * End round and move players 3 tiles away from firewall
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round){
        round.getLastBridge().replacePlayers();
        round.getLastTurn().end();
        round.end();
    }
}
