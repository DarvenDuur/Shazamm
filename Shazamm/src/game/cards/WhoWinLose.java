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
public class WhoWinLose extends AbstractCard {
    
    
    /**
     * @see AbstractCard.apply()
     * @param round 
     */
    @Override
    public void apply(Round round){
        round.getLastBridge().setInvertWinLose();
    }
}
