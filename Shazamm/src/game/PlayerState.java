
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
public class PlayerState implements Cloneable {
    private final Player player;    // TO DO

    /*
     *  0 for the middle of the bridge, < 0 for the left side of the bridge,
     * > 0 for the right side of the bridge
     */
    private int         position;
    private int         mana;
    private CardManager cardManager;    // regroups deck, discarded cards and hand

    // TO DO
    public PlayerState(Player player) {
        this.player      = player;
        this.cardManager = new CardManager();
        this.mana        = Config.MAX_MANA;
        this.position    = (this.player.getColor())
                           ? 3
                           : -3;
    }

    public void bet() {
        boolean betDone = false;

        while (!betDone) {
            int manaAmount = Test.printAndIntReception("choose the amount of" + " mana to bet");

            betDone = this.verifyBet(manaAmount);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PlayerState clone = (PlayerState) super.clone();

        clone.cardManager = (CardManager) clone.cardManager.clone();

        return clone;
    }

    private void removeMana(int manaAmount) {
        this.mana -= manaAmount;
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
