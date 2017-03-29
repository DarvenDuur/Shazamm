
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package game;

import game.cards.CardManager;
import game.gui.Console;
import java.util.Locale;

/**
 *
 * @author darven
 */
public class PlayerState implements Cloneable {
    // player informations
    private final Player player;

    /*
     * 0 for the middle of the bridge, < 0 for the left side of the bridge,
     * > 0 for the right side of the bridge
     */
    private int position;
    
    // mana pool
    private int mana;
    
    // bet (amont of mana)
    private int bet;
    
    //the power of the attack
    private int powerAttack;
    
    // regroups deck, discarded cards and hand
    private CardManager cardManager;    

//***************************** CONSTRUCTOR ************************************
    
    /**
     * CONSTRUCTOR:
     *      initialize CardManager
     *      set position to -3 if player is player 1, 3 otherwise
     *      set mana to the max
     * @param player
     *      player to create this state from
     * @param isPlayer1 
     *      true if player is player 1
     */
    public PlayerState(Player player, boolean isPlayer1) {
        this.player = player;
        this.cardManager = new CardManager(isPlayer1);
        
        //mana and bet
        this.mana = Config.MAX_MANA;
        this.bet = 0;
        
        //initalize position
        this.position = (isPlayer1) ? -3 : 3;
    }
    
//***************************** GETTERS ****************************************

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
     * @return the bet
     */
    public int getBet() {
        return bet;
    }

    /**
     * @return the mana
     */
    public int getMana() {
        return mana;
    }

    /**
     * @return the powerAttack
     */
    public int getPowerAttack() {
        return powerAttack;
    }
    
//***************************** SETTERS ****************************************

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @param powerAttack the powerAttack to set
     */
    public void setPowerAttack(int powerAttack) {
        this.powerAttack = powerAttack;
    }

    /**
     * add mana to the mana pool
     * @param amount 
     *      mana to add
     */
    public void addMana(int amount) {
        this.mana += amount;
    }
 
//***************************** OTHER ******************************************
    
    /**
     * get input for the bet and check if input is valid
     */
    public void bet() {
        int manaAmount = Console.getIntInput(this.player.getName() + 
                ", please enter a bet (" + this.mana + " available):");
        boolean betDone = this.verifyBet(manaAmount);
        
        //while input is invalid, ask for a valid bet
        while (!betDone) {
            manaAmount = Console.getIntInput("Input invalid ("+this.mana+
                    "). Please enter a valid bet:");
            
            betDone = this.verifyBet(manaAmount);
        }
        
        this.setPowerAttack(manaAmount); 
        this.addMana(- manaAmount);
    }

    /**
     * @see Object.clone()
     * @return a clone of the playerState
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        PlayerState clone = (PlayerState) super.clone();

        clone.cardManager = (CardManager) clone.cardManager.clone();

        return clone;
    }
    
    /**
     * check valitdity of inputed bet
     * @param manaAmont value of the bet to check
     * @return true if the bet is possible
     */
    private boolean verifyBet(int manaAmount) {
        if (manaAmount > this.getMana()) {
            System.out.println("Bet over available mana.");

            return false;
        }else if (manaAmount < 1){
            System.out.println("Bet lower than minimal bet.");

            return false;
        }

        return true;
    }
    
    public String toString(){
       String str="";
       str+=this.bet+" ";
       str+=this.mana+" ";
       str+=this.player+" ";
       str+=this.powerAttack+" ";
       str+=this.cardManager;
       return str;
    }
}
