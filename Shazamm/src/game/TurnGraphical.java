package game;

import game.cards.AbstractCard;
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
public class TurnGraphical extends Turn {
    private HashSet<AbstractCard> player1Cards, player2Cards;
    private PlayerState player1, player2;
    private boolean canContinuePlayer1, canContinuePlayer2;
    private Timer timerPlayer1, timerPlayer2;

//***************************** CONSTRUCTOR ************************************
    /**
     * @param bridge 
     *      Bridge used by this turn (not cloned)
     */
    public TurnGraphical(Bridge bridge) {
        super(bridge);
        
        player1 = this.getPlayerState(true);
        player2 = this.getPlayerState(false);
        
        timerPlayer1 = new Timer();
        timerPlayer2 = new Timer();
        
        player1Cards = player1.getCardManager().getHand();
        player2Cards = player2.getCardManager().getHand();
    }

//***************************** GETTER *****************************************
    /**
     * initialise gui
     */
    public void play() {
        //add turn presentation log
        LogSystem.addLog(new LogTitle());
        game.gui.Shazamm.update(this.getBridge());
        
        //replace listner in console mode
        timerPlayer1.start();
        player1.getPlayer().getGui().update(this);
        if (!(player2.getPlayer() instanceof BotPlayer)){
            timerPlayer2.start();
            player2.getPlayer().getGui().update(this);
        }
    }
    
    /**
     * update values from gui
     */
    public void update(){
        game.gui.Shazamm.update(this.getBridge());
        
        //update cards
        player1Cards = this.getPlayerState(true).askCards(this);
        player2Cards = this.getPlayerState(false).askCards(this);
        
        //update bet and timer
        timerPlayer1.stop();
        player1.betGui();
        if (!(player2.getPlayer() instanceof BotPlayer)){
            timerPlayer2.stop();
            player2.betGui();
        }
    }
    
    /**
     * try to continue play flow, while loading usefull values
     * @param player1 
     *      player asking to continue
     */
    public void continuePlay(boolean player1){
        if (player1) {
            canContinuePlayer1 = true;
        } else {
            canContinuePlayer2 = true;
        }
        
        continuePlay();
    }
    
    public void continuePlay(){
        if (canContinuePlayer1 && canContinuePlayer2) {
            this.update();
        }
    }
    
    /**
     * Resume play flow
     * @param round
     *      round on wich apply cards
     */
    public void endPlay(Round round){
        if(!timerPlayer1.isInTime()){
            player1.setBet(1);
            player1Cards=new HashSet<>();
        }
        
        if(!timerPlayer2.isInTime()){
            player2.setBet(1);
            player2Cards=new HashSet<>();
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
        
        player1.updateBet();
        player2.updateBet();

        //apply bet
        this.applyBets();

        this.endOfTurnDraw();
        this.end();

        //add bet summary log
        LogSystem.addLog(new LogBetOverview(this.getBridge()));
        //update turn summary log
        turnLog.setFinalTurn(this);
        LogSystem.addLog(turnLog);

        game.gui.Shazamm.update(this.getBridge());
    }

    /**
     * @return the ended
     */
    public boolean isEnded() {
        return super.isEnded();
    }
}
