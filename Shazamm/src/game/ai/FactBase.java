package game.ai;

import game.PlayerState;
import game.Turn;
import game.cards.AbstractCard;
import game.cards.CardManager;
import game.cards.CardsEnum;
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
    private final int Z2_LIMIT=0;
    private final int LOW_MANA_LIMIT=0;
    private final float BIG_MANA_A_RATIO=0;
    private final int SMALL_MANA_D_RATIO=0;
    private short clone=0;
    
//******************************************************************************    
    public void launch(Turn turn){
        
        
        PlayerState player1=turn.getPlayerState(true);
        PlayerState botPlayer=turn.getPlayerState(false);
        int firewallLocation=turn.getBridge().getFirewallLocation();
        
        CardManager botCardManager=botPlayer.getCardManager();
        HashSet<AbstractCard> botCards=botCardManager.getHand();
        HashSet<AbstractCard> botPlayedCards=botCardManager.getLastDiscard();
        
        CardManager playerCardManager=botPlayer.getCardManager();
        
        HashSet<AbstractCard> playerPlayedCards=playerCardManager.getLastDiscard();
        
        this.generalFact(player1, botPlayer, firewallLocation);
        this.availableCards(botCards);
        lastPlayedCardsBot(botPlayedCards);
        
        lastPlayedCardsEnnemy(playerPlayedCards);
        
        ArrayList<FactBase> factBases = this.cloneManager(playerPlayedCards, 
                botPlayedCards, botCards);
    }
    
    
//***************************** GENERAL_FACTS **********************************
    
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
        
        if(strongBenefits()){
            this.add(Fact.MANA_BIG_A);
        }
        if(littleDifference()){
            this.add(Fact.MANA_SMALL_A);
        }
        if(lowBenefits()){
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
    private boolean strongBenefits(){
        return true; 
    }
    
    /**
     * @deprecated 
     * @return 
     */
    private boolean lowBenefits(){
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
