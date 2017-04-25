/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ai;

import game.ai.fact.Fact;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author darven
 */
public enum KnowledgeBase {
    Z2(new Fact[]{new Fact("Z2")},
            new Fact[]{new Fact()},
            "Z2",
            (short) 0),
    sdqdqs();
    
    //necessary facts to activate knowledge
    final FactBase POSTULATE;
    
    //facts created by knowledge
    final FactBase CONCLUSION;
    
    //nkowledge name
    final String NAME;
    
    //weight used when dealing with conflicts
    final short WEIGHT;
    
    private KnowledgeBase(Fact[] postulate, Fact[] conclusion,
            String name, short weight){
        this.POSTULATE = (FactBase) new HashSet<Fact>(Arrays.asList(postulate));
        this.CONCLUSION = (FactBase) new HashSet<Fact>(Arrays.asList(conclusion));
        this.NAME = name;
        this.WEIGHT = weight;
    }

    /**
     * @deprecated 
     * @param factBase
     * @return 
     */
    public boolean isApplicable(FactBase factBase){
        return true;
    }
    
}
