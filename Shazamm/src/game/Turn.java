package game;

import game.cards.AbstractCard;
import game.gui.Console;
import game.gui.GuiConfig;
import game.gui.log.Log;
import game.gui.log.LogBetOverview;
import game.gui.log.LogTurnOverview;
import game.gui.log.LogSystem;
import game.gui.log.LogTitle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Turn of the game, managing interractions with the bridge
 */
public class Turn implements Cloneable {
    
    // Bridge of this turn
    protected Bridge bridge; 

    // Curent state of the turn, if false, can not proceed to next turn
    private boolean ended;

    // Winner of the turn, 0 for draw, -1 for player1, 1 for player2
    private short winner;
    
    // Effect of mutism, if true no spell can be applied
    private boolean mute;
    
    /* true if indiqued player thieves other player's cards, 
     * appropriating the effects to himself.
     * If both are true, players "exchange" their cards. */
    private boolean player1Theft, player2Theft;
    
    // security for interruption of play
    private static boolean canContinuePlay = false;
    
    // security for interruption of play
    private static boolean canContinuePlayer1 = false;
    
    // security for interruption of play
    private static boolean canContinuePlayer2 = false;
    
    // memory for interruption of play 
    private static Round tempRound = null;
    private static Turn tempTurn = null;


//***************************** CONSTRUCTOR ************************************
    /**
     * @param bridge 
     *      Bridge used by this turn (not cloned)
     */
    public Turn(Bridge bridge) {
        this.bridge = bridge;
        this.mute = false;
        this.ended = false;
        this.winner = 0;
    }

//***************************** GETTER *****************************************
    /**
     * @return 
     *      the bridge
     */
    public Bridge getBridge() {
        return bridge;
    }

    /**
     * @return 
     *      0 for draw, -1 for player1, 1 for player2, -2 for unended turn
     */
    public short getWinner() {
        if (!this.ended) {
            return -2;
        }
        return winner;
    }
    
    /**
     * @param player1
     *      if true will return player 1 state, otherwise return player 2 state
     * @return 
     *      the PlayerState for the player
     */
    public PlayerState getPlayerState(boolean player1) {
        return this.bridge.getPlayerState(player1);
    }

    /**
     * Check if this turn fills end of round condition.
     *      End of round condition: at least a player is in or in the wrong side
     *      of the firewall, or at least a player has run out of mana.
     * @return 
     *      true if this turn fills round end conditions
     */
    public boolean isRoundEnd() {
        //get firewall and players positions
        int firewall = this.bridge.getFirewallLocation();
        int player1Position = this.bridge.getPlayerState(true).getPosition();
        int player2Position = this.bridge.getPlayerState(false).getPosition();

        //at least one of the playersis in or in the wrong side of the firewall
        boolean firewallKill = (firewall <= player1Position)
                || (firewall >= player2Position);

        //get players mana
        int player1Mana = this.bridge.getPlayerState(true).getMana();
        int player2Mana = this.bridge.getPlayerState(false).getMana();

        //at least one of the players has run out of mana
        boolean manaRounout = player1Mana <= 0 || player2Mana <= 0;

        return firewallKill || manaRounout;
    }
    
    /**
     * @return 
     *      mute, if true no spell can be applied
     */
    public boolean isMute() {
        return mute;
    }
    
    /**
     * @return 
     *      true if player 1 thieves player 2's cards
     */
    public boolean isPlayer1Theft() {
        return player1Theft;
    }

    /**
     * @return 
     *      true if player 2 thieves player 1's cards
     */
    public boolean isPlayer2Theft() {
        return player2Theft;
    }

//***************************** SETTER *****************************************
    /**
     * Set ended to true, allowing proceeding to next turn
     */
    public void end() {
        this.ended = true;
    }

    /**
     * Set mute to true, so no spell can be applied anymore
     */
    public void setMute() {
        this.mute = true;
    }
    
    /**
     * Set player1Theft to true, 
     *      giving the effects of cards used by player 2 to player 1.
     */
    public void setPlayer1Theft() {
        this.player1Theft = true;
    }

