package game.cards;

import game.Round;

/**
 * Card 2: Cole
 */
public class Clone extends AbstractCard {
    // cloned card
    private AbstractCard clone;
        
//**************************** CONSTRUCTOR *************************************
    /**
     * Set the card with ID 2, and initialize clone
     * @see AbstractCard
     * @param belongPlayer1 
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     */
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
     * Invert ownership of cloned card, then apply its effect
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
