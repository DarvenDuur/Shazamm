/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.gui.Console;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darven
 */
public class Turn implements Cloneable {
    
    protected Bridge bridge;// TO DO

//***************************** CONSTRUCTOR ************************************
    // TO DO
    public Turn(Bridge bridge) {
        this.bridge = bridge;
    }
    
    
//***************************** GETTER *****************************************
    /**
     * @return the bridge
     */
    public Bridge getBridge() {
        return bridge;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        Turn clone = (Turn) super.clone();
        clone.bridge = (Bridge) clone.bridge.clone();
        return clone;
    }
    
    /**
     * Play turn 
     * get action input and apply it to the turn
     * @return 
     *      turn resulting of actions played
     */
    public Turn play(){
        try {
            //generate next turn
            Turn resultTurn = (Turn) this.clone();
            
            //collect actions input
            //TO CLEAN
            ArrayList<Integer> player1Cards = Console.askCards(
                    this.bridge.getPlayerState1().getCardManager().getHand());
            ArrayList<Integer> player2Cards = Console.askCards(
                    this.bridge.getPlayerState2().getCardManager().getHand());
            
            //apply actions to the turn
            
            
            
            return resultTurn;
            
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Turn.class.getName()).log(Level.SEVERE, null, ex);
            return this;
        }    
    }
    
    /**
     * apply end of turn actions:
     *      - each player draws cards (number set in Config.END_OF_ROUND_DRAW)
     */
    public void endOfRoundActions(){
        PlayerState player1, player2;
        player1 = this.bridge.getPlayerState1();
        player2 = this.bridge.getPlayerState2();
        
        //draw set number of cards
        for (int i = 0; i < Config.END_OF_ROUND_DRAW; i++){
            player1.getCardManager().drawCard();
            player2.getCardManager().drawCard();
        }
    }
}
