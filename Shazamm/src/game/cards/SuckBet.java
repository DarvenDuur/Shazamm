/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.Round;

/**
 *
 * @author darven
 */
public class SuckBet extends AbstractCard {

    /**
     * The Mana Reserve increases by the amount of the opponent's bet.
     * @param round
     * 
     * @author Adrien
     */
    @Override
    public void apply(Round round) {   
        super.getOwnerPLayer(round).setMana(
                super.getNotOwnerPlayer(round).getBet());
    }
}
