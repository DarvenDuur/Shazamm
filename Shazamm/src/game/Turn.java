/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.cards.AbstractCard;
import game.gui.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darven
 */
public class Turn implements Cloneable {
    
    protected Bridge bridge;// TO DO
    
    private int player1bet;
    private int player2bet;
    
    private boolean ended;
    
    private short winner; //0 for draw, -1 for player1, 1 for player2

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
        
        //default bets
        clone.player1bet = 0;
        clone.player2bet = 0;
        
        //default end and turn victory
        clone.ended = false;
        clone.winner = 0;
        
        return clone;
    }

    /**
     * @return 0 for draw, -1 for player1, 1 for player2
     */
    public short getWinner() {
        if (this.ended) {
            System.out.println("Warning: checking winner in unended turn");
        }
        return winner;
    }
    
//******************************************************************************
    
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
            ArrayList<AbstractCard> player1Cards = Console.askCards(
                    this.bridge.getPlayerState1());
            ArrayList<AbstractCard> player2Cards = Console.askCards(
                    this.bridge.getPlayerState2());
            
            //get bet

            //merge and sort card lists
            ArrayList<AbstractCard> cards = player1Cards;
            cards.addAll(player2Cards);
            Collections.sort(cards);
            
            //apply actions to the turn
            
            //apply bet
            
            return resultTurn;
            
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Turn.class.getName()).log(Level.SEVERE, null, ex);
            return this;
        }    
    }
    
    /**
     * set isPlayer1Winner to 0 for draw, -1 for player1, 1 for player2
     */
    public void applyBets(){
        if (this.player1bet < this.player2bet){
            this.winner = -1;
        }else if (this.player1bet > this.player2bet){
            this.winner = 1;
        }else{
            this.winner = 0;
        }
        this.ended = true;
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
