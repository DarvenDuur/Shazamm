/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author darven
 */
public class Player {
    
    private final boolean GREEN;// TO DO
    
    private final String NAME;// TO DO
    
    
    public Player(String name,boolean green) {
        this.GREEN = green;
        this.NAME = name;
    }


    /**
     * @return the COLOR
     */
    public boolean getColor() {
        return GREEN;
    }

    /**
     * @return the NAME
     */
    public String getName() {
        return NAME;
    }
    
}
