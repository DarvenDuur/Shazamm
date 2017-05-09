package game;

import game.gui.GuiPlayer;

/**
 * Player informations
 */
public class Player {
    
    // name of the player
    private final String NAME;
    
    private GuiPlayer gui;
    
//***************************** CONSTRUCTOR ************************************
    /**
     * @param name
     *      name of the player
     * @param green 
     *      true if has to be displayed as green mage
     */
    public Player(String name) {
        this.NAME = name;
    }

//***************************** GETTER *****************************************
    /**
     * @return 
     *      the name of the player
     */
    public String getName() {
        return NAME;
    }

    /**
     * @return the gui
     */
    public GuiPlayer getGui() {
        return gui;
    }

//***************************** OTHER ******************************************
    /**
     * For console printing
     * @see 
     *      Object.toString()
     * @return 
     *      String representing the player
     */
    @Override
    public String toString(){
        return this.NAME;
    }

    /**
     * @param gui the gui to set
     */
    public void setGui(GuiPlayer gui) {
        this.gui = gui;
    }
    
        
}
