/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui.log;

import game.Bridge;
import game.Config;
import game.Player;
import game.PlayerState;
import game.Turn;

/**
 * @author mg
 */
public class LogTurnOverview extends Log{
    
    
    private final Player PLAYER_1, PLAYER_2;
    private final int PLAYER_1_BET, PLAYER_2_BET,
            PLAYER_1_MANA_START, PLAYER_2_MANA_START;
    private int Player1Attack, Player2Attack, Player1ManaEnd, Player2ManaEnd;
    private short winner;
    
    public LogTurnOverview(Bridge bridge) {
       this.PLAYER_1 = bridge.getPlayer1();
       this.PLAYER_2 = bridge.getPlayer2();
       
       this.PLAYER_1_BET = bridge.getPlayerState1().getBet();
       this.PLAYER_2_BET = bridge.getPlayerState2().getBet();
       
       this.PLAYER_1_MANA_START = bridge.getPlayerState1().getMana();
       this.PLAYER_2_MANA_START = bridge.getPlayerState2().getMana();
       
       this.Player1Attack = 0;
       this.Player2Attack = 0;
       
       this.Player1ManaEnd = 0;
       this.Player2ManaEnd = 0;
       
       this.winner = -2;
    }
    
    /**
     * @param TurnAfter the TurnAfter to set
     */
    public void setFinalTurn(Turn turn) {
       this.Player1Attack = turn.getBridge().getPlayerState1().getPowerAttack();
       this.Player2Attack = turn.getBridge().getPlayerState2().getPowerAttack();
       
       this.Player1ManaEnd = turn.getBridge().getPlayerState1().getMana();
       this.Player2ManaEnd = turn.getBridge().getPlayerState2().getMana();
       
       this.winner = turn.getWinner();
    }
    
    public Player getPlayer1(){
        return this.PLAYER_1;
    }
    public Player getPlayer2(){
        return this.PLAYER_2;
    }
    
    public int getPlayer1Bet(){
        return this.PLAYER_1_BET;
    }
    public int getPlayer2Bet(){
        return this.PLAYER_2_BET;
    }
    
    /**
     * @return the PLAYER_1_MANA_START
     */
    public int getPlayer1ManaStart() {
        return PLAYER_1_MANA_START;
    }
    /**
     * @return the PLAYER_2_MANA_START
     */
    public int getPlayer2ManaStart() {
        return PLAYER_2_MANA_START;
    }

    /**
     * @return the Player1Attack
     */
    public int getPlayer1Attack() {
        return Player1Attack;
    }
    /**
     * @return the Player2Attack
     */
    public int getPlayer2Attack() {
        return Player2Attack;
    }

    /**
     * @return the PLAYER_1_MANA_END
     */
    public int getPlayer1ManaEnd() {
        return Player1ManaEnd;
    }
    /**
     * @return the PLAYER_2_MANA_END
     */
    public int getPlayer2ManaEnd() {
        return Player2ManaEnd;
    }

    /**
     * @return the PLAYER_1_MANA_END
     */
    public int getPlayer1ManaLoss() {
        return PLAYER_1_MANA_START - Player1ManaEnd;
    }
    /**
     * @return the PLAYER_2_MANA_END
     */
    public int getPlayer2ManaLoss() {
        return PLAYER_2_MANA_START - Player2ManaEnd;
    }
    
    public String getWinner(){
        
        switch(this.winner){
            case -1:
                return this.PLAYER_1.getName();
                
            case 1:
                return this.PLAYER_2.getName();
                
            case 0:
                return "personne";
                
            default:
                return "";
        }
        
    }
    
    @Override
    public String toString(){
        String string = super.toString() + Config.TURN_STRING + "\n";
        
        string += Config.MANA_STOCK_START + "\n";
        string += this.PLAYER_1.getName() + ": " + this.PLAYER_1_MANA_START + "\n";
        string += this.PLAYER_2.getName() + ": " + this.PLAYER_2_MANA_START + "\n";
        
        string += "\n" + Config.BET + "\n";
        string += this.PLAYER_1.getName() + ": " + this.PLAYER_1_BET + "\n";
        string += this.PLAYER_2.getName() + ": " + this.PLAYER_2_BET + "\n";
        
        string += "\n" + Config.ATTACK_POWER + "\n";
        string += this.PLAYER_1.getName() + ": " + this.PLAYER_1_BET + "\n";
        string += this.PLAYER_2.getName() + ": " + this.PLAYER_2_BET + "\n";
        
        string += "\n" + Config.MANA_SPENT + "\n";
        string += this.PLAYER_1.getName() + ": " + this.getPlayer1ManaLoss() + "\n";
        string += this.PLAYER_2.getName() + ": " + this.getPlayer2ManaLoss() + "\n";
        
        string += "\n" + Config.MANA_STOCK_END + "\n";
        string += this.PLAYER_1.getName() + ": " + this.Player1ManaEnd + "\n";
        string += this.PLAYER_2.getName() + ": " + this.Player2ManaEnd + "\n";
        
        string += "\n" + Config.WINNER + this.getWinner() + "\n";
        
        return string;
    }
}
