package game.ai;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * To ittreate over enum, use "for(KnowledgeBase k : KnowledgeBase.values())"
 */
public enum KnowledgeBase {
    Z2(new Fact[]{Fact.Z2},
            new Fact[]{Fact.BET_2},
            "Z2", 0),
    
    nonZ2(null, 
            new Fact[]{Fact.Z2},
            new Fact[]{Fact.BET_RELATIVE},
            "nonZ2", 0),
    
    mutisme1(new Fact[]{new CardFact(1,CardFact.AVAILABLE), Fact.MANA_BIG_A}, 
            new Fact[]{new CardFact(1, CardFact.USE)},
            "mutisme1", 0),
    
    mutisme2(new Fact[]{new CardFact(1,CardFact.AVAILABLE), Fact.WALL_E_1}, 
            new Fact[]{new CardFact(1, CardFact.USE), Fact.BET_BIG},
            "mutisme2", 0),
    
    mutisme3(new Fact[]{new CardFact(1,CardFact.AVAILABLE), Fact.MANA_SMALL_A, 
        Fact.BEGIN}, 
            new Fact[]{new CardFact(1, CardFact.USE)},
            "mutisme3", 0),
    
    larcin1(new Fact[]{new CardFact(3,CardFact.AVAILABLE), 
        new CardFact(10,CardFact.AVAILABLE), Fact.WALL_E_2},
            new Fact[]{new CardFact(3, CardFact.USE), 
                new CardFact(10,CardFact.USE)},
            "larcin1", 0),
    
    larcin2(new Fact[]{new CardFact(3,CardFact.AVAILABLE), Fact.WALL_E_1}, 
            new Fact[]{new CardFact(3, CardFact.USE)},
            "larcin2", 0),
    
    FDM(new Fact[]{new CardFact(4,CardFact.AVAILABLE), Fact.WALL_E_1},
            new Fact[]{new CardFact(4, CardFact.USE)},
            "FDM", 0),
    
    milieu(new Fact[]{new CardFact(5,CardFact.AVAILABLE), Fact.WALL_S_1},
            new Fact[]{new CardFact(5, CardFact.USE)}, "milieu", 0),
    
    recy1(new Fact[]{new CardFact(6, CardFact.AVAILABLE)},
            new Fact[]{new CardFact(6, CardFact.USE), Fact.BET_6},
            "recy1", 0),
    
    recy2(new Fact[]{new CardFact(6, CardFact.USE), 
        new CardFact(9, CardFact.ENNEMY)}, 
            new Fact[]{Fact.BET_ENLARGE},
            "recy2", 0),
    
    boostDouble(new Fact[]{new CardFact(7,CardFact.AVAILABLE), 
        new CardFact(8,CardFact.AVAILABLE)}, 
            new Fact[]{new CardFact(7, CardFact.USE), 
                new CardFact(8, CardFact.USE), Fact.BET_SMALL},
            "boostDouble", 0),
    
    boost(new Fact[]{new CardFact(7,CardFact.AVAILABLE), Fact.MANA_SMALL_R}, 
            new Fact[]{Fact.BEGIN},
            new Fact[]{new CardFact(7,CardFact.USE), Fact.BET_1},
            "boost", 0),
    
    qpg(new Fact[]{new CardFact(9,CardFact.AVAILABLE), Fact.MANA_SMALL_R},
            new Fact[]{Fact.BET_SMALL},
            "qpg", 0),
    
    qpgAfter1(new Fact[]{new CardFact(9,CardFact.SELF), 
        new CardFact(1,CardFact.AVAILABLE)}, 
            new Fact[]{new CardFact(1,CardFact.USE)},
            "qpgAfter1", 0),
    
    qpgAfter2(new Fact[]{new CardFact(9,CardFact.SELF), 
        new CardFact(3,CardFact.AVAILABLE)}, 
            new Fact[]{new CardFact(3,CardFact.USE)},
            "qpgAfter2", 0),
    
