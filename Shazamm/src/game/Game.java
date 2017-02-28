/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.LinkedList;

/**
 * 
 */
public class Game {
    
    private LinkedList<Round> rounds;// represent a game

//***************************** CONSTRUCTOR ************************************
    
    // TO DO
    public Game() {
        
        this.rounds=new LinkedList<>();
        
        
        String name1;
        String name2;
        
        String color1;
        String color2;
        
        //attribute values to names and colors
        
        //create 2 players
        Player player1=new Player(name1,color1);
        Player player2=new Player(name2,color2);
        
        
        //create 2 players State corresponding to the players
        PlayerState playerS1=new PlayerState(player1);
        PlayerState playerS2=new PlayerState(player2);
        
        Bridge bridge=new Bridge(playerS1,playerS2);
        Turn firstTurn=new Turn(bridge);
        Round firstRound=new Round();
        firstRound.addTurn(firstTurn);
        
        
        
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
    public void playRound(){
        
    }




}
