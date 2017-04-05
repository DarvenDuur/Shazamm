/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.Round;
import game.gui.Console;

/**
 *
 * @author darven
 */
public class SuckBet extends AbstractCard {

    
    public SuckBet(boolean belongPlayer1) {
        this.ID = CardsEnum.SuckBet.getId();
        this.BELONG_PLAYER_1=belongPlayer1;
    }
    
    /**
     * The Mana Reserve increases by the amount of the opponent's bet.
     * @param round
     * 
     * @author Adrien
     */
    @Override
    protected void apply(Round round) {   
        super.getUserPLayer(round).addMana(
                super.getNotUserPlayer(round).getBet());
    }
}
