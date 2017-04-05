/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.Bridge;
import game.Round;

/**
 *
 * @author darven
 */
public class Clone extends AbstractCard {
    // cloned card
    private AbstractCard clone;
        
//**************************** CONSTRUCTOR *************************************
    public Clone(boolean belongPlayer1) {
        super(belongPlayer1, 2);
        this.clone = null;
    }
    
//**************************** SETTER ******************************************
    /**
     * set cloned card
     * @param clone 
     *      cloned card
     */
    public void setClone(AbstractCard clone){
        this.clone = clone;
    }
    
//**************************** GETTER ******************************************
    /**
     * @return 
     *      the card ID, or its clone if it exists
     */
    public int getId() {
        if (this.clone == null){
            return super.getId();
        } else {
            return clone.getId();
        }
    }

//**************************** OTHER *******************************************
    /**
     * Specific action, must called from generalApply()
     * @see AbstractCard
     * @param round 
     *      round to which apply the card
     */
    @Override
    protected void apply(Round round) {
        if (this.clone != null){
            //invert cloned card ownership
            this.clone.setStolen(!this.isStolen());
            
            //no need to call generalApply, because musism is checked before
            clone.apply(round);
            
            //safety to prevent use of cloned card on another call
            this.clone = null;
        }
    }
}
