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
    
    private final LinkedList<Turn> turns; /*last turn of the list is the last turn 
    played*/

    private boolean ended;
    
    private short winner; //0 for draw, -1 for player1, 1 for player2
    
    
    
    
    
    
    /**
     * Create a new round from all parameters
     * @param player1
     * @param player2
     * @param size
     * @param firewallLocation 
     */
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
     * Create a new round from players, best option for first round
     * @param player1
     * @param player2 
     */
    public Round(Player player1, Player player2) {
        //init empty list
        this.turns=new LinkedList<>();
        this.ended=false;
        //init playerState
        PlayerState playerState1 = new PlayerState(player1);
        PlayerState playerState2 = new PlayerState(player2);
        
        //init the new turn 
        Bridge bridge = new Bridge(playerState1, playerState2);
        Turn initTurn = new Turn(bridge);
        this.turns.add(initTurn);
    }
    /**
     * Create a new round from another round, as the next round
     * @param round
     */
    public Round(Round round) {
        //init empty list
        this.turns=new LinkedList<>();
        this.ended=false;
        
        //recover previous lest turn
        Bridge lastBridge = round.getLastTurn().getBridge();
        
        //init playerState
        PlayerState playerState1 = new PlayerState(lastBridge.getPlayer1());
        PlayerState playerState2 = new PlayerState(lastBridge.getPlayer2());
        
        //init the new turn 
        Bridge bridge = new Bridge(playerState1, playerState2, 
                lastBridge.getSize()-1, 
                lastBridge.getFirewallLocation());
        Turn initTurn = new Turn(bridge);
        this.turns.add(initTurn);
    }   
    
    
    /**
     * @return the turns
     */
    public LinkedList<Turn> getTurns() {
        return turns;
    }
    
    public Turn getLastTurn(){
        return this.turns.getLast();
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

    /**
     * @return the winner
     */
    public short getWinner() {
        return winner;
    }

    void play() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the ended
     */
    public boolean isEnded() {
        return ended;
    }
}
