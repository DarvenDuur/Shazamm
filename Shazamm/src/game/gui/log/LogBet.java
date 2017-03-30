/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui.log;

import game.Config;
import game.Player;

/**
 *
 * @author mg
 */
public class LogBet extends Log {
    
    private final Player PLAYER;

    public LogBet(Player player) {
        this.PLAYER = player;
    }

    /**
     * @return the PLAYER
     */
    public Player getPlayer() {
        return PLAYER;
    }
    
    @Override
    public String toString(){
        return super.toString()+this.PLAYER+" "+Config.BET_STRING;
    }
    
}