    /**
     * Set player2Theft to true, 
     *      giving the effects of cards used by player 1 to player 2.
     */
    public void setPlayer2Theft() {
        this.player2Theft = true;
    }

//**************************** CLONE *******************************************
    /**
     * @see 
     *      Object.clone()
     * @return 
     *      clone of the turn, with card effects reset
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Turn clone = (Turn) super.clone();
        clone.bridge = (Bridge) clone.bridge.clone();

        //default end and turn victory
        clone.ended = false;
        clone.winner = 0;

        return clone;
    }

//***************************** OTHER ******************************************
    /**
     * Play turn get action and bet input and apply it to this turn.
     *      Clear console between each player.
     *      Break flow before player input
     * @param round 
     *      Parent round, needed to apply actions globally
     */
    public void play(Round round) {
        
        //add turn presentation log
        LogSystem.addLog(new LogTitle());
        if (!GuiConfig.guiMode) {
            Console.clear();
            
            Console.println(LogSystem.getLastLogs(1));
            
            //print bridge initial state
            Console.println(this.getBridge().toString());
        } else {
            game.gui.Shazamm.update(this.getBridge());
        }
        
        
        //where we whait for both players to finish their choice in GUI
        this.interuptPlay(round);
        
        //replace listner in console mode
        if (!GuiConfig.guiMode) {
            this.continuePlay(true);
            this.continuePlay(false);
        } else {
            this.getPlayerState(true).getPlayer().getGui().update(this);
            if (!(this.getPlayerState(false).getPlayer() instanceof BotPlayer)){
                this.getPlayerState(false).getPlayer().getGui().update(this);
            }
        }
    }
    
    /**
     * save usefull values and interupt flow
     * @param round 
     *      round to keep in memory
     */
    public void interuptPlay(Round round){
        canContinuePlayer1 = false;
        //if player is a bot, automaticaly consider it ready
        canContinuePlayer2 = this.getPlayerState(false).getPlayer() 
                instanceof BotPlayer;
        canContinuePlay = true;
        
        // initialising variables for interruption
        tempRound = round;
        tempTurn = this;
    }
    
    /**
     * try tu continue play flow, while loading usefull values
     * @param player1 
     *      player asking to continue
     */
    public static void continuePlay(boolean player1){
        if (player1) {
            canContinuePlayer1 = true;
        } else {
            canContinuePlayer2 = true;
        }
        
        if (canContinuePlayer1 && canContinuePlayer2 && canContinuePlay) {
                canContinuePlay = false;
                tempTurn.playPart2(tempRound);
        }
    }
    
    /**
     * Resume play flow
     */
    private void playPart2(Round round){
        //get current player states
        PlayerState player1 = this.getPlayerState(true);
        PlayerState player2 = this.getPlayerState(false);

        //bet
        player1.bet();
        if (!GuiConfig.guiMode) {
            Console.clear();
        }
        player2.bet();
        
        //print bet log
        if (!GuiConfig.guiMode) {
            Console.clear();
            Console.println(LogSystem.getLastLogs(2));
        } else {
            game.gui.Shazamm.update(this.getBridge());
        }

        //collect actions input
        HashSet<AbstractCard> player1Cards = player1.askCards(this);
        if (!GuiConfig.guiMode) {
            Console.clear();
        }
        HashSet<AbstractCard> player2Cards = player2.askCards(this);
        if (!GuiConfig.guiMode) {
            Console.clear();
        }

        //discard cards played by each player
        player1.getCardManager().discardAll(player1Cards);
        player2.getCardManager().discardAll(player2Cards);

        //merge and sort card lists
        ArrayList<AbstractCard> cards = new ArrayList<>();
        cards.addAll(player1Cards);
        cards.addAll(player2Cards);
        Collections.sort(cards);

        //create turn summary log
        LogTurnOverview turnLog = new LogTurnOverview(this.getBridge());

        //apply cards' action to the turn
        for (AbstractCard card : cards) {
            card.generalApply(round);
        }

        //apply bet
        this.applyBets();

        this.endOfTurnDraw();
        this.end();

        //add bet summary log
        LogSystem.addLog(new LogBetOverview(this.getBridge()));
        //update turn summary log
        turnLog.setFinalTurn(this);
        LogSystem.addLog(turnLog);

        if (!GuiConfig.guiMode) {
            for (Log log : LogSystem.getLastLogs(2)){
                Console.clear();
                Console.println(log);
            }
        } else {
            game.gui.Shazamm.update(this.getBridge());
        }
    }


