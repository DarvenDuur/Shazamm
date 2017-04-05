package game.gui.log;

import game.Config;

/**
 * Log containing turn number and round number
 */
public class LogTitle extends Log {
    // current turn index
    private static int roundIndex = 1;
    
    // current round index
    private static int turnIndex = 1;

    // turn index when this log was created
    private final int TRUN;
    
    // round index when this log was created
    private final int ROUND;

//***************************** CONSTRUCTOR ************************************
    /**
     * Increment turn index after saving it in this log
     */
    public LogTitle() {
        super();

        this.TRUN = turnIndex++;
        this.ROUND = roundIndex;
    }

//***************************** OTHER ******************************************
    /**
     * Rest turn index and increments round index
     */
    public static void endRound() {
        roundIndex++;
        turnIndex = 1;
    }
    
    /**
     * For console printing
     * @see 
     *      Object.toString()
     * @return 
     *      String representing this log
     */
    @Override
    public String toString() {
        return Config.ROUND_STRING + this.ROUND + " "
                + Config.TURN_STRING + this.TRUN;
    }

}
