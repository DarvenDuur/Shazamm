/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ai.fact;

/**
 *
 * @author darven
 */
public class Fact implements Comparable<Fact>{
    //usual facts
    public static final Fact 
            BET_1 = new Fact("bet1"),
            BET_2 = new Fact("bet2"),
            BET_6 = new Fact("bet6"),
            BET_RELATIVE = new Fact("betRelative"),
            BET_SMALL = new Fact("betSmall"),
            BET_BIG = new Fact("betBig"),
            BET_ENLARGE = new Fact("betEnlarge"),
            
            WALL_E_1 = new Fact("wallE1"),
            WALL_E_2 = new Fact("wallS1"),
            WALL_S_1 = new Fact("wallE2"),
            
            MANA_BIG_A = new Fact("manaBigA"),
            MANA_SMALL_A = new Fact("manaSmallA"),
            MANA_SMALL_D = new Fact("manaSmallD"),
            MANA_SMALL_R = new Fact("manaSmallR"),
            
            Z2 = new Fact("Z2"),
            Z2_NOT = new Fact("Z2Not"),
            BEGIN = new Fact("begin"),
            BEGIN_NOT = new Fact("beginNot");

    private String name;

    public Fact(String name) {
        this.name = name;
    }
    
    
    
    /**
     * @deprecated 
     */
    @Override
    public int compareTo(Fact o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * @deprecated 
     */
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
