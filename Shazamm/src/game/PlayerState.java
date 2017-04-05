
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package game;

import game.cards.AbstractCard;
import game.cards.CardManager;
import game.gui.Console;
import game.gui.log.LogBet;
import game.gui.log.LogSystem;
import java.util.Locale;

/**
 *
 * @author darven
 */
public class PlayerState implements Cloneable {

    // informations on player represented by this state
    private final Player player;

    /* 0 for the middle of the bridge, < 0 for the left side of the bridge,
     * > 0 for the right side of the bridge
     */
    private int position;

    // mana pool
    private int mana;

    // bet (amont of mana)
    private int bet;

    //the power of the attack
    private int attackPower;

    // regroups deck, discarded cards and hand
    private CardManager cardManager;

//***************************** CONSTRUCTOR ************************************
    /**
     * Initialize CardManager.
     *      Set position to -3 if player is player 1, 3 otherwise.
     *      Set mana to the max (see Config)
     * @param player 
     *      player to create this state from
     * @param isPlayer1 
     *      true if player is player 1
     */
    public PlayerState(Player player, boolean isPlayer1) {
        this.player = player;
        this.cardManager = new CardManager(isPlayer1);

        //mana and bet
        this.mana = Config.START_MANA;
        this.bet = 0;

        //initalize position
        this.position = (isPlayer1) ? -3 : 3;
    }

//***************************** GETTERS ****************************************
    /**
     * @return 
     *      the card manager
     */
    public CardManager getCardManager() {
        return cardManager;
    }

    /**
     * @return
     *      the player represented by this state
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return 
     *      the position of the player
     */
    public int getPosition() {
        return position;
    }

    /**
     * @return
     *      the bet of the player
     */
    public int getBet() {
        return bet;
    }

    /**
     * @return 
     *      the mana of the player
     */
    public int getMana() {
        return mana;
    }

    /**
     * @return 
     *      the attack power of the player
     */
    public int getAttackPower() {
        return attackPower;
    }

//***************************** SETTERS ****************************************
    /**
     * @param position 
     *      the new position of the player
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @param powerAttack 
     *      the new attack power of the player
     */
    public void setAttackPower(int powerAttack) {
        this.attackPower = powerAttack > 0 ? powerAttack : 0;
    }

    /**
     * Add mana to the mana pool of the player
     * @param amount 
     *      amount of mana to add
     */
    public void addMana(int amount) {
        this.mana += amount;
    }

//**************************** CLONE *******************************************
    /**
     * @see
     *      Object.clone()
     * @return
     *      a clone of the player state, with a clone of its card manager
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        PlayerState clone = (PlayerState) super.clone();

        clone.cardManager = (CardManager) clone.cardManager.clone();

        return clone;
    }

//***************************** OTHER ******************************************
    /**
     * Get input for the bet, check if input is valid, and deduce mana cost
     */
    public void bet() {
        Console.println(this.player.getName()
                + ", here is the content of your hand:");
        for (AbstractCard card : this.getCardManager().getHand()) {
            Console.println(card.getName());
        }

        int manaAmount = Console.getIntInput(
                "Please enter a bet (" + this.mana + " available):");
        boolean betDone = this.verifyBet(manaAmount);

        //while input is invalid, ask for a valid bet
        while (!betDone) {
            manaAmount = Console.getIntInput("Input invalid (" + this.mana
                    + "). Please enter a valid bet:");

            betDone = this.verifyBet(manaAmount);
        }

        this.bet = manaAmount;
        this.setAttackPower(manaAmount);
        this.addMana(-manaAmount);

        LogSystem.addLog(new LogBet(this.player));
    }

    /**
     * Check valitdity of inputed bet
     * @param manaAmount 
     *      value of the bet to check
     * @return 
     *      true if the bet is conform to conditions
     */
    private boolean verifyBet(int manaAmount) {
        if (manaAmount > this.getMana()) {
            System.out.println("Bet over available mana.");

            return false;
        } else if (manaAmount < 1) {
            System.out.println("Bet lower than minimal bet.");

            return false;
        }

        return true;
    }

    /**
     * For console printing
     * @see 
     *      Object.toString()
     * @return 
     *      String representing the current state of the player
     */
    @Override
    public String toString() {
        String str = "";
        str += this.bet + " ";
        str += this.mana + " ";
        str += this.player + " ";
        str += this.attackPower + " ";
        str += this.cardManager;
        return str;
    }
}
