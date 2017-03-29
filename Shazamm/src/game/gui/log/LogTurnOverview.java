/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui.log;

import game.Bridge;
import game.PlayerState;
import game.Turn;

/**
 * @deprecated 
 * @author mg
 */
public class LogTurnOverview extends LogBetOverview {
    
    
    
    
    private Turn TurnAfter;
    
    public LogTurnOverview(Bridge bridgeBefore) {
        super(bridgeBefore);
        
    }
    
    
    /**
     * @param TurnAfter the TurnAfter to set
     */
    public void setTurnAfter(Turn TurnAfter) {
        this.TurnAfter = TurnAfter;
    }
    
    public String getNameOfWinner(){
        
        switch(TurnAfter.getWinner()){
            case -1:
                return TurnAfter.getBridge().getPlayer1().getName();
            case 1:
                return TurnAfter.getBridge().getPlayer2().getName();
            case 0:
                return "personne";
            default:
                return "nothing";
        }
        
    }
    
    
    public String getBridgeBefore(){
       return  super.BRIDGE.toString();
    }
     
    public String getBridgeAfter(){
       if(TurnAfter!=null){ 
        return  this.TurnAfter.getBridge().toString(); 
       }
       return "pb";
    }
    
    public int getManaBeforePlayer1(){
        return (super.BRIDGE.getPlayerState1().getMana());
    }
    public int getManaBeforePlayer2(){
        return super.BRIDGE.getPlayerState2().getMana();
    }
    public int getManaAfterPlayer1(){
        return this.TurnAfter.getBridge().getPlayerState1().getMana();
    }
    
    public int getManaAfterPlayer2(){
        return this.TurnAfter.getBridge().getPlayerState2().getMana();
    }
    
    public int getPowerBoostPlayer1(){
        return super.BRIDGE.getPlayerState1().getPowerAttack();
    }
    public int getPowerBoostPlayer2(){
        return super.BRIDGE.getPlayerState1().getPowerAttack();
    }
    
    @Override
    public String toString(){
        return null;
    }

    
}
