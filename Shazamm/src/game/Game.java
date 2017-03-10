
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 */
public class Game {
    private final LinkedList<Round> rounds;    // represent a game
    Player player1, player2;

//***************************** CONSTRUCTOR ************************************

    /**
     * Constructor
     *      initialize the player data by calling inputs
     */
    public Game() {

        // init list of rounds
        this.rounds = new LinkedList<>();

        // attribute values
        Random  random       = new Random();
        boolean greenPlayer1 = random.nextBoolean();
        String  namePlayer1  = Test.printAndReception("Player 1, what is your username?");
        String  namePlayer2  = Test.printAndReception("Player 2, what is your username?");

        // uniqueness of name
        while (namePlayer1.equals(namePlayer2)) {
            System.out.println("please try a new username");
        }

        // create 2 players
        this.player1 = new Player(namePlayer1, greenPlayer1);
        this.player2 = new Player(namePlayer2, !greenPlayer1);

        // create the first round of the game
        Round firstRound = new Round(this.player1, this.player2, Config.BRIDGE_MAX_SIZE, 0);

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

//******************************************************************************

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
            round = new Round(this.player1, this.player2);
        }else if (rounds.getFirst().isEnded()){
            round = rounds.getFirst();
        }else{
            round = new Round(this.rounds.getLast());
        }
        
        //execute the round
        round.play();
        
        /*when the round is finished, add to list of rounds
        (could be before executing the round)*/
        rounds.add(round);
        
        /*return true if, in the last turn of the round, at least one of the
        players is out of the bridge*/
        return round.getLastTurn().getBridge().hasOutOfBridge();
    }
    
    /**
     * get the winer of the game
     * @return 
     *      0 for draw, positive for player1, negative for player2
     *      the bigger the value, the wider the difference in victories
     */
    public short winner() {
        short winner = 0;
        for (Round round : rounds){
            winner += round.getWinner();
        }
        return winner;
    }
}