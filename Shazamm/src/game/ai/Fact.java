package game.ai;

import java.util.Objects;

/**
 * Fact representing information about current situation
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
            WALL_E_2 = new Fact("wal0lS1"),
            WALL_S_1 = new Fact("wallE2"),
            
            MANA_BIG_A = new Fact("manaBigA"),
            MANA_SMALL_A = new Fact("manaSmallA"),
            MANA_SMALL_D = new Fact("manaSmallD"),
            MANA_SMALL_R = new Fact("manaSmallR"),
            
            Z2 = new Fact("Z2"),
            BEGIN = new Fact("begin");

    //fact name, used for hash and display
    private String name;

    /**
     * @param name 
     *      name of the fact
     */
    protected Fact(String name) {
        this.name = name;
    }
    
    @Override
    public int compareTo(Fact o) {
        if (o instanceof Fact) {
            return this.hashCode() - o.hashCode();
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.name.hashCode();
        return hash;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fact other = (Fact) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
