/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.PlayerState;
import game.Round;
import game.gui.Console;

/**
 *
 * @author darven
 */
public class Recycling extends AbstractCard {
    
    
    
    
    /**
     * ask to the player if he want to increment by five else decrements by five 
     * @param round 
     */
    @Override
    public void apply(Round round){
        PlayerState ownerPlayer=super.getOwnerPLayer(round);
        if(Console.getConfirmation("do you want to up to 5 you mana")){
            ownerPlayer.setBet(ownerPlayer.getPowerAttack()+5);
        }
        ownerPlayer.setBet(ownerPlayer.getPowerAttack()-5);    
    }
}
