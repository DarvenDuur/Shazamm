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
            
            //get current player states
            PlayerState player1 = this.bridge.getPlayerState1();
            PlayerState player2 = this.bridge.getPlayerState2();
            
            //collect actions input
            ArrayList<AbstractCard> player1Cards = Console.askCards(player1);
            ArrayList<AbstractCard> player2Cards = Console.askCards(player2);
            
            //merge and sort card lists
            ArrayList<AbstractCard> cards = player1Cards;
            cards.addAll(player2Cards);
            Collections.sort(cards);
            
            //get bet
            player1.getBet();
            player2.getBet();
                    
            //apply actions to the turn
            
            
            //apply bet
            this.applyBets();
            
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
        //get bets
        int player1bet = this.bridge.getPlayerState1().getBet();
        int player2bet = this.bridge.getPlayerState2().getBet();
        
        //compare bets to determine winner
        if (player1bet < player2bet){
            this.winner = 1;
            
        }else if (player1bet > player2bet){
            this.winner = -1;
            
        }else{
            this.winner = 0;
        }
        
        //end Turn
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
