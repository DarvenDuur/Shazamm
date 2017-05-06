package game.ai;

import game.PlayerState;
import game.Turn;
import game.cards.AbstractCard;
import game.cards.CardManager;
import game.cards.Clone;
import game.gui.log.LogTitle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Regroups different facts
 */
public class FactBase extends HashSet<Fact> {
    

//***************************** CONSTANTS **************************************    
    private static final int Z2_LIMIT=20;
    private static final int LOW_MANA_LIMIT=10;
    private static final float RELATIVE_BET_RATIO = (float) 0.2;
    private static final float BET_VARIATION_RATIO = (float) 0.5;
    
    private static final float BIG_MANA_A_RATIO = (float) 0.2;
    private static final float SMALL_MANA_D_RATIO = (float) 0.1;
    private static final int[] CARDS_WEIGHTS = {4, 3, 3, 4, 1, 1, 2, 2, 4, 3, 2, 1, 3, 1};
    private short clone=0;
    
//******************************************************************************    
    public void extractTurn(Turn turn){
        PlayerState humanPlayer=turn.getPlayerState(true);
        PlayerState botPlayer=turn.getPlayerState(false);
        int firewallLocation=turn.getBridge().getFirewallLocation();
        
        CardManager botCardManager=botPlayer.getCardManager();
        HashSet<AbstractCard> botCards=botCardManager.getHand();
        HashSet<AbstractCard> botPlayedCards=botCardManager.getLastDiscard();
        
        CardManager playerCardManager=humanPlayer.getCardManager();
        
        HashSet<AbstractCard> playerPlayedCards=playerCardManager.getLastDiscard();
        
        this.generalFact(humanPlayer, botPlayer, firewallLocation);
        this.availableCards(botCards);
        lastPlayedCardsBot(botPlayedCards);
        
        lastPlayedCardsEnnemy(playerPlayedCards);
        
        ArrayList<FactBase> factBases = this.cloneManager(playerPlayedCards, 
                botPlayedCards, botCards);
    }
    
//***************************** asAIAction *************************************
    public AIAction asAIAction(Turn turn){
        int availableMana = turn.getPlayerState(false).getMana();
        return new AIAction(this.extractCards(turn), 
                this.extractBet(availableMana));
    }

    private AbstractCard extractCard(int id, 
            HashSet<AbstractCard> availableCards){
        for (AbstractCard card : availableCards) {
            if (card.getId()==id) {
                return(card);
            }
        }
        return null;
    }
    
    private HashSet<AbstractCard> extractCards(Turn turn) {
        HashSet<AbstractCard> cards = new HashSet<>();
        int id;
        
        CardManager botCardManager=turn.getPlayerState(false).getCardManager();
        HashSet<AbstractCard> botCards=botCardManager.getHand();
        
        for (Fact fact : this) {
            if (fact instanceof CardFact) {
                CardFact cardFact = (CardFact) fact;
                if (cardFact.getType() == CardFact.USE){
                    id = cardFact.getCardID();
                    //if card is a cloned card
                    if (id != this.clone && id != -this.clone){
                        cards.add(this.extractCard(id, botCards));
                    }
                }
            }
        }
        
        this.extractClone(turn, cards);
        
        return cards;
    }
    
    private void extractClone(Turn turn, HashSet<AbstractCard> cards){
        //if a card is cloned
        if (this.clone != 0){
            CardManager botCardManager =
                    turn.getPlayerState(false).getCardManager();
            HashSet<AbstractCard> botCards =
                    botCardManager.getHand();
            
            CardManager playerCardManager =
                    turn.getPlayerState(true).getCardManager();
            HashSet<AbstractCard> playerPlayedCards =
                    playerCardManager.getLastDiscard();
            
            Clone cloneCard = (Clone) extractCard(2, botCards);
            AbstractCard clonedCard;
            
            //if cloned card is from a double clone
            if (this.clone < 0){
                HashSet<AbstractCard> botPlayedCards = 
                        botCardManager.getLastDiscard();
                clonedCard = extractCard(2, playerPlayedCards);
                AbstractCard secondClonedCard = extractCard(-this.clone, botPlayedCards);
                
                clonedCard.setClone(secondClonedCard);
                
            //if cloned card from a single clone
            } else {
                clonedCard = extractCard(this.clone, playerPlayedCards);
            }
            
            cloneCard.setClone(clonedCard);
            cards.add(cloneCard);
        }
    }
    
