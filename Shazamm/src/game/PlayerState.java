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
public class PlayerState {
    private Player player;
    private int position; /*0 for the middle of the bridge, < 0 for the left side of the
    bridge, > 0 for the left side of the bridge*/
    private CardManager cardManager; //regroups deck, discarded cards and hand

    public PlayerState(Player player, int position, CardManager cardManager) {
        this.player = player;
        this.position = position;
        this.cardManager = cardManager;
    }

    /**
     * Copy inputed PlayerState, and apply new informations
     * @param state PlayerState to copy
     * @param position new position
     * @param cardManager new CardManager
     */
    public PlayerState(PlayerState state, int position, CardManager cardManager) {
    }
    
    /**
     * Copy inputed PlayerState, and apply new informations
     * @param state PlayerState to copy
     * @param position new position
     */
    public PlayerState(PlayerState state, int position) {
    }

    /**
     * Copy constructor, copy inputed PlayerState, and Clone CardManager
     * @param state PlayerState to copy
     */
    public PlayerState(PlayerState state) {
    }
    
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
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return the cardManager
     */
    public CardManager getCardManager() {
        return cardManager;
    }
}
