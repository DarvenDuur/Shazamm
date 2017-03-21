/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.Bridge;
import game.Round;

/**
 *
 * @author darven
 */
public class AttackBoost extends AbstractCard {
    
    
    @Override
    /**
     * add 7 points of mana to the attack
     */
    public void apply(Round round){
        super.getOwnerPLayer(round).setBet(13);
    }
}
