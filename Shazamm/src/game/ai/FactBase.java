package game.ai;

import game.PlayerState;
import game.Turn;
import game.cards.AbstractCard;
import game.cards.CardManager;
import game.cards.CardsEnum;
import game.cards.Clone;
import game.gui.log.LogTitle;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Regroups different facts
 */
public class FactBase extends HashSet<Fact> {
    

//***************************** CONSTANTS **************************************    
    private final int Z_TWO=0;
    private final int LOW_MANA=0;
    private final float PROFIT_RATIO=0;
    private final int Low_DIFFERENCE=0;
    private boolean clone=false;
    
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
        return botMana<=this.LOW_MANA;
    }
    
    private boolean isZoneTwo(int botMana){
       return botMana<=this.Z_TWO;
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
                this.clone=true;
            }
            else{
                this.add(new CardFact(botCard.getId(),'a'));
            }
            
        }
    }
    
    private static boolean contains(int id,HashSet<AbstractCard> botPlayedCards){
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
}
