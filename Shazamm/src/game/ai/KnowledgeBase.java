/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ai;

import game.ai.fact.Fact;
import java.util.HashSet;

/**
 *
 * @author darven
 */
public enum KnowledgeBase {
    test(),
    sdqdqs();
    
    
    
    private HashSet<Fact> postulate;
    private HashSet<Fact> conclusion;
    private String name;
    private short weight;
    
    private KnowledgeBase(HashSet<Fact> postulate, HashSet<Fact> conclusion,
            String name, short weight){
        //blabla
    }

    /**
     * @deprecated 
     * @param factBase
     * @return 
     */
    public boolean isApplicable(FactBase factBase){
        return true;
    }

    /**
     * @return the postulate
     */
    public HashSet<Fact> getPostulate() {
        return postulate;
    }

    /**
     * @param postulate the postulate to set
     */
    public void setPostulate(HashSet<Fact> postulate) {
        this.postulate = postulate;
    }

    /**
     * @return the conclusion
     */
    public HashSet<Fact> getConclusion() {
        return conclusion;
    }

    /**
     * @param conclusion the conclusion to set
     */
    public void setConclusion(HashSet<Fact> conclusion) {
        this.conclusion = conclusion;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the weight
     */
    public short getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(short weight) {
        this.weight = weight;
    }
    
}
