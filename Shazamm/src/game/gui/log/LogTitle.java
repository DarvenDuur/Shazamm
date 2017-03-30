/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui.log;

import game.Config;

/**
 *
 * @author mg
 */
public class LogTitle extends Log {
    private static int roundIndex = 1;
    private static int turnIndex = 1;

    private final int TRUN;
    private final int ROUND;
    
    public LogTitle() {
        this.TRUN = turnIndex++;
        this.ROUND = roundIndex;
    }
    
    public static void endRound(){
        roundIndex+=1;
        turnIndex=0;
    }
    
    @Override
    public String toString(){
        return Config.ROUND_STRING + this.ROUND + " " +
                Config.TURN_STRING + this.TRUN;
    }
    
}
