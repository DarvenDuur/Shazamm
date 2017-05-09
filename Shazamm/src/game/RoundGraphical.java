package game;

import game.gui.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darven
 */
public class RoundGraphical extends Round {

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
    public RoundGraphical(Player player1, Player player2, int size, int firewallLocation) {
        super(player1, player2, size, firewallLocation);
    }
    
    /**
     * Create a new round from another round, as the round following it.
     *      Calls endOfTurnDraw() and getNextRoundStarter() to setup 
     *      the first turn of this round
     * @param round
     *      round to create this round from
     */
    public RoundGraphical(RoundGraphical round) {
        super(round);
    }

//***************************** OTHER ******************************************
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
        //get last turn added; if first turn, uses initial turn
        TurnGraphical turn = (TurnGraphical) this.getLastTurn();
        while (!this.isEnded() || !turn.isEnded()) {
            
            //play turn
            turn.play(this);
            while (!turn.isEnded()) {
                //whait the turn to end
            }
            turn.endPlay(this);

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
}
