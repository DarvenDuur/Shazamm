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
public class Blaze extends AbstractCard {
    
    
    public Blaze(boolean belongPlayer1) {
        this.ID = CardsEnum.Blaze.getId();
        this.BELONG_PLAYER_1=belongPlayer1;
    }
    
    /**
     * The fire wall moves two spaces instead of one. Only if he had to move.
     * @param round
     * 
     * @author Adrien
     */
    @Override
    protected void apply(Round round) {
        round.getLastBridge().setBlaze();
    }
}
