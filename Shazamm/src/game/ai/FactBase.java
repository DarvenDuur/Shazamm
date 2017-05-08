package game.ai;

import game.Config;
import game.PlayerState;
import game.Turn;
import game.cards.AbstractCard;
import game.cards.CardManager;
import game.cards.Clone;
import game.cards.CardsEnum;
import game.gui.log.LogTitle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Regroups different facts
 */
public class FactBase extends HashSet<Fact> {
    
    // mana limit to determine that a situation is in zone 2 (basic bet = 2)
    private static final int Z2_LIMIT=20;
    
    // mana limit to determine that a situation provide critical amount of mana
    private static final int LOW_MANA_LIMIT=10;
    
    // ratio to calculate bet from available mana
    private static final float RELATIVE_BET_RATIO = (float) 0.2;
    
    /* max ratio to randomize calculated bet
    (will be multiplied by a real from -1 to 1) */
    private static final float BET_VARIATION_RATIO = (float) 0.2;
    
    // minimum ratio between available mana, to consider a big advantage
    private static final float BIG_MANA_A_RATIO = (float) 0.2;
    
    // maximum ratio between available mana, to consider a small diference
    private static final float SMALL_MANA_D_RATIO = (float) 0.1;
    
    // id of card cloned in this fact base (0: no clone; negative: double clone)
    private short clone=0;
    
//******************************************************************************    
    /**
     * generate all possible fact bases from turn, clone included
     * @param turn
     *      turn to analyse
     * @return 
     *      all possible fact bases from turn
     */
    public static HashSet<FactBase> extractTurn(Turn turn){
        FactBase factBase = new FactBase();
        
        PlayerState humanPlayer=turn.getPlayerState(true);
        PlayerState botPlayer=turn.getPlayerState(false);
        int firewallLocation=turn.getBridge().getFirewallLocation();
        
        CardManager botCardManager=botPlayer.getCardManager();
        HashSet<AbstractCard> botCards=botCardManager.getHand();
        HashSet<AbstractCard> botPlayedCards=botCardManager.getLastDiscard();
        
        CardManager playerCardManager=humanPlayer.getCardManager();
        
        HashSet<AbstractCard> playerPlayedCards=playerCardManager.getLastDiscard();
        
        factBase.generalFact(humanPlayer, botPlayer, firewallLocation);
        factBase.availableCards(botCards);
        factBase.lastPlayedCardsBot(botPlayedCards);
        
        factBase.lastPlayedCardsEnnemy(playerPlayedCards);
        
        return factBase.cloneManager(playerPlayedCards, botPlayedCards, botCards);
    }
    
//***************************** GENERAL FACTS **********************************
    /**
     * extract all possible general facts from inputed PlayerState
     * @param humanPlayer
     *      human player PlayerState
     * @param botPlayer
     *      bot player PlayerState
     * @param firewallLocation 
     *      firewall location on the bridge
     */
    private void generalFact(PlayerState humanPlayer, PlayerState botPlayer, 
            int firewallLocation){
        
        // extract both player position
        int humanLocation=humanPlayer.getPosition();
        int botLocation=botPlayer.getPosition();
        
        // extract both player mana
        int botMana=botPlayer.getMana();
        int humanMana=humanPlayer.getMana();
        
        // facts about available mana
        if(isManaLow(botMana)){
            this.add(Fact.MANA_SMALL_R);
        }
        if(isZoneTwo(botMana)){
            this.add(Fact.Z2);
        }
        
        // facts about firewall location
        if(firewallOneEnnemy(firewallLocation, humanLocation)){
            this.add(Fact.WALL_E_1);
        } else if(firewallTwoEnnemy(firewallLocation, humanLocation)){
            this.add(Fact.WALL_E_2);
        } else if(firewallOneSelf(firewallLocation, botLocation)){
            this.add(Fact.WALL_S_1);
        }
        
        // facts about ratio of mana between the two players
        float differenceRatio = differenceRatio(botMana, humanMana);
        if(bigAdvantage(differenceRatio)){
            this.add(Fact.MANA_BIG_A);
        }
        if(smallDifference(differenceRatioa)){
            this.add(Fact.MANA_SMALL_A);
        }
        if(smallAdvantage(differenceRatio)){
            this.add(Fact.MANA_SMALL_D);
        }
        
        // facts about number of turns in the round
        if(isTheFirstTurn()){
            this.add(Fact.BEGIN);
        }
    }
    
    /**
     * compare firewall location and player position
     */
    private boolean firewallOneEnnemy(int firewallLocation, int humanLocation){
        return firewallLocation-humanLocation==1;
    }
    
