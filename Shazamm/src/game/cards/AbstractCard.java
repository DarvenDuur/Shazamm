/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.PlayerState;
import game.Round;

/**
 *
 */
public abstract class AbstractCard implements Cloneable, Comparable<AbstractCard> {
    
    protected int id; //from 1 to 14
    
    protected boolean belongPlayer1; //true if card belong to player 1
    
    private boolean stolen; //true if card is used by the posessor's adversary

//******************************************************************************    
    
    @Override
    public int compareTo(AbstractCard o) {
        //ascending order of IDs
        return this.getId() - o.getId();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        AbstractCard clone = (AbstractCard) super.clone();
        return clone;
    }
    
    /**
     * specific action, must call generalApply
     * @param round
     *      round to which apply the card
     */
    protected abstract void apply(Round round);
    
    /**
     * warps each action call, 
     */
    public void generalApply(Round round){
        if (!round.getLastTurn().isMute()){
            //true if this card was stolen
            boolean cardTheft = (round.getLastTurn().isPlayer2Theft() && 
                    this.belongPlayer1) || (round.getLastTurn().isPlayer1Theft()
                    && !this.belongPlayer1);
            
            //invert posessor
            this.setStolen(cardTheft);
            
            this.apply(round);
            
            //revert posessor
            this.setStolen(false);
        }
    }
    
//***************************** GETTER *****************************************
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the card name used for display
     */
    public String getName() {
        return CardsEnum.CARDS[id-1].getName();
    }

    /**
     * @return the image file name, do not contain extension
     */
    public String getImageName() {
        return CardsEnum.CARDS[id-1].getImageName();
    }

    /**
     * @return the decription of the card usage and effects
     */
    public String getDescription() {
        return CardsEnum.CARDS[id-1].getDescription();
    }

    /**
     * @return 
     *      true if stolen and belonging to player 2, 
     *      or not stolen and belonging to player 1
     */
    public boolean isUsedPlayer1() {
        return (belongPlayer1 && !stolen) || (!belongPlayer1 && stolen);
    }
    
    /**
     * @return the belongPlayer1
     */
    public boolean isBelongPlayer1() {
        return belongPlayer1;
    }
    
    /**
     * return the owner of the card
     * @param round
     * @return
     */
    protected PlayerState getUserPLayer(Round round){
        if(this.isUsedPlayer1()){
           return round.getLastPlayerState1();
        }
        return round.getLastPlayerState2();
    }
    
    protected PlayerState getNotUserPlayer(Round round){
        if(this.isUsedPlayer1()){
           return round.getLastPlayerState2();
        }
        return round.getLastPlayerState1();
    }
    
    public static AbstractCard create(int id, boolean belongPlayer1){
        AbstractCard card = null;
        
        switch (id){
            case 1:
                card = new Mutism(belongPlayer1);
                break;
                
            case 2:
                card = new Clone(belongPlayer1);
                break;
                
            case 3:
                card = new Theft(belongPlayer1);
                break;
                
            case 4:
                card = new EndOfRound(belongPlayer1);
                break;
                
            case 5:
                card = new Middle(belongPlayer1);
                break;
                
            case 6:
                card = new Recycling(belongPlayer1);
                break;
            
            case 7:
                card = new AttackBoost(belongPlayer1);
                break;
                
            case 8:
                card = new DoubleDose(belongPlayer1);
                break;
                
            case 9:
                card = new WhoWinLose(belongPlayer1);
                break;
                
            case 10:
                card = new Blaze(belongPlayer1);
                break;
                
            case 11:
                card = new Rezilliance(belongPlayer1);
                break;
                
            case 12:
                card = new Scrooge(belongPlayer1);
                break;
            
            case 13:
                card = new StockBoost(belongPlayer1);
                break;
            
            case 14:
                card = new SuckBet(belongPlayer1);
                break;
        }
        
        return card;
        }

    /**
     * @return the stolen
     */
    public boolean isStolen() {
        return stolen;
    }

    /**
     * @param stolen the stolen to set
     */
    public void setStolen(boolean stolen) {
        this.stolen = stolen;
    }
}