    /**
     * use estimated available mana
     * @return 
     */
    public int extractBet(){
        if (this.contains(Fact.MANA_SMALL_R)){
            return this.extractBet(LOW_MANA_LIMIT);
        } else if (this.contains(Fact.Z2)){
            return this.extractBet(Z2_LIMIT);
        } else {
            return this.extractBet(game.Config.START_MANA);
        }
    }
    
    public int extractBet(int availableMana) {
        int bet;
        if (this.contains(Fact.BET_1)){
            bet = 1;
        } else if (this.contains(Fact.BET_2)){
            bet = 2;
        } else if (this.contains(Fact.BET_6)){
            bet = 3;
                
        //proportionnal bets 
        } else if (this.contains(Fact.BET_BIG)){
            bet = (int) (relativeBet(availableMana) * (1 + BET_VARIATION_RATIO));
        } else if (this.contains(Fact.BET_SMALL)){
            bet = (int) (relativeBet(availableMana) * (1 - BET_VARIATION_RATIO));
        } else {
            bet = relativeBet(availableMana);
        }
        
        if (this.contains(Fact.BET_ENLARGE)){
            bet += relativeBet(availableMana) * BET_VARIATION_RATIO;
            
            //bet corrector
            bet = bet >= availableMana ? availableMana : bet;
        }
        return bet;
    }
   
    private int relativeBet(int availableMana) {
        return (int) (RELATIVE_BET_RATIO * availableMana);
    }
 
//***************************** GENERAL FACTS **********************************
    private void generalFact(PlayerState player1, PlayerState botPlayer, 
            int firewallLocation){
        
        int player1Location=player1.getPosition();
        int botLocation=botPlayer.getPosition();
        
        int botMana=botPlayer.getMana();
        int playerMana=player1.getMana();
        
        if(isManaLow(botMana)){
            this.add(Fact.MANA_SMALL_R);
        }
        if(isZoneTwo(botMana)){
            this.add(Fact.Z2);
        }
        
        if(firewallOneEnnemy(firewallLocation, player1Location)){
            this.add(Fact.WALL_E_1);
        }
        if(firewallTwoEnnemy(firewallLocation, player1Location)){
            this.add(Fact.WALL_E_2);
        }
        
        if(firewallOneSelf(firewallLocation, botLocation)){
            this.add(Fact.WALL_S_1);
        }
        
        if(strongAdvantage()){
            this.add(Fact.MANA_BIG_A);
        }
        if(littleDifference()){
            this.add(Fact.MANA_SMALL_A);
        }
        if(lowAdvantage()){
            this.add(Fact.MANA_SMALL_D);
        }
        if(isTheFirstTurn()){
            this.add(Fact.BEGIN);
        }
    }
    
    private boolean firewallOneEnnemy(int firewallLocation, int player1Location){
        return firewallLocation-player1Location==1;
    }
    
    private boolean firewallTwoEnnemy(int firewallLocation, int player1Location){
        return firewallLocation-player1Location==2;
    }
   
    private boolean firewallOneSelf(int firewallLocation, int botLocation){
        return botLocation-firewallLocation==1;
    }
    
    private boolean isManaLow(int botMana){
        return botMana<=this.LOW_MANA_LIMIT;
    }
    
    private boolean isZoneTwo(int botMana){
       return botMana<=this.Z2_LIMIT;
    }
    
    /**
     * @deprecated 
     * @return 
     */
    private boolean strongAdvantage(){
        return true; 
    }
    
    /**
     * @deprecated 
     * @return 
     */
    private boolean lowAdvantage(){
        return true;
    }
    
