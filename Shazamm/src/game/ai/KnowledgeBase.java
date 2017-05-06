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
    
    mutisme1(new Fact[]{new CardFact(1,'a'), Fact.MANA_BIG_A}, 
            new Fact[]{new CardFact(1,'u')},
            "mutisme1", 0),
    
    mutisme2(new Fact[]{new CardFact(1,'a'), Fact.WALL_E_1}, 
            new Fact[]{new CardFact(1,'u'), Fact.BET_BIG},
            "mutisme2", 0),
    
    mutisme3(new Fact[]{new CardFact(1,'a'), Fact.MANA_SMALL_A, Fact.BEGIN}, 
            new Fact[]{new CardFact(1,'u')},
            "mutisme3", 0),
    
    larcin1(new Fact[]{new CardFact(3,'a'), new CardFact(10,'a'), Fact.WALL_E_2},
            new Fact[]{new CardFact(3,'u'), new CardFact(10,'u')},
            "larcin1", 0),
    
    larcin2(new Fact[]{new CardFact(3,'a'), Fact.WALL_E_1}, 
            new Fact[]{new CardFact(3,'u')},
            "larcin2", 0),
    
    FDM(new Fact[]{new CardFact(4,'a'), Fact.WALL_E_1},
            new Fact[]{new CardFact(4,'u')},
            "FDM", 0),
    
    milieu(new Fact[]{new CardFact(5,'a'), Fact.WALL_S_1},
            new Fact[]{new CardFact(5,'u')}, "milieu", 0),
    
    recy1(new Fact[]{new CardFact(6, 'a')},
            new Fact[]{new CardFact(6, 'u'), Fact.BET_6},
            "recy1", 0),
    
    recy2(new Fact[]{new CardFact(6, 'u'), new CardFact(9, 'e')}, 
            new Fact[]{Fact.BET_ENLARGE},
            "recy2", 0),
    
    boostDouble(new Fact[]{new CardFact(7,'a'), new CardFact(8,'a')}, 
            new Fact[]{new CardFact(7,'u'), new CardFact(8,'u'), Fact.BET_SMALL},
            "boostDouble", 0),
    
    boost(new Fact[]{new CardFact(7,'a'), Fact.MANA_SMALL_R}, 
            new Fact[]{Fact.BEGIN},
            new Fact[]{new CardFact(7,'u'), Fact.BET_1},
            "boost", 0),
    
    qpg(new Fact[]{new CardFact(9,'a'), Fact.MANA_SMALL_R},
            new Fact[]{Fact.BET_SMALL},
            "qpg", 0),
    
    qpgAfter1(new Fact[]{new CardFact(9,'s'), new CardFact(1,'a')}, 
            new Fact[]{new CardFact(1,'u')},
            "qpgAfter1", 0),
    
    qpgAfter2(new Fact[]{new CardFact(9,'s'), new CardFact(3,'a')}, 
            new Fact[]{new CardFact(3,'u')},
            "qpgAfter2", 0),
    
    qpgAfter3(new Fact[]{new CardFact(9,'s'), new CardFact(6,'a')},  
            new Fact[]{new CardFact(6,'u')},
            "qpgAfter3", 0),
    
    qpgAfter4(new Fact[]{new CardFact(9,'s'), new CardFact(11,'a')},  
            new Fact[]{new CardFact(11,'u')},
            "qpgAfter4", 0),
    
    qpgAfter5(new Fact[]{new CardFact(9,'s'), new CardFact(10,'a')}, 
            new Fact[]{new CardFact(10,'u')},
            "qpgAfter5", 0),
    
    qpgAfter6(new Fact[]{new CardFact(9,'s')},
            new Fact[]{new CardFact(1,'a'), new CardFact(3,'a'), 
                new CardFact(6,'a'), new CardFact(11,'a'), new CardFact(10,'a')},
            new Fact[]{Fact.BET_1},
            "qpgAfter6", 0),
    
    brasier1(new Fact[]{new CardFact(3,'a'), new CardFact(10,'a'), Fact.WALL_E_2},
            new Fact[]{new CardFact(10,'u'), new CardFact(3,'u')},
            "brasier1", 0),
    
    brasier2(new Fact[]{new CardFact(10,'a'), Fact.WALL_E_2},
            new Fact[]{new CardFact(3,'a')}, 
            new Fact[]{new CardFact(10,'u'), Fact.BET_ENLARGE},
            "brasier2", 0),
    
    res1(new Fact[]{new CardFact(10,'a'), new CardFact(11,'a')},
            new Fact[]{new CardFact(10,'u'), new CardFact(11,'u')},
            "res1", 0),
    
    res2(new Fact[]{new CardFact(11,'a'), Fact.WALL_S_1},
            new Fact[]{new CardFact(11,'u'), Fact.BET_SMALL},
            "res2", 0),
    
    harpagon(new Fact[]{new CardFact(12,'a'), Fact.Z2, Fact.MANA_SMALL_D},
            new Fact[]{new CardFact(12,'u')},
            "harpagon", 0),
    
    boostR1(new Fact[]{new CardFact(13,'a'), new CardFact(1,'a'), Fact.WALL_E_1},
            new Fact[]{new CardFact(1,'u')},
            new Fact[]{new CardFact(13,'u')},
            "boostR1", 1),
    
    boostR2(new Fact[]{new CardFact(13,'a'), new CardFact(1,'a'), Fact.WALL_S_1},
            new Fact[]{new CardFact(1,'u')},
            new Fact[]{new CardFact(13,'u')},
            "boostR2", 1),
    
    boostR3(new Fact[]{new CardFact(13,'a'), new CardFact(4,'a'), Fact.WALL_E_1},
            new Fact[]{new CardFact(4,'u')},
            new Fact[]{new CardFact(13,'u')},
            "boostR3", 1),
    
    boostR4(new Fact[]{new CardFact(13,'a'), new CardFact(4,'a'), Fact.WALL_S_1},
            new Fact[]{new CardFact(4,'u')},
            new Fact[]{new CardFact(13,'u')},
            "boostR4", 1),
    
    boostRAfter1(new Fact[]{new CardFact(13,'s'), new CardFact(1,'a')}, 
            new Fact[]{new CardFact(1,'u')},
            "boostRAfter1", 0),
    
    boostRAfter2(new Fact[]{new CardFact(13,'s'), new CardFact(4,'a')},
            new Fact[]{new CardFact(4,'u')},
            "boostRAfter2", 0),
    
    aspi1(new Fact[]{new CardFact(14,'a'), new CardFact(1,'a')}, 
            new Fact[]{new CardFact(14,'u')},
            "aspi1", 0),
    
    aspi2(new Fact[]{new CardFact(14,'a'), new CardFact(4,'a')}, 
            new Fact[]{new CardFact(4,'u')},
            new Fact[]{new CardFact(14,'u')},
            "aspi2", 0),
    
    aspiAfter1(new Fact[]{new CardFact(14,'s')}, 
            new Fact[]{new CardFact(1,'u')},
            "aspiAfter1", 0),
    
    aspiAfter2(new Fact[]{new CardFact(14,'s')}, 
            new Fact[]{new CardFact(4,'u')},
            "aspiAfter2", 0);
    
    
    
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
    
    private KnowledgeBase(Fact[] postulate, Fact[] incompatible, Fact[] conclusion,
            String name, int weight){
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
