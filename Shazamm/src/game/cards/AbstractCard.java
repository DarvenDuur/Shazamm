/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.Bridge;

/**
 *
 */
public abstract class AbstractCard implements Cloneable {
    protected int id; //from 1 to 14
    protected String name; //card name used for display
    protected String imageName; //image file name, do not contain extension
    protected String description; //decription of the card usage and effects
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        AbstractCard clone = (AbstractCard) super.clone();
        return clone;
    }
    
    public abstract void apply(Bridge bridge);

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
