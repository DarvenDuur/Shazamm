/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author MG
 */
public class Timer {
    
    private static long timeLimit = 0;
    private long timeBegin, timeStop;

    public Timer() {
        
    }
    
    /**
     * @return true if the player is in the time 
     */
    public boolean isInTime(){
        if(!(timeLimit<=0) &&  timeStop + timeBegin >= timeLimit){
            return false;
        }
        return true;
    }
    
    /**
     * Setup time limit
     * @param time 
     */
    public static void setTimeLimit(int time){
        timeLimit = time * 1000;
       
    }
    
    /**
     * start timer
     */
    public void start(){
        timeBegin = Config.DATE.getTime();
    }    
    
    public void stop(){
        timeStop = Config.DATE.getTime();
    }
}
