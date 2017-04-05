/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.Bridge;
import game.PlayerState;
import game.Round;
import game.gui.Console;

/**
 *
 * @author darven
 */
public class DoubleDose extends AbstractCard {
    
    
    
    public DoubleDose(boolean belongPlayer1) {
        this.id = CardsEnum.DoubleDose.getId();
        this.belongPlayer1=belongPlayer1;
    }
    
    /**
     * double the bet
     */
    @Override
    protected void apply(Round round){
        
        PlayerState player =super.getUserPLayer(round);
        player.setAttackPower(player.getAttackPower()*2);
    }
}
