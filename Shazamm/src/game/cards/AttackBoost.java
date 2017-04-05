package game.cards;

import game.PlayerState;
import game.Round;

/**
 * Card 7: Mutism
 */
public class AttackBoost extends AbstractCard {
    
//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 7
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
    public AttackBoost(boolean belongPlayer1) {
        super(belongPlayer1, 7);
    }
    
//**************************** OTHER *******************************************
    /**
     * add 7 points of mana to the attack
     */
    @Override
    protected void apply(Round round){
        PlayerState player =super.getUserPLayer(round);
        player.setAttackPower(player.getAttackPower()+7);
    }
}
