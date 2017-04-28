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
    Z2(new Fact[]{Fact.Z2}, new Fact[]{Fact.BET_2},
            "Z2", 0),
    nonZ2(new Fact[]{}, new Fact[]{},
            "nonZ2", 0),
    mutisme1(new Fact[]{}, new Fact[]{},
            "mutisme1", 0),
    mutisme2(new Fact[]{}, new Fact[]{},
            "mutisme2", 0),
    mutisme3(new Fact[]{}, new Fact[]{},
            "mutisme3", 0),
    larcin1(new Fact[]{}, new Fact[]{},
            "larcin1", 0),
    larcin2(new Fact[]{}, new Fact[]{},
            "larcin2", 0),
    FDM(new Fact[]{}, new Fact[]{},
            "FDM", 0),
    milieu(new Fact[]{}, new Fact[]{},
            "milieu", 0),
    recy1(new Fact[]{}, new Fact[]{},
            "recy1", 0),
    recy2(new Fact[]{}, new Fact[]{},
            "recy2", 0),
    boostDouble(new Fact[]{}, new Fact[]{},
            "boostDouble", 0),
    boost(new Fact[]{}, new Fact[]{},
            "boost", 0),
    qpg(new Fact[]{}, new Fact[]{},
            "qpg", 0),
    qpgAfter1(new Fact[]{}, new Fact[]{},
            "qpgAfter1", 0),
    qpgAfter2(new Fact[]{}, new Fact[]{},
            "qpgAfter2", 0),
    qpgAfter3(new Fact[]{}, new Fact[]{},
            "qpgAfter3", 0),
    qpgAfter4(new Fact[]{}, new Fact[]{},
            "qpgAfter4", 0),
    qpgAfter5(new Fact[]{}, new Fact[]{},
            "qpgAfter5", 0),
    qpgAfter6(new Fact[]{}, new Fact[]{},
            "qpgAfter6", 0),
    brasier1(new Fact[]{}, new Fact[]{},
            "brasier1", 0),
    brasier2(new Fact[]{}, new Fact[]{},
            "brasier2", 0),
    res1(new Fact[]{}, new Fact[]{},
            "res1", 0),
    res2(new Fact[]{}, new Fact[]{},
            "res2", 0),
    harpagon(new Fact[]{}, new Fact[]{},
            "harpagon", 0),
    boostR1(new Fact[]{}, new Fact[]{},
            "boostR1", 0),
    boostR2(new Fact[]{}, new Fact[]{},
            "boostR2", 0),
    boostRAfter1(new Fact[]{}, new Fact[]{},
            "boostRAfter1", 0),
    boostRAfter2(new Fact[]{}, new Fact[]{},
            "boostRAfter2", 0),
    aspi1(new Fact[]{}, new Fact[]{},
            "aspi1", 0),
    aspi2(new Fact[]{}, new Fact[]{},
            "aspi2", 0),
    aspiAfter1(new Fact[]{}, new Fact[]{},
            "aspiAfter1", 0),
    aspiAfter2(new Fact[]{}, new Fact[]{},
            "aspiAfter2", 0);
    
    
    
    //necessary facts to activate knowledge
    final FactBase POSTULATE;
    
    //facts created by knowledge
    final FactBase CONCLUSION;
    
    //nkowledge name
    final String NAME;
    
    //weight used when dealing with conflicts
    final int WEIGHT;
    
    private KnowledgeBase(Fact[] postulate, Fact[] conclusion,
            String name, int weight){
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
