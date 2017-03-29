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
import game.cards.AbstractCard;
import java.util.HashSet;

/**
 * @deprecated 
 * @author mg
 */
public class LogBetOverview {
    protected final Bridge BRIDGE;
    

    public LogBetOverview(Bridge bridge) {
       this.BRIDGE=bridge;
    }
    
//******************************************************************************    
 
    public Player getPlayer1(){
        return BRIDGE.getPlayer1();
    }
    public Player getPlayer2(){
        return BRIDGE.getPlayer2();
    }
    public int getBetPlayer1(){
        return BRIDGE.getPlayerState1().getBet();
    }
    public int getBetPlayer2(){
        return BRIDGE.getPlayerState2().getBet();
    }
    public HashSet getLastDiscardPlayer1(){
        return BRIDGE.getPlayerState2().getCardManager().getLastDiscard();
    }
    public HashSet getLastDiscardPlayer2(){
        return BRIDGE.getPlayerState2().getCardManager().getLastDiscard();
    }
    
    @Override
    public String toString(){
        String string = super.toString()+ Config.BET_OVERVIEW_STRING+"\n";
        string += Config.USERNAME + "\t"+ getPlayer1().getName()+ "\t" + 
                getPlayer2().getName()+"\n";
        
        string += Config.BET + "\t" +getBetPlayer1()+ "\t" +
                getBetPlayer2()+ "\n";
        
        string += Config.SPELLS + "\n ";
        HashSet<AbstractCard> discardPlayer1 =this.getLastDiscardPlayer1();
        HashSet<AbstractCard> discardPlayer2 =this.getLastDiscardPlayer2();
        
        for (AbstractCard card : discardPlayer2) {
         string +=card.toString()+";";
        }
        string +="\n";
        for (AbstractCard card : discardPlayer2) {
         string +=card.toString()+";";
        }
        return string;
    }
    
}
