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
    private final String COLOR;
    private final String NAME;

    public Player(String color, String name) {
        this.COLOR = color;
        this.NAME = name;
    }

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
}
