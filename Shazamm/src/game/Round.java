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
    
//***************************** CONSTRUCTOR ************************************   
    
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
        PlayerState playerState1 = new PlayerState(player1, true);
        PlayerState playerState2 = new PlayerState(player2, false);
        
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
        PlayerState playerState1 = new PlayerState(player1, true);
        PlayerState playerState2 = new PlayerState(player2, false);
        
        //init the new turn 
        Bridge bridge = new Bridge(playerState1, playerState2);
        Turn initTurn = new Turn(bridge);
        this.turns.add(initTurn);
    }
    /**
     * Create a new round from another round, as the round following it
     *      call endOfRoundActions()
     * @param round
     */
    public Round(Round round) {
        //init empty list
        this.turns=new LinkedList<>();
        this.ended=false;
        
        //recover previous lest turn
        Bridge lastBridge = round.getLastTurn().getBridge();
        
        //init playerState
        PlayerState playerState1 = new PlayerState(lastBridge.getPlayer1(), true);
        PlayerState playerState2 = new PlayerState(lastBridge.getPlayer2(), false);
        
        //init the new turn 
        Bridge bridge = new Bridge(playerState1, playerState2, 
                lastBridge.getSize()-1, 
                lastBridge.getFirewallLocation());
        Turn initTurn = new Turn(bridge);
        initTurn.endOfRoundActions();
        this.turns.add(initTurn);
    }   
    
//***************************** GETTERS ****************************************
    
    /**
     * @return the turns
     */
    public LinkedList<Turn> getTurns() {
        return turns;
    }
    
    /**
     * @return last turn
     */
    public Turn getLastTurn(){
        return this.turns.getLast();
    }
    
    /**
     * @return 
     *      turn before current turn
     *      if less than two turns (including current turn), return null
     */
    public Turn getSecondLastTurn(){
        if (this.turns.size() < 2){
            return null;
        }else{
            return this.turns.get(this.turns.size()-2);
        }
    }
    
    /**
     * Return the last bridge
     * @return 
     */
    public Bridge getLastBridge(){
        return this.getLastTurn().getBridge();
    }
    
    /**
     * Return the last bridge
     * @return 
     */
    public Bridge getSecondLastBridge(){
        return this.getSecondLastTurn().getBridge();
    }
    
    /**
     * return the last playstate 1
     * @return 
     */
    public PlayerState getLastPlayerStateOne(){
        return this.getLastBridge().getPlayerState1();
    }
    
    /**
     * return the last playstate 2
     * @return 
     */
    public PlayerState getLastPlayerStateTwo(){
        return this.getLastBridge().getPlayerState1();
    }
    
    /**
     * @return the ended
     */
    public boolean isEnded() {
        return ended;
    }
    
//******************************************************************************
    
    /**
     * set ended to false: end the round, necessary to avoid doing extra turns 
     *      or replaying the whole round
     */
    protected void end(){
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

    /**
     * play turns untill the end of the round
     * @return 
     *      true if the game has ended (if a player is in the lava)
     */
    public boolean play() {
        if (!this.turns.isEmpty()){
            throw new IndexOutOfBoundsException("No initial turn defined !");
        }
        
        //play turns while turn does not end the round
        Turn turn;
        while (!this.isEnded()){
            //get last turn added; if first turn, uses initial turn
            turn = this.getLastTurn();
            
            //play turn
            Turn resultTurn = turn.play(this);
                        
            //add resulting turn to the turns
            this.turns.add(resultTurn);
            
            //check if the turn fills round end condition
            if (this.getLastTurn().isRoundEnd()){
                this.end();
            }
        }
        
        /*true if, in the last turn of the round, at least one of the
        players is out of the bridge (game ending condition)*/
        return this.getLastTurn().getBridge().hasOutOfBridge();
    }
}