    /**
     * Set isPlayer1Winner to 0 for draw, -1 for player1, 1 for player2
     */
    private void applyBets() {
        this.setWinner();

        //move firewall toward loser
        this.bridge.moveFirewallLocation(-this.winner);

        //end Turn
        this.end();
    }

    /**
     * Apply end of turn actions: each player draws cards (see Config).
     *      If number of cards inferior to minimum hand size (see Config),
     *      draw cards until hand has the minimum accepted size.
     */
    private void endOfTurnDraw() {
        PlayerState player1, player2;
        player1 = this.bridge.getPlayerState(true);
        player2 = this.bridge.getPlayerState(false);

        //draw set number of cards
        for (int i = 0; i < Config.END_OF_TURN_DRAW; i++) {
            player1.getCardManager().drawCard();
            player2.getCardManager().drawCard();
        }

        //refill hand to minimal accepted size
        player1.getCardManager().refillHand();
        player2.getCardManager().refillHand();
    }

    /**
     * Apply start of round actions: each player draws cards (see Config).
     */
    public void startOfRoundActions() {
        PlayerState player1, player2;
        player1 = this.bridge.getPlayerState(true);
        player2 = this.bridge.getPlayerState(false);

        //draw set number of cards
        for (int i = 0; i < Config.FIRST_TURN_DRAW; i++) {
            player1.getCardManager().drawCard();
            player2.getCardManager().drawCard();
        }
    }

    /**
     * Create a new turn baseed on this turn's final state, 
     *      to start an new round.
     * @return
     *      new turn, ready to play
     */
    public Turn getNextRoundStarter() {
        //break if turn not ended
        if (this.getWinner() == -2) {
            return null;
        }

        //recover bridge
        Bridge bridge = this.getBridge();

        //init playerState
        PlayerState playerState1 = new PlayerState(bridge.getPlayer(true), true);
        PlayerState playerState2 = new PlayerState(bridge.getPlayer(false), false);

        //get previous location of firewall
        int firewallLocation = bridge.getFirewallLocation();

        //if any player lost, the firewall takes its position 
        if (this.winner != 0) {
            //set firewal to loser's position
            if (this.winner < 0) {
                firewallLocation = bridge.getPlayerState(false).getPosition();
            } else {
                firewallLocation = bridge.getPlayerState(true).getPosition();
            }

            //if tie, copy the positions of the firewall and the players
        } else {
            //copy player 1 and 2 positions
            playerState1.setPosition(bridge.getPlayerState(true).getPosition());
            playerState2.setPosition(bridge.getPlayerState(false).getPosition());
        }

        //init the new turn's bridge
        Bridge nextBridge = new Bridge(playerState1, playerState2,
                bridge.getSize() - 1,
                firewallLocation);

        Turn initTurn = new Turn(nextBridge);

        //if any player lost, the players are replaced 3 tiles from the firewall
        if (this.winner != 0) {
            nextBridge.replacePlayers();
        }

        //end turn
        initTurn.end();

        //return turn
        return initTurn;
    }

    /**
     * Calculate turn's winner, basesd on attack power
     */
    private void setWinner() {
        //get bets
        int player1power = this.bridge.getPlayerState(true).getAttackPower();
        int player2power = this.bridge.getPlayerState(false).getAttackPower();

        //compare bets to determine winner
        //player 2 won,
        if (player1power < player2power) {
            this.winner = 1;

            //player 1 won
        } else if (player1power > player2power) {
            this.winner = -1;

        } else {
            this.winner = 0;
        }
    }

    /**
     * For console printing
     * @see 
     *      Object.toString()
     * @return 
     *      String representing the turn
     */
    @Override
    public String toString() {
        String str = "";
        str += this.bridge + "\n";
        str += this.winner + "\n";
        str += this.mute;
        return str;
    }

    boolean canPlay() {
        return !canContinuePlay;
    }
}
