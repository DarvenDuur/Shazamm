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
 * [TODO]
 * @param turn
 * @return 
 */
    public static AIAction run(Turn turn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
