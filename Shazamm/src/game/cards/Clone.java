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
    
    private AbstractCard clone;
    
    public Clone(boolean belongPlayer1) {
        this.id = CardsEnum.Clone.getId();
        this.belongPlayer1=belongPlayer1;
        this.clone = null;
    }
   
    @Override
    protected void apply(Round round) {
        if (this.clone != null){
            this.clone.setStolen(!this.isStolen());
            clone.apply(round);
            this.clone = null;
        }
    }
    
    /**
     * set cloned card
     * @param clone 
     *      cloned card
     */
    public void setClone(AbstractCard clone){
        this.clone = clone;
    }
}
