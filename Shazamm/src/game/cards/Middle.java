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
public class Middle extends AbstractCard {
    
    public Middle(boolean belongPlayer1) {
        this.ID = CardsEnum.Middle.getId();
        this.BELONG_PLAYER_1=belongPlayer1;
    }
    
    @Override
    protected void apply(Round round){
        int player1Location = round.getLastPlayerState1().getPosition();
        int player2Location = round.getLastPlayerState2().getPosition();
        
        round.getLastBridge().setFirewallLocation(
                (player1Location + player2Location) / 2);
    }
}