    /**
     * compare firewall location and player position
     */
    private boolean firewallTwoEnnemy(int firewallLocation, int humanLocation){
        return firewallLocation - humanLocation==2;
    }
   
    /**
     * compare firewall location and player position
     */
    private boolean firewallOneSelf(int firewallLocation, int botLocation){
        return botLocation-firewallLocation==1;
    }
    
    /**
     * analyse available mana 
     */
    private boolean isManaLow(int botMana){
        return botMana<=this.LOW_MANA_LIMIT;
    }
    
    /**
     * analyse available mana 
     */
    private boolean isZoneTwo(int botMana){
       return botMana<=this.Z2_LIMIT;
    }
    
    /**
     * extract ratio of mana between the two players, 
     *      relative to the mean of available mana
     */
    private float differenceRatio(int botMana, int humanMana){
        return ((botMana - humanMana) / ((botMana + humanMana)/2)); 
    }
    
    /**
     * analyse ratio of mana between the two players 
     */
    private boolean bigAdvantage(float differenceRatio){
        return differenceRatio > BIG_MANA_A_RATIO; 
    }
    
    /**
     * analyse ratio of mana between the two players 
     */
    private boolean smallAdvantage(float differenceRatio){
        return differenceRatio < SMALL_MANA_D_RATIO && differenceRatio > 0;
    }
    
    /**
     * analyse ratio of mana between the two players 
     */
    private boolean smallDifference(float differenceRatio){
        return (differenceRatio < SMALL_MANA_D_RATIO && differenceRatio > -SMALL_MANA_D_RATIO);
    }
    
    /**
     * @return true if it is the first turn of the round 
     */
    private boolean isTheFirstTurn(){
        return LogTitle.getTurnNumber()==1;
    }
    
    /**
     * analyse whole fact base
     * @return 
     *      score of the fact base, higher is better
     */
    public int evaluate(){
        int score=0, 
                clone = this.clone < 0 ? - this.clone : this.clone;
        
        //if cloned card is not used
        if (!this.contains(new CardFact(clone,CardFact.USE)) && clone != 0){
            return 0;
        }
        
        //cards evaluation
        for (Fact f : this) {
            if(f instanceof CardFact){
                score += CardsEnum.CARDS[((CardFact) f).getCardID()-1].getWeight();
            }
        }
        
        //bet evaluation
        score *= 2;
        score -= this.extractBet();
        
        return score;
    }
    
//***************************** CARDS ******************************************
    /**
     * extract all cards as available
     * @param botCards 
     *      cards to extract
     */
    private void availableCards(HashSet<AbstractCard> botCards){
        for (AbstractCard botCard : botCards) {
            
            // if clone is encountered, change value of atribute
            if(botCard instanceof Clone){
                this.clone=1;
            
            // add card to available cards
            } else {
                this.add(new CardFact(botCard.getId(),'a'));
            }
            
        }
    }
    
    /**
     * alternative contain, using card ID
     * @param id
     *      card ID
     * @param botPlayedCards
     *      cards to analyse
     * @return 
     *      true if acrd is in input, false otherwise
     */
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
    
    /**
     * extract all cards as played last turn by bot
     *      (reduced to the few used cases)
     * @param botPlayedCards 
     *      cards to extract
     */
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
    
    /**
     * extract all cards as played last turn by human player
     *      (reduced to the few used cases)
     * @param playerPlayedCards 
     *      cards to extract
     */
    private void lastPlayedCardsEnnemy(HashSet<AbstractCard> playerPlayedCards){
        if(contains(9, playerPlayedCards)){
            this.add(new CardFact(9,'e'));
        }
    }

//***************************** CLONE ******************************************
    /**
     * generate all possible fact bases by adding cloned cards
     * @param playerPlayedCards
     *      cards played last turn by bot
     * @param botPlayedCards
     *      cards played last turn by human
     * @param botCards
     *      current bot hand
     * @return 
     *      all possible fact bases including cloned cards
     */
    public HashSet<FactBase> cloneManager(
            HashSet<AbstractCard> playerPlayedCards, 
            HashSet<AbstractCard> botPlayedCards,
            HashSet<AbstractCard> botCards) {
        HashSet<FactBase> factBases = new HashSet<>();
        factBases.add(this);
        
        // add cloned card if clone was encountered
        if (this.getClone() != 0) {
            this.clone = 0;
            addEnnemyClonable (factBases, playerPlayedCards, botPlayedCards,
                    botCards);
        }
        
        return factBases;
    }

