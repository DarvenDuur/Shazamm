package game;

import java.util.LinkedList;
import java.util.Random;
import game.gui.Console;

/**
 * Main Shazamm game system, manages rounds and global victory
 */
public class Game {
    // all rounds of the game
    private final LinkedList<Round> rounds;
    
    //Player objects containing the data of the two players
    private final Player PLAYER1, PLAYER2;

//***************************** CONSTRUCTOR ************************************
    /**
     * Initialize the player data by calling inputs,
     *      and initialize the first round.
     */
    public Game() {

        // init list of rounds
        this.rounds = new LinkedList<>();

        // attribute values
        Random  random      = new Random();
        boolean greenPlayer = random.nextBoolean();
            
        // get mode (AI or PvP)
        if (/* PvP */true){
            String  namePlayer1 = Console.getInput("Player 1, what"
                    + " is your username?");
            String  namePlayer2 = Console.getInput("Player 2, what"
                    + " is your username?");

            // uniqueness of name
            while (namePlayer1.equals(namePlayer2)) {
                System.out.println("please try a new username");
                namePlayer2 = Console.getInput("Player 2, what"
                    + " is your username?");
            }

            // create 2 players
            if(greenPlayer){
                this.PLAYER1 = new Player(namePlayer1, greenPlayer);
                this.PLAYER2 = new Player(namePlayer2, !greenPlayer);
            } else {
                this.PLAYER1 = new Player(namePlayer2, !greenPlayer);
                this.PLAYER2 = new Player(namePlayer1, greenPlayer);
            }
            
        } else {
            String  namePlayer = Console.getInput("Player 1, what"
                + " is your username?");
            this.PLAYER1 = new Player(namePlayer, greenPlayer);
            this.PLAYER2 = new BotPlayer(!greenPlayer);
        }
        
            
        // create the first round of the game
        Round firstRound = new Round(this.PLAYER1, this.PLAYER2,
                Config.BRIDGE_MAX_SIZE, 0);

        // add the first round to list of rounds
        this.rounds.add(firstRound);
    }

//***************************** GETTER *****************************************
    /**
     * @return the rounds
     */
    public LinkedList<Round> getRounds() {
        return rounds;
    }

//***************************** OTHER ******************************************
    /**
     * loop rounds until game end condition is met (a player is in the lava)
     */
    public void play(){
        while(!playRound()){
            //play rounds while the ending conditions are not met
        }
    }

    /**
     * makes the players play a round
     * @return
     *      true if the game has ended (if a player is in the lava)
     */
    public boolean playRound() {
        Round round;

        /*create a round from scratch if no round is available (just a safety);
        if the last round added to rounds is not ended, will use it;
        otherwize use the data of the last round to create a new one*/
        if (rounds.isEmpty()){
            round = new Round(this.PLAYER1, this.PLAYER2,
                Config.BRIDGE_MAX_SIZE, 0);

        }else if (!rounds.getLast().isEnded()){
            round = rounds.getLast();

        }else{
            round = new Round(this.rounds.getLast());
        }

        /*when the round is finished, add to list of rounds
        (could be after executing the round)*/
        rounds.addLast(round);

        /*execute the round, and returns true if the raoun fills the game ending
        condition*/
        return round.play();
    }

    /**
     * get the winer of the game
     * @return
     *      0 for draw, -1 for player1, 1 for player2
     *      -2 for no player out of the bridge
     */
    public short winner() {
        return this.getRounds().getLast().getWinner();
    }
}