    /**
     * @deprecated
     * @return 
     */
    private boolean littleDifference(){
        return true;
    }
    
    /**
     * 
     * @return true if it is the first turn of the round 
     */
    private boolean isTheFirstTurn(){
        return LogTitle.getTurnNumber()==1;
    }
    
    /**
     * @deprecated 
     * @return 
     */
    public int evaluate(){
        int score=0;
        for (Fact f : this) {
            //evaluation des cartes
            if(f instanceof CardFact){
                score += CARDS_WEIGHTS[((CardFact) f).getCardID()-1];
            }
        }
        
        //evaluation des paris
        score *= 2;
        score += this.extractBet();
        
        return score;
    }
    
//***************************** CARDS ******************************************
    private void availableCards(HashSet<AbstractCard> botCards){
        for (AbstractCard botCard : botCards) {
            if(botCard instanceof Clone){
                this.clone=1;
            }
            else{
                this.add(new CardFact(botCard.getId(),'a'));
            }
            
        }
    }
    
    private static boolean contains(int id, HashSet<AbstractCard> botPlayedCards){
        boolean find=false;
        Iterator it=botPlayedCards.iterator();
        while(!find && it.hasNext()){
            AbstractCard abCard=(AbstractCard)it.next();
            if(abCard.getId()==id){
                find=true;
            }
        }
        return find;
    }
    
    private void lastPlayedCardsBot(HashSet<AbstractCard> botPlayedCards){
        if(contains(9,botPlayedCards)){
            this.add(new CardFact(9,'s'));
        }
        if(contains(13,botPlayedCards)){
            this.add(new CardFact(13,'s'));
        }
        if(contains(14, botPlayedCards)){
            this.add(new CardFact(14,'s'));
        }
    }
    
    private void lastPlayedCardsEnnemy(HashSet<AbstractCard> playerPlayedCards){
        if(contains(9, playerPlayedCards)){
            this.add(new CardFact(9,'e'));
        }
    }

//***************************** CLONE ******************************************
   
    private ArrayList<FactBase> cloneManager(
            HashSet<AbstractCard> playerPlayedCards, 
            HashSet<AbstractCard> botPlayedCards,
            HashSet<AbstractCard> botCards) {
        ArrayList<FactBase> factBases = new ArrayList<>();
        factBases.add(this);
        
        if (this.clone != 0) {
            this.clone = 0;
            addEnnemyClonable (factBases, playerPlayedCards, botPlayedCards,
                    botCards);
        }
        
        return factBases;
    }

    private void addEnnemyClonable(ArrayList<FactBase> factBases, 
            HashSet<AbstractCard> playerPlayedCards, 
            HashSet<AbstractCard> botPlayedCards,
            HashSet<AbstractCard> botCards) {
        boolean doubleClone = false;
        for (AbstractCard card : playerPlayedCards){
            if (card.getId() == 2){
                doubleClone = true;
                
            } else if (contains(card.getId(), botCards)) {
                FactBase clonedBase = (FactBase) this.clone();
                clonedBase.clone = (short) card.getId();
                clonedBase.add(new CardFact(card.getId(), 'a'));
                factBases.add(clonedBase);
            }
        }
        
        if (doubleClone){
            addSelfColnable(factBases, playerPlayedCards, botPlayedCards,
                    botCards);
        }
    }
    
    
    private void addSelfColnable(ArrayList<FactBase> factBases, 
            HashSet<AbstractCard> playerPlayedCards, 
            HashSet<AbstractCard> botPlayedCards,
            HashSet<AbstractCard> botCards) {
        
        for (AbstractCard card : botPlayedCards){
            if (card.getId() != 2 && (contains(card.getId(), botCards) || 
                    contains(card.getId(), playerPlayedCards))){
                FactBase clonedBase = (FactBase) this.clone();
                clonedBase.clone = (short) -card.getId();
                clonedBase.add(new CardFact(card.getId(), 'a'));
                factBases.add(clonedBase);
            }
        }
    }

}