    qpgAfter3(new Fact[]{new CardFact(9,CardFact.SELF), 
        new CardFact(6,CardFact.AVAILABLE)},  
            new Fact[]{new CardFact(6,CardFact.USE)},
            "qpgAfter3", 0),
    
    qpgAfter4(new Fact[]{new CardFact(9,CardFact.SELF), 
        new CardFact(11,CardFact.AVAILABLE)},  
            new Fact[]{new CardFact(11,CardFact.USE)},
            "qpgAfter4", 0),
    
    qpgAfter5(new Fact[]{new CardFact(9,CardFact.SELF), 
        new CardFact(10,CardFact.AVAILABLE)}, 
            new Fact[]{new CardFact(10,CardFact.USE)},
            "qpgAfter5", 0),
    
    qpgAfter6(new Fact[]{new CardFact(9,CardFact.SELF)},
            new Fact[]{new CardFact(1,CardFact.AVAILABLE), 
                new CardFact(3,CardFact.AVAILABLE), 
                new CardFact(6,CardFact.AVAILABLE), 
                new CardFact(11,CardFact.AVAILABLE), 
                new CardFact(10,CardFact.AVAILABLE)},
            new Fact[]{Fact.BET_1},
            "qpgAfter6", 0),
    
    brasier1(new Fact[]{new CardFact(3,CardFact.AVAILABLE), 
        new CardFact(10,CardFact.AVAILABLE), Fact.WALL_E_2},
            new Fact[]{new CardFact(10,CardFact.USE), 
                new CardFact(3,CardFact.USE)},
            "brasier1", 0),
    
    brasier2(new Fact[]{new CardFact(10,CardFact.AVAILABLE), Fact.WALL_E_2},
            new Fact[]{new CardFact(3,CardFact.AVAILABLE)}, 
            new Fact[]{new CardFact(10,CardFact.USE), Fact.BET_ENLARGE},
            "brasier2", 0),
    
    res1(new Fact[]{new CardFact(10,CardFact.AVAILABLE), 
        new CardFact(11,CardFact.AVAILABLE)},
            new Fact[]{new CardFact(10,CardFact.USE), 
                new CardFact(11,CardFact.USE)},
            "res1", 0),
    
    res2(new Fact[]{new CardFact(11,CardFact.AVAILABLE), Fact.WALL_S_1},
            new Fact[]{new CardFact(11,CardFact.USE), Fact.BET_SMALL},
            "res2", 0),
    
    harpagon(new Fact[]{new CardFact(12,CardFact.AVAILABLE), Fact.Z2, 
        Fact.MANA_SMALL_D},
            new Fact[]{new CardFact(12,CardFact.USE)},
            "harpagon", 0),
    
    boostRAfter1(new Fact[]{new CardFact(13,CardFact.SELF), 
        new CardFact(1,CardFact.AVAILABLE)}, 
            new Fact[]{new CardFact(1,CardFact.USE)},
            "boostRAfter1", 0),
    
    boostRAfter2(new Fact[]{new CardFact(13,CardFact.SELF), 
        new CardFact(4,CardFact.AVAILABLE)},
            new Fact[]{new CardFact(4,CardFact.USE)},
            "boostRAfter2", 0),
    
    aspi1(new Fact[]{new CardFact(14,CardFact.AVAILABLE), 
        new CardFact(1,CardFact.AVAILABLE)}, 
            new Fact[]{new CardFact(14,CardFact.USE)},
            "aspi1", 0),
    
    aspiAfter1(new Fact[]{new CardFact(14,CardFact.SELF)}, 
            new Fact[]{new CardFact(1,CardFact.USE)},
            "aspiAfter1", 0),
    
    aspiAfter2(new Fact[]{new CardFact(14,CardFact.SELF)}, 
            new Fact[]{new CardFact(4,CardFact.USE)},
            "aspiAfter2", 0),
    
