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
        if (round.getLastTurn().isMute()){
            this.apply(round);
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
    protected PlayerState getOwnerPLayer(Round round){
        if(this.isBelongPlayer1()){
           return round.getLastPlayerState1();
        }
        return round.getLastPlayerState2();
    }
    
    protected PlayerState getNotOwnerPlayer(Round round){
        if(this.isBelongPlayer1()){
           return round.getLastPlayerState2();
        }
        return round.getLastPlayerState1();
    }
    
    public static AbstractCard create(int id, boolean belongPlayer1){
        AbstractCard card;
        
        switch (id){
            default:
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
}
