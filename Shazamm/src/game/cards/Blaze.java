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
    
    /**
     * The fire wall moves two spaces instead of one. Only if he had to move.
     * @param round
     * 
     * @author Adrien
     */
    @Override
    public void apply(Round round) {
        //Second last fire wall location
        int secondLast = round.getSecondLastBridge().getFirewallLocation();
        
        //Last fire wall location
        int last = round.getLastBridge().getFirewallLocation();
        
        if(secondLast < last){
            round.getLastBridge().addFirewallLocation(1);
        }
        else if(secondLast > last){
            round.getLastBridge().addFirewallLocation(-1);
        }
    }
}
