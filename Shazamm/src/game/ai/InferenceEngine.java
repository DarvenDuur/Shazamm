package game.ai;

import game.Turn;

/**
 * Apply inference rules to the fact base
 */
public class InferenceEngine {
    private FactBase factBase;

    /**
     * @return the factBase
     */
    public FactBase getFactBase() {
        return factBase;
    }

    /**
     * @param factBase the factBase to set
     */
    public void setFactBase(FactBase factBase) {
        this.factBase = factBase;
    }
    
    
    /**
     * Extract fact bases from turn, run engine on them, resolve conflicts,
     *      and determine best course of action
     * @param turn
     *      Current turn
     * @return 
     *      AIAction containing cards to play and mana to bet on this turn
     */
    public static AIAction run(Turn turn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
