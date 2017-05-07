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
    public static AIAction run(Turn turn) {
        
        InferenceEngine engine = new InferenceEngine();
        engine.factBases = FactBase.extractTurn(turn);
        engine.runEngine();
        
        return new AIAction(engine.bestFactBase(), turn);
    }
    
    private void runEngine(){
        HashSet<FactBase> generatedFactBases=new HashSet<>();
        for (FactBase factBase : factBases) {
            for(KnowledgeBase k : KnowledgeBase.values()){
                if(k.isApplicable(factBase)){
                    generatedFactBases.add(k.apply(factBase));
                }
            }
        }
        factBases.addAll(generatedFactBases);
    }

    
    /**
     * 
     * @param turn
     * @return the best factBase
     */
    private FactBase bestFactBase(){
        
        int score=0;
        FactBase BestFact=null;
        int temp=0;
        
        for (FactBase factBase : factBases) {
            temp=factBase.evaluate();
            if(temp<=score){
                score=temp;
                BestFact=factBase;
            }
        }
        return BestFact;
    }
}
