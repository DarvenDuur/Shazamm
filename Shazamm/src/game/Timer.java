/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.gui.Console;
import game.gui.GuiConfig;
import java.util.Date;

/**
 *
 * @author MG
 */
public class Timer {
    
    private static long timeLimit;
    private static long timeBegin;
    
    /**
     * 
     * @return true if the player is in the time 
     */
    public static boolean isInTime(){
        if(!(timeLimit<=0) && timeBegin-Config.DATE.getTime()>=timeLimit){
            return false;
        }
        return true;
    }
    
    public static void timeLimit(int time){
       timeLimit=time*1000;
       
    }
    
    public static void setTimeBegin(){
        timeBegin=Config.DATE.getTime();
    }    
}