    /**
     * generate fact bases by adding cloned cards with double clone
     * @param factBases
     *      set of fact bases, where all generated fact bases will be added
     * @param playerPlayedCards
     *      cards played last turn by bot
     * @param botPlayedCards
     *      cards played last turn by human
     * @param botCards
     *      current bot hand
     * @return 
     *      all possible fact bases including cloned cards with double clone
     */
    private void addEnnemyClonable(HashSet<FactBase> factBases, 
            HashSet<AbstractCard> playerPlayedCards, 
            HashSet<AbstractCard> botPlayedCards,
            HashSet<AbstractCard> botCards) {
        
        boolean doubleClone = false;
        
        for (AbstractCard card : playerPlayedCards){
            // check if a double clone is possible
            if (card.getId() == 2){
                doubleClone = true;
               
            // add fact bases with available cloned card
            } else if (contains(card.getId(), botCards)) {
                FactBase clonedBase = (FactBase) this.clone();
                
                // set cloned card as from a double clone
                clonedBase.clone = (short) card.getId();
                
                // add cloned card 
                clonedBase.add(new CardFact(card.getId(), 'a'));
                
                // add fact base
                factBases.add(clonedBase);
            }
        }
        
        // if a double clone is possible, add cards accessible with double clone
        if (doubleClone){
            addSelfColnable(factBases, playerPlayedCards, botPlayedCards,
                    botCards);
        }
    }
    
    /**
     * generate fact bases by adding cloned cards with simple clone
     * @param factBases
     *      set of fact bases, where all generated fact bases will be added
     * @param playerPlayedCards
     *      cards played last turn by bot
     * @param botPlayedCards
     *      cards played last turn by human
     * @param botCards
     *      current bot hand
     * @return 
     *      all possible fact bases including cloned cards with simple clone
     */
    private void addSelfColnable(HashSet<FactBase> factBases, 
            HashSet<AbstractCard> playerPlayedCards, 
            HashSet<AbstractCard> botPlayedCards,
            HashSet<AbstractCard> botCards) {
        
        for (AbstractCard card : botPlayedCards){
            
            // ignore clone cards and add fact bases with available cloned card 
            if (card.getId() != 2 && (contains(card.getId(), botCards) || 
                    contains(card.getId(), playerPlayedCards))){
                
                FactBase clonedBase = (FactBase) this.clone();
                
                // set cloned card as from a double clone
                clonedBase.clone = (short) -card.getId();
                
                // add cloned card 
                clonedBase.add(new CardFact(card.getId(), 'a'));
                
                // add fact base
                factBases.add(clonedBase);
            }
        }
    }

    /**
     * use estimated available mana to extract bet from fact base
     * @return
     *      estimated bet
     */
    public int extractBet() {
        if (this.contains(Fact.MANA_SMALL_R)) {
            return extractBet(LOW_MANA_LIMIT);
        } else if (this.contains(Fact.Z2)) {
            return extractBet(Z2_LIMIT);
        } else {
            return extractBet(Config.START_MANA);
        }
    }

    /**
     * extract bet from fact base
     * @param availableMana
     *      available mana
     * @return 
     *      calculated bet
     */
    public int extractBet(int availableMana) {
        int bet;
        if (this.contains(Fact.BET_1)) {
            bet = 1;
        } else if (this.contains(Fact.BET_2)) {
            bet = 2;
        } else if (this.contains(Fact.BET_6)) {
            bet = 3;
            //proportionnal bets
        } else if (this.contains(Fact.BET_BIG)) {
            bet = (int) (relativeBet(availableMana) * (1 + FactBase.BET_VARIATION_RATIO));
        } else if (this.contains(Fact.BET_SMALL)) {
            bet = (int) (relativeBet(availableMana) * (1 - FactBase.BET_VARIATION_RATIO));
        } else {
            bet = relativeBet(availableMana);
        }
        if (this.contains(Fact.BET_ENLARGE)) {
            bet += relativeBet(availableMana) * FactBase.BET_VARIATION_RATIO;
            //bet corrector
            bet = bet >= availableMana ? availableMana : bet;
        }
        return bet;
    }
    
    /**
     * calculate bet relatively to available mana
     * @param availableMana
     *      available mana
     * @return 
     *      calculated bet
     */
    private int relativeBet(int availableMana) {
        Random r = new Random();
        
        return (int) ((RELATIVE_BET_RATIO * availableMana) *
                (1 + ((r.nextFloat() - 0.5) * 2 * BET_VARIATION_RATIO)));
    }

    /**
     * @return the clone
     */
    public short getClone() {
        return clone;
    }

}
