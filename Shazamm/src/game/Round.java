package game;

import game.gui.Console;
import game.gui.log.LogSystem;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darven
 */
public class Round {
    /* List of all the turns of this round. 
     * The last turn of the list is the turn currently played, 
     * and if turn is ended, it is the last turn of the round. */
    private final LinkedList<Turn> turns;

    /* Curent state of the round, if false, can not proceed to next round
     * necessary to avoid doing extra turns or replaying the whole round */
    private boolean ended;

    // Winner of the turn, 0 for draw, -1 for player1, 1 for player2
    private short winner; 

//***************************** CONSTRUCTOR ************************************   
    /**
     * Create a new round from all parameters
     * @see Bridge
     * @param player1
     *      player informations for player 1
     * @param player2
     *      player informations for player 2
     * @param size
     *      size of the bridge (see Bridge)
     * @param firewallLocation
     *      firewall location (see Bridge)
     */
    public Round(Player player1, Player player2, int size, int firewallLocation) {
        //init empty list
        this.turns = new LinkedList<>();
        this.ended = false;

        //init playerState
        PlayerState playerState1 = new PlayerState(player1, true);
        PlayerState playerState2 = new PlayerState(player2, false);

        //init the new turn 
        Bridge bridge = new Bridge(playerState1, playerState2, size, firewallLocation);
        Turn initTurn = new Turn(bridge);

        initTurn.startOfRoundActions();
        initTurn.end();

        this.turns.add(initTurn);
    }

    /**
     * Create a new round from another round, as the round following it.
     *      Calls endOfTurnDraw() and getNextRoundStarter() to setup 
     *      the first turn of this round
     * @param round
     *      round to create this round from
     */
    public Round(Round round) {
        //init empty list
        this.turns = new LinkedList<>();
        this.ended = false;

        //recover initial turn from previous last turn
        Turn initTurn = round.getLastTurn().getNextRoundStarter();

        initTurn.startOfRoundActions();
        this.turns.add(initTurn);
    }

//***************************** GETTER *****************************************
    /**
     * @return 
     *      all turns of the round
     */
    public LinkedList<Turn> getTurns() {
        return turns;
    }

    /**
     * @return 
     *      last turn of the round
     */
    public Turn getLastTurn() {
        return this.turns.getLast();
    }

    /**
     * @return 
     *      Second-last turn of the round (turn before current turn).
     *      If less than two turns (including current turn), return null.
     */
    public Turn getSecondLastTurn() {
        if (this.turns.size() < 2) {
            return null;
        } else {
            return this.turns.get(this.turns.size() - 2);
        }
    }

    /**
     * @return
     *      last turn's bridge
     */
    public Bridge getLastBridge() {
        return this.getLastTurn().getBridge();
    }

    /**
     * @param player1
     *      if true will return player 1 state, otherwise return player 2 state
     * @return 
     *      last turn's player state, depending on parameter
     */
    public PlayerState getLastPlayerState(boolean player1){
        return this.getLastTurn().getPlayerState(player1);
    }

    /**
     * @return 
     *      0 for draw, -1 for player1, 1 for player2,
     *      -2 for no player out of the bridge
     */
    public short getWinner() {
        return winner;
    }

    /**
     * @return 
     *      true if round is ended
     */
    public boolean isEnded() {
        return ended;
    }

//***************************** SETTER *****************************************
    /**
     * Set winner value, using Bridge.hasOutOfBridge().
     *      0 for draw, -1 for player1, 1 for player2,
     *      -2 for no player out of the bridge.
     */
    private void setWinner() {
        this.winner = this.getLastTurn().getBridge().hasOutOfBridge();
    }

    /**
     * Set ended to false, ending the round.
     *      Necessary to avoid doing extra turns or replaying the whole round.
     */
    public void end() {
        this.ended = true;
        this.setWinner();
        LogSystem.endRound();
    }

    /**
     * add turn at the end of the turn list
     * @param turn 
     *      turn to add
     */
    public void addTurn(Turn turn) {
        this.turns.addLast(turn);
    }

    /**
     * Play turns untill the end of the round
     * @return 
     *      true if the game has ended (if a player is in the lava)
     */
    public boolean play() {
        if (this.turns.isEmpty()) {
            throw new IndexOutOfBoundsException("No initial turn defined !");
        }

        //play turns while turn does not end the round
        Turn turn;
        while (!this.isEnded()) {
            //get last turn added; if first turn, uses initial turn
            turn = this.getLastTurn();

            //play turn
            turn.play(this);

            //check if the turn fills round end condition
            if (turn.isRoundEnd()) {
                this.end();
            } else {

                //if round isn't ended, prepare the next turn
                try {
                    this.turns.add((Turn) turn.clone());
                    Console.println("New Turn");
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Round.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        /*true if, in the last turn of the round, at least one of the
        players is out of the bridge (game ending condition)*/
        return this.getWinner() != -2;
    }

//***************************** OTHER ******************************************
    /**
     * For console printing
     * @see 
     *      Object.toString()
     * @return 
     *      String representing the round
     */
    @Override
    public String toString() {
        String str = "";
        for (Turn turn : turns) {
            str += turn.toString();
            str += "\n";

        }
        return str;
    }
}
