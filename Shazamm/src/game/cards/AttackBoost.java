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
   
    public AttackBoost(boolean belongPlayer1) {
        this.id = CardsEnum.AttackBoost.getId();
        this.belongPlayer1=belongPlayer1;
    }
    @Override
    
    /**
     * add 7 points of mana to the attack
     */
    protected void apply(Round round){
        PlayerState player =super.getUserPLayer(round);
        player.setPowerAttack(player.getPowerAttack()+7);
    }
}
