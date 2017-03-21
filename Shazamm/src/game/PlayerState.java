
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package game;

import game.cards.manager.CardManager;
import game.gui.Console;

/**
 *
 * @author darven
 */
public class PlayerState implements Cloneable {
    private final Player player;    // TO DO

    /*
     * 0 for the middle of the bridge, < 0 for the left side of the bridge,
     * > 0 for the right side of the bridge
     */
    private int position;
    
    // mana pool
    private int mana;
    
    // bet
    private int bet;
    
    // regroups deck, discarded cards and hand
    private CardManager cardManager;    

    // TO DO
    public PlayerState(Player player, boolean belongPlayer1) {
        this.player = player;
        this.cardManager = new CardManager(belongPlayer1);
        this.mana = Config.MAX_MANA;
        this.bet = 0;
        this.position = (this.player.getColor()) ? 3 : -3;
    }

    
    public int getBet(){
        return this.bet;
    }
    
    public void setBet(int value){
        this.bet = value;
    }
    
    public void bet() {
        boolean betDone = false;
        int manaAmount = 0;
        
        //while input is invalid, ask for a valid bet
        while (!betDone) {
            manaAmount = Console.askBet();
            
            betDone = this.verifyBet(manaAmount);
        }
        
        this.bet = manaAmount; 
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PlayerState clone = (PlayerState) super.clone();

        clone.cardManager = (CardManager) clone.cardManager.clone();

        return clone;
    }

    public void setMana(int manaAmount) {
        this.mana += manaAmount;
    }

    private boolean verifyBet(int manaAmount) {
        if (manaAmount > this.mana) {
            System.out.println("error");

            return false;
        }

        return true;
    }

    /**
     * @return the cardManager
     */
    public CardManager getCardManager() {
        return cardManager;
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
}


//~ Formatted by Jindent --- http://www.jindent.com