    aspi2(new Fact[]{new CardFact(14,CardFact.AVAILABLE), 
        new CardFact(4,CardFact.AVAILABLE)}, 
            new Fact[]{new CardFact(4,CardFact.USE)},
            new Fact[]{new CardFact(14,CardFact.USE)},
            "aspi2", 1),
    
    boostR1(new Fact[]{new CardFact(13,CardFact.AVAILABLE), 
        new CardFact(1,CardFact.AVAILABLE), Fact.WALL_E_1},
            new Fact[]{new CardFact(1,CardFact.USE)},
            new Fact[]{new CardFact(13,CardFact.USE)},
            "boostR1", 1),
    
    boostR2(new Fact[]{new CardFact(13,CardFact.AVAILABLE), 
        new CardFact(1,CardFact.AVAILABLE), Fact.WALL_S_1},
            new Fact[]{new CardFact(1,CardFact.USE)},
            new Fact[]{new CardFact(13,CardFact.USE)},
            "boostR2", 1),
    
    boostR3(new Fact[]{new CardFact(13,CardFact.AVAILABLE), 
        new CardFact(4,CardFact.AVAILABLE), Fact.WALL_E_1},
            new Fact[]{new CardFact(4,CardFact.USE)},
            new Fact[]{new CardFact(13,CardFact.USE)},
            "boostR3", 1),
    
    boostR4(new Fact[]{new CardFact(13,CardFact.AVAILABLE), 
        new CardFact(4,CardFact.AVAILABLE), Fact.WALL_S_1},
            new Fact[]{new CardFact(4,CardFact.USE)},
            new Fact[]{new CardFact(13,CardFact.USE)},
            "boostR4", 1);
    
    
    
    //necessary facts to activate knowledge
    final FactBase POSTULATE;
    
    //fact incompatible witw the activation of the knowledge (temporary name)
    final FactBase INCOMPATIBLE;
    
    //facts created by knowledge
    final FactBase CONCLUSION;
    
    //nkowledge name
    final String NAME;
    
    //weight used when dealing with conflicts
    final int WEIGHT;
    
    private KnowledgeBase(Fact[] postulate, Fact[] incompatible, 
            Fact[] conclusion, String name, int weight){
        this.POSTULATE = (FactBase) new HashSet<Fact>(Arrays.asList(postulate));
        this.INCOMPATIBLE = (FactBase) new HashSet<Fact>(Arrays.asList(incompatible));
        this.CONCLUSION = (FactBase) new HashSet<Fact>(Arrays.asList(conclusion));
        this.NAME = name;
        this.WEIGHT = weight;
    }
    
    
    private KnowledgeBase(Fact[] postulate, Fact[] conclusion,
            String name, int weight){
        this.POSTULATE = (FactBase) new HashSet<Fact>(Arrays.asList(postulate));
        this.INCOMPATIBLE = (FactBase) new HashSet<Fact>();
        this.CONCLUSION = (FactBase) new HashSet<Fact>(Arrays.asList(conclusion));
        this.NAME = name;
        this.WEIGHT = weight;
    }
    

    /**
     * 
     * @param factBase
     * @return 
     */
    public boolean isApplicable(FactBase factBase){
        boolean isApplicable=true;

        Iterator it=this.POSTULATE.iterator();
        //Parcours la partie gauche
        while(it.hasNext() &&isApplicable){
            if(!factBase.contains((Fact)it.next())){
               isApplicable=false;
            }
        }
        //parcours des incompatibles si possible;
        if(isApplicable){
            it=this.INCOMPATIBLE.iterator();
            while(it.hasNext() && isApplicable){
                if(factBase.contains((Fact)it.next())){
                    isApplicable=false;
                }
            }
        }
        return isApplicable;
    }
    public FactBase apply(FactBase base) {
        //foreach donc on clone
        FactBase returnBase=(FactBase)base.clone();
        //ajout des conclusion à la base de faits.
        returnBase.addAll(this.CONCLUSION);
        return returnBase;
    }
    
}
