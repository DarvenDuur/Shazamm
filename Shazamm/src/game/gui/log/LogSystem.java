/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui.log;

import java.util.LinkedList;

/**
 *
 * @author darven
 */
public class LogSystem {
    private static final LinkedList<LinkedList<Log>> LOGS = new LinkedList<>();
    
    public static void addLog(Log log){
        if (getLogs().isEmpty()){
            getLogs().add(new LinkedList<>());
        }
        getLogs().getFirst().addFirst(log);
    }
    
    public static void endRound(){
        getLogs().addFirst(new LinkedList<>());
    }

    /**
     * @return the LOGS, first is latest
     */
    public static LinkedList<LinkedList<Log>> getLogs() {
        return LOGS;
    }
    
    
    /**
     * @param number
     *      number of logs to get
     * @return the last 'number' logs, first is latest
     */
    public static LinkedList<Log> getLastLogs(int number) {
        LinkedList<Log> logs = new LinkedList<>();
        
        while (number > 0){
            for (LinkedList<Log> roundLogs : getLogs()){
                for (Log log : roundLogs){
                    logs.add(log);
                    number--;
                }
            }
        }
        
        return logs;
    }
}
