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
public class EndOfRound extends AbstractCard {
    
    
    public EndOfRound(boolean belongPlayer1) {
        this.ID = CardsEnum.EndOfRound.getId();
        this.BELONG_PLAYER_1=belongPlayer1;
    }
    
    
    /**
     * end round and reset players' position 3 tiles away from firewall
     * @param round 
     */
    @Override
    protected void apply(Round round){
        round.getLastBridge().replacePlayers();
        round.getLastTurn().end();
        round.end();
    }
}
