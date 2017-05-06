package game.gui.log;

import game.Bridge;
import game.Config;
import game.Player;
import game.Turn;
import game.gui.GuiConfig;

/**
 * Log summarysing a turn
 */
public class LogTurnOverview extends Log {
    // players whose turn is logged
    private final Player PLAYER_1, PLAYER_2;
    
    // players' bet and mana at the start of the turn
    private final int PLAYER_1_BET, PLAYER_2_BET,
            PLAYER_1_MANA_START, PLAYER_2_MANA_START;
    
    // players' attack power and mana at the end of the turn
    private int Player1Attack, Player2Attack, Player1ManaEnd, Player2ManaEnd;
    
    /* winner of the turn: 0 for draw, -1 for player1, 1 for player2,
     * -2 for no player out of the bridge */
    private short winner;

//***************************** CONSTRUCTOR ************************************
    /**
     * @param bridge 
     *      bridge of the turn to log
     */
    public LogTurnOverview(Bridge bridge) {
        super();

        this.PLAYER_1 = bridge.getPlayer(true);
        this.PLAYER_2 = bridge.getPlayer(false);

        this.PLAYER_1_BET = bridge.getPlayerState(true).getBet();
        this.PLAYER_2_BET = bridge.getPlayerState(false).getBet();

        this.PLAYER_1_MANA_START = bridge.getPlayerState(true).getMana();
        this.PLAYER_2_MANA_START = bridge.getPlayerState(false).getMana();

        this.Player1Attack = 0;
        this.Player2Attack = 0;

        this.Player1ManaEnd = 0;
        this.Player2ManaEnd = 0;

        this.winner = -2;
    }

//***************************** SETTER ***************************************** 
    /**
     * @param turn
     *      turn after update (bet and actions effects)
     */
    public void setFinalTurn(Turn turn) {
        this.Player1Attack = turn.getPlayerState(true).getAttackPower();
        this.Player2Attack = turn.getPlayerState(false).getAttackPower();

        this.Player1ManaEnd = turn.getPlayerState(true).getMana();
        this.Player2ManaEnd = turn.getPlayerState(false).getMana();

        this.winner = turn.getWinner();
    }

//***************************** GETTER ***************************************** 
    /**
     * @return 
     *      player 1
     */
    public Player getPlayer1() {
        return this.PLAYER_1;
    }

    /**
     * @return 
     *      player 2
     */
    public Player getPlayer2() {
        return this.PLAYER_2;
    }

    /**
     * @return 
     *      player 1's bet
     */
    public int getPlayer1Bet() {
        return this.PLAYER_1_BET;
    }

    /**
     * @return 
     *      player 2's bet
     */
    public int getPlayer2Bet() {
        return this.PLAYER_2_BET;
    }

    /**
     * @return 
     *      player 1's mana at the start of the turn
     */
    public int getPlayer1ManaStart() {
        return PLAYER_1_MANA_START;
    }

    /**
     * @return 
     *      player 2's mana at the start of the turn
     */
    public int getPlayer2ManaStart() {
        return PLAYER_2_MANA_START;
    }

    /**
     * @return 
     *      player 1's attack power at the end of the turn
     */
    public int getPlayer1Attack() {
        return Player1Attack;
    }

    /**
     * @return 
     *      player 2's attack power at the end of the turn
     */
    public int getPlayer2Attack() {
        return Player2Attack;
    }

    /**
     * @return 
     *      player 1's mana at the end of the turn
     */
    public int getPlayer1ManaEnd() {
        return Player1ManaEnd;
    }

    /**
     * @return 
     *      player 2's mana at the end of the turn
     */
    public int getPlayer2ManaEnd() {
        return Player2ManaEnd;
    }

    /**
     * @return 
     *      player 1's mana loss during the turn
     */
    public int getPlayer1ManaLoss() {
        return PLAYER_1_MANA_START - Player1ManaEnd;
    }

    /**
     * @return 
     *      player 2's mana loss during the turn
     */
    public int getPlayer2ManaLoss() {
        return PLAYER_2_MANA_START - Player2ManaEnd;
    }

    /**
     * @return 
     *      winner's name, and specific message if draw (see Config.DRAW)
     */
    public String getWinner() {

        switch (this.winner) {
            case -1:
                return this.PLAYER_1.getName();

            case 1:
                return this.PLAYER_2.getName();

            case 0:
                return GuiConfig.DRAW;

            default:
                return "";
        }
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
        String string = super.toString() + GuiConfig.TURN_STRING + "\n";

        string += GuiConfig.MANA_STOCK_START + "\n";
        string += this.PLAYER_1.getName() + ": " + this.PLAYER_1_MANA_START + "\n";
        string += this.PLAYER_2.getName() + ": " + this.PLAYER_2_MANA_START + "\n";

        string += "\n" + GuiConfig.BET + "\n";
        string += this.PLAYER_1.getName() + ": " + this.PLAYER_1_BET + "\n";
        string += this.PLAYER_2.getName() + ": " + this.PLAYER_2_BET + "\n";

        string += "\n" + GuiConfig.ATTACK_POWER + "\n";
        string += this.PLAYER_1.getName() + ": " + this.PLAYER_1_BET + "\n";
        string += this.PLAYER_2.getName() + ": " + this.PLAYER_2_BET + "\n";

        string += "\n" + GuiConfig.MANA_SPENT + "\n";
        string += this.PLAYER_1.getName() + ": " + this.getPlayer1ManaLoss() + "\n";
        string += this.PLAYER_2.getName() + ": " + this.getPlayer2ManaLoss() + "\n";

        string += "\n" + GuiConfig.MANA_STOCK_END + "\n";
        string += this.PLAYER_1.getName() + ": " + this.Player1ManaEnd + "\n";
        string += this.PLAYER_2.getName() + ": " + this.Player2ManaEnd + "\n";

        string += "\n" + GuiConfig.WINNER + this.getWinner() + "\n";

        return string;
    }
}
