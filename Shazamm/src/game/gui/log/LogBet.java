package game.gui.log;

import game.Config;
import game.Player;

/**
 * Log for when a player bet
 */
public class LogBet extends Log {
    // player whose bet is logged
    private final Player PLAYER;
    
//***************************** CONSTRUCTOR ************************************
    /**
     * @param player
     *      player whose bet is logged
     */
    public LogBet(Player player) {
        this.PLAYER = player;
    }

//***************************** GETTER *****************************************
    /**
     * @return 
     *      the player whose bet is logged
     */
    public Player getPlayer() {
        return PLAYER;
    }

//***************************** OTHER ******************************************
    /**
     * For console printing
     * @see 
     *      Object.toString()
     * @return 
     *      String representing this log
     */
    @Override
    public String toString() {
        return super.toString() + this.PLAYER + " " + Config.BET_STRING;
    }

}
