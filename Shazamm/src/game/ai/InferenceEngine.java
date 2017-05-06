package game.ai;

import game.Turn;
import java.util.HashSet;

/**
 * Apply inference rules to the fact base
 */
public class InferenceEngine {
    private HashSet<FactBase> factBases;

       
    
    /**
     * Extract fact bases from turn, run engine on them, resolve conflicts,
     *      and determine best course of action
     * @param turn
     *      Current turn
     * @return 
     *      AIAction containing cards to play and mana to bet on this turn
     */
    /**
     * @deprecated 
     * @param turn
     * @return 
     */
    public static AIAction run(Turn turn) {
    
    }
    public void runEngine(){
        HashSet<FactBase> generatedFactBases=new HashSet<>();
        for (FactBase factBase : factBases) {
            for(KnowledgeBase k : KnowledgeBase.values()){
                if(k.isApplicable(factBase)){
                    generatedFactBases.add(k.apply(factBase));
                }
            }
        }
    }

  
}
