/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.Bridge;
import game.PlayerState;
import game.Round;

/**
 *
 * @author darven
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
