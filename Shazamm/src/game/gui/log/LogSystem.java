package game.gui.log;

import java.util.LinkedList;

/**
 * Log mannager
 */
public class LogSystem {
    /* all log, list of log lists by round, containing logs for said round
     * first is latest */
    private static final LinkedList<LinkedList<Log>> LOGS = new LinkedList<>();
    
//***************************** SETTER *****************************************
    /**
     * @param log 
     *      log to add to logs, at latest round logs
     */
    public static void addLog(Log log){
        if (getLogs().isEmpty()){
            getLogs().add(new LinkedList<>());
        }
        getLogs().getFirst().addFirst(log);
    }
    
    /**
     * Close the log list for current round, 
     *      and start a new one for the new round.
     *      Calls LogTitle.endRound()
     */
    public static void endRound(){
        getLogs().addFirst(new LinkedList<>());
        LogTitle.endRound();
    }

//***************************** GETTER ***************************************** 
    /**
     * @return 
     *      all the logs, first is latest
     */
    public static LinkedList<LinkedList<Log>> getLogs() {
        return LOGS;
    }
    
    /**
     * @param number
     *      number of logs to get
     * @return 
     *      the last 'number' logs, first is latest
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
