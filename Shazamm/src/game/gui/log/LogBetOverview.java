package game.gui.log;

import game.Bridge;
import game.Config;
import game.Player;
import game.cards.AbstractCard;
import java.util.HashSet;

/**
 * Log containing bet information, as well as cards played on the turn
 */
public class LogBetOverview extends Log {
    // players whose bet is logged
    private final Player PLAYER_1, PLAYER_2;
    
    // players' bet
    private final int PLAYER_1_BET, PLAYER_2_BET;
    
    // cards used by the players
    private final HashSet<AbstractCard> PLAYER_1_CARDS, PLAYER_2_CARDS;

//***************************** CONSTRUCTOR ************************************
    /**
     * @param bridge 
     *      bridge from which extract the players, their bet,
     *      and the cards they played
     */
    public LogBetOverview(Bridge bridge) {
        super();

        this.PLAYER_1 = bridge.getPlayer(true);
        this.PLAYER_2 = bridge.getPlayer(false);

        this.PLAYER_1_BET = bridge.getPlayerState(true).getBet();
        this.PLAYER_2_BET = bridge.getPlayerState(false).getBet();

        this.PLAYER_1_CARDS = (HashSet<AbstractCard>) 
                bridge.getPlayerState(true).getCardManager().getLastDiscard().clone();
        this.PLAYER_2_CARDS = (HashSet<AbstractCard>) 
                bridge.getPlayerState(false).getCardManager().getLastDiscard().clone();
    }

//***************************** GETTER *****************************************  
    /**
     * @return 
     *      player 1
     */
    public Player getPlayer1() {
        return this.PLAYER_1;
    }

    /**
     * @return 
     *      player 2
     */
    public Player getPlayer2() {
        return this.PLAYER_2;
    }

    /**
     * @return 
     *      player 1's bet
     */
    public int getBetPlayer1() {
        return this.PLAYER_1_BET;
    }

    /**
     * @return 
     *      player 2's bet
     */
    public int getBetPlayer2() {
        return this.PLAYER_2_BET;
    }

    /**
     * @return 
     *      player 1's last played cards
     */
    public HashSet<AbstractCard> getLastDiscardPlayer1() {
        return this.PLAYER_1_CARDS;
    }

    /**
     * @return 
     *      player 2's last played cards
     */
    public HashSet<AbstractCard> getLastDiscardPlayer2() {
        return this.PLAYER_2_CARDS;
    }

//***************************** OTHER ******************************************
    /**
     * For console printing
     * @see 
     *      Object.toString()
     * @return 
     *      String representing this log
     */
    @Override
    public String toString() {
        String string = super.toString() + Config.BET_OVERVIEW_STRING + "\n";

        string += Config.BET + "\n";
        string += this.PLAYER_1.getName() + ": " + this.PLAYER_1_BET + "\n";
        string += this.PLAYER_2.getName() + ": " + this.PLAYER_2_BET + "\n";

        string += "\n" + Config.SPELLS + "\n";
        string += this.PLAYER_1.getName() + ":\n";
        for (AbstractCard card : this.PLAYER_1_CARDS) {
            string += card.toString() + ";";
        }
        string += "\n";

        string += this.PLAYER_2.getName() + ":\n";
        for (AbstractCard card : this.PLAYER_2_CARDS) {
            string += card.toString() + ";";
        }

        return string;
    }

}
