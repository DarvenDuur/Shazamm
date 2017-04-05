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
    
    public Recycling(boolean belongPlayer1) {
        this.id = CardsEnum.Recycling.getId();
        this.belongPlayer1=belongPlayer1;
    }
    
    
    /**
     * ask to the player if he want to increment by five else decrements by five 
     * @param round 
     */
    @Override
    protected void apply(Round round){
        PlayerState ownerPlayer=super.getUserPLayer(round);
        if(Console.getConfirmation(ownerPlayer.getPlayer().getName() + 
                ", do you want to add yourself 5 attack power " +
                "(if you refuse, you will lose 5 attack power)?")){
            ownerPlayer.setAttackPower(ownerPlayer.getAttackPower() + 5);
        }
        ownerPlayer.setAttackPower(ownerPlayer.getAttackPower() - 5);    
    }
}
