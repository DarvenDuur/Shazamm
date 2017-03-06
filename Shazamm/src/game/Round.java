/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.LinkedList;

/**
 *
 * @author darven
 */
public class Round {
    
    protected LinkedList<Turn> turns; /*last turn of the list is the talt turn 
    played*/

    protected boolean ended;
    // TO DO
    public Round(Player player1, Player player2, int size, int firewallLocation) {
        //init empty list
        this.turns=new LinkedList<>();
        this.ended=false;
        //init playerState
        PlayerState playerState1=new PlayerState(player1);
        PlayerState playerState2=new PlayerState(player2);
        
        //init the new turn 
        Bridge bridge =new Bridge(playerState1, playerState2, size, firewallLocation);
        Turn initTurn=new Turn(bridge);
        this.turns.add(initTurn);
    }

       
    /**
     * @return the turns
     */
    public LinkedList<Turn> getTurns() {
        return turns;
    }
    public void end(){
        this.ended=true;
    }
    
    
    /**
     * add turn to the end of the turn list
     * @param turn turn to add
     */
    public void addTurn(Turn turn){
        this.turns.addLast(turn);
    }
}
