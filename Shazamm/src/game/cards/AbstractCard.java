/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.Bridge;

/**
 *
 */
public abstract class AbstractCard implements Cloneable, Comparable<AbstractCard> {
    
    protected int id; //from 1 to 14

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
     * @param bridge
     *      bridge to which action will be applied
     * @param firstPlayer 
     *      true if the first player apply the action
     */
    public abstract void apply(Bridge bridge, boolean firstPlayer);
    
    /**
     * actions to apply for each
     * @param bridge
     *      bridge to which action will be applied
     * @param card 
     *      card applied
     * @param firstPlayer
     *      true if the first player apply the action
     * @return
     *      true if action can proceed
     */
    protected boolean generalApply(Bridge bridge, boolean firstPlayer){
        
        if (bridge.isMute()){
            return false;
        }
        return true;
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
}
