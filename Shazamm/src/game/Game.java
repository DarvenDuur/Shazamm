
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
    private LinkedList<Round> rounds;    // represent a game
    Player player1, player2;

//***************************** CONSTRUCTOR ************************************

    // TO DO
    public Game() {

        // init list of rounds
        this.rounds = new LinkedList<>();

        // attribute values
        String  namePlayer1  = Test.printAndReception("Player 1, what is your username?");
        Random  random       = new Random();
        boolean greenPlayer1 = random.nextBoolean();
        String  namePlayer2  = Test.printAndReception("Player 2, what is your username?");

        // uniqueness of name
        while (namePlayer1.equals(namePlayer2)) {
            System.out.println("please try a new username");
        }

        // create 2 players
        this.player1 = new Player(namePlayer1, greenPlayer1);
        this.player2 = new Player(namePlayer2, !greenPlayer1);

        Round firstRound = new Round(this.player1, this.player2, Config.BRIDGE_MAX_SIZE, 0);

    }

//***************************** GETTER *****************************************    

    /**
     * @return the rounds
     */
    public LinkedList<Round> getRounds() {
        return rounds;
    }

//******************************************************************************

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
        
        if (rounds.isEmpty()){
            round = new Round(this.player1, this.player2);
        }else{
            round = new Round(this.rounds.getLast());
        }
        
        round.play();
        
        rounds.add(round);
        
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


