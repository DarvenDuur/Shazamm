/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.cards.manager.CardManager;

/**
 *
 * @author darven
 */
public class PlayerState implements Cloneable{
    
    private final Player player;//TO DO
    
    private int position; /*0 for the middle of the bridge, < 0 for the left 
    side of the bridge, > 0 for the left side of the bridge*/
    
    private CardManager cardManager; //regroups deck, discarded cards and hand
 
    public PlayerState(Player player, int position, CardManager cardManager) {
        this.player = player;
        this.position = position;
        this.cardManager = cardManager;
    }
    
   

//***************************** GETTER *****************************************

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }
    
    /**
     * @return the cardManager
     */
    public CardManager getCardManager() {
        return cardManager;
    }
    
//***************************** SETTER *****************************************
    
    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

//******************************************************************************
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        PlayerState clone = (PlayerState) super.clone();
        clone.cardManager = (CardManager) clone.cardManager.clone();
        return clone;
    }
}
