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
