/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui.log;

import game.Bridge;
import game.Config;
import game.Player;
import game.cards.AbstractCard;
import java.util.HashSet;

/** 
 * @author mg
 */
public class LogBetOverview extends Log{
    private final Player PLAYER_1, PLAYER_2;
    private final int PLAYER_1_BET, PLAYER_2_BET;
    private final HashSet<AbstractCard> PLAYER_1_CARDS, PLAYER_2_CARDS;
    
    
    public LogBetOverview(Bridge bridge) {
       this.PLAYER_1 = bridge.getPlayer1();
       this.PLAYER_2 = bridge.getPlayer2();
       
       this.PLAYER_1_BET = bridge.getPlayerState1().getBet();
       this.PLAYER_2_BET = bridge.getPlayerState2().getBet();
       
       this.PLAYER_1_CARDS = bridge.getPlayerState1().getCardManager().getLastDiscard();
       this.PLAYER_2_CARDS = bridge.getPlayerState2().getCardManager().getLastDiscard();
    }
    
//******************************************************************************    
 
    public Player getPlayer1(){
        return this.PLAYER_1;
    }
    public Player getPlayer2(){
        return this.PLAYER_2;
    }
    public int getBetPlayer1(){
        return this.PLAYER_1_BET;
    }
    public int getBetPlayer2(){
        return this.PLAYER_2_BET;
    }
    public HashSet<AbstractCard> getLastDiscardPlayer1(){
        return this.PLAYER_1_CARDS;
    }
    public HashSet<AbstractCard> getLastDiscardPlayer2(){
        return this.PLAYER_2_CARDS;
    }
    
    @Override
    public String toString(){
        String string = super.toString()+ Config.BET_OVERVIEW_STRING+"\n";
        
        string += Config.BET + "\n";
        string += this.PLAYER_1.getName() + ": " + this.PLAYER_1_BET + "\n";
        string += this.PLAYER_2.getName() + ": " + this.PLAYER_2_BET + "\n";
        
        
        string += "\n" + Config.SPELLS + "\n";
        string += this.PLAYER_1.getName() + ":\n";
        for (AbstractCard card : this.PLAYER_1_CARDS) {
            string +=card.toString()+";";
        }
        string +="\n";
        
        string += this.PLAYER_2.getName() + ":\n";
        for (AbstractCard card : this.PLAYER_2_CARDS) {
            string +=card.toString()+";";
        }
        
        return string;
    }
    
}
