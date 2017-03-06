
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
            System.err.println("please try a new username");
        }

        // create 2 players
        Player player1 = new Player(namePlayer1, greenPlayer1);
        Player player2 = new Player(namePlayer2, !greenPlayer1);

        // create 2 players State corresponding to the players
        PlayerState playerS1   = new PlayerState(player1);
        PlayerState playerS2   = new PlayerState(player2);

        Bridge      bridge     = new Bridge(playerS1, playerS2);
        Round       firstRound = new Round();

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
     * makes the players play a round
     */
    public void playRound() {}
}


