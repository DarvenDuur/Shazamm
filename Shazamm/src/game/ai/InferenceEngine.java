package game.ai;

import game.Turn;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Apply inference rules to the fact base
 */
public class InferenceEngine {
    // set of fact bases ???
    private HashSet<FactBase> factBases;
    
    // array of knowledge base values, avoid multiple calls of values()
    private static final KnowledgeBase[] KNOWLEDGE_BASE = KnowledgeBase.values();
       
    
    /**
     * Extract fact bases from turn, run engine on them, resolve conflicts,
     *      and determine best course of action
     * @param turn
     *      Current turn
     * @return 
     *      AIAction containing cards to play and mana to bet on this turn
     */
    public static AIAction run(Turn turn) {
        // create new inference engine
        InferenceEngine engine = new InferenceEngine();
        engine.factBases = FactBase.extractTurn(turn);
        engine.runEngine();
        
        return new AIAction(engine.bestFactBase(), turn);
    }
    
    private void runEngine(){
        HashSet<FactBase> generatedFactBases=new HashSet<>();
        for (FactBase factBase : factBases) {
            for(KnowledgeBase k : KNOWLEDGE_BASE){
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
        Iterator  it =factBases.iterator();
        FactBase BestFact=factBases.iterator().next();
        int score=BestFact.evaluate();
        int temp;

        while(it.hasNext()){
            FactBase tempFactBase=(FactBase)it.next();
            temp=tempFactBase.evaluate();
            if(temp<=score){
                score=temp;
                BestFact=tempFactBase;
            }
        }
        return BestFact;
    }
}
