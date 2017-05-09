package game;

import static game.gui.Shazamm.getName;
import static game.gui.Shazamm.getTimeLimit;
import static game.gui.Shazamm.run;

/**
 * Main Shazamm game system, manages rounds and global victory
 */
public class GameGraphical extends Game {
    
    /**
     * Initialize the player data by calling inputs,
     *      and initialize the first round.
     * @param activateAI
     *      if true, the second player is controlled by AI
     */
    public GameGraphical(boolean activateAI) {
        super(activateAI);
    }
    
    @Override
    protected void setPvEName(String  namePlayer){
        namePlayer = getName("Player, what is your username?");
    }
    
    @Override
    protected void setPvPNames(String namePlayer1, String namePlayer2){
        namePlayer1 = getName("Player 1, what is your username?");

        // uniqueness of name
        do {
            namePlayer2 = getName("Player 2, what"
                    + " is your username?");
        } while (namePlayer2.equals(namePlayer2));
    }
    
    @Override
    protected void setFirstRound(){
        // create the first round of the game
        Round firstRound = new RoundGraphical(this.player1, this.player2,
                Config.BRIDGE_MAX_SIZE, 0);

        // add the first round to list of rounds
        this.rounds.add(firstRound);
    }
    
    @Override
    protected void setTimerLimit(){
        Timer.setTimeLimit(getTimeLimit());
    }

//***************************** OTHER ******************************************
    /**
     * makes the players play a round
     * @return
     *      true if the game has ended (if a player is in the lava)
     */
    @Override
    public boolean playRound() {
        RoundGraphical round;

        /*create a round from scratch if no round is available (just a safety);
        if the last round added to rounds is not ended, will use it;
        otherwize use the data of the last round to create a new one*/
        if (rounds.isEmpty()){
            round = new RoundGraphical(this.player1, this.player2,
                Config.BRIDGE_MAX_SIZE, 0);

        } else {
            round = (RoundGraphical) this.rounds.getLast();
            if (round.isEnded()){
                round = new RoundGraphical(round);
            }
        }

        /*when the round is finished, add to list of rounds
        (could be after executing the round)*/
        rounds.addLast(round);

        /*execute the round, and returns true if the raoun fills the game ending
        condition*/
        return round.play();
    }
}