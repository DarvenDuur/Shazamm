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
    
    private final String COLOR;// TO DO
    
    private final String NAME;// TO DO
    
//***************************** CONSTRUCTOR ************************************
    
    public Player(String color, String name) {
        this.COLOR = color;
        this.NAME = name;
    }

//***************************** GETTER *****************************************

    /**
     * @return the COLOR
     */
    public String getColor() {
        return COLOR;
    }

    /**
     * @return the NAME
     */
    public String getName() {
        return NAME;
    }
    
//***************************** SETTER *****************************************
}
