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

//***************************** CONSTRUCTOR ************************************
    
    // TO DO
    public Round() {
        
    }

//***************************** GETTER *****************************************
       
    /**
     * @return the turns
     */
    public LinkedList<Turn> getTurns() {
        return turns;
    }
    
//******************************************************************************    
    
    /**
     * add turn to the end of the turn list
     * @param turn turn to add
     */
    public void addTurn(Turn turn){
        this.turns.addLast(turn);
    }
}
