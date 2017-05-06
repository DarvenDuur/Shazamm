package game;

/**
 * Player informations
 */
public class Player {
    // if true, will be displayed as green mage (will probably be removed)
    private final boolean GREEN;
    
    // name of the player
    private final String NAME;
    
//***************************** CONSTRUCTOR ************************************
    /**
     * @param name
     *      name of the player
     * @param green 
     *      true if has to be displayed as green mage
     */
    public Player(String name,boolean green) {
        this.GREEN = green;
        this.NAME = name;
    }

//***************************** GETTER *****************************************
    /**
     * @return 
     *      true if has to be displayed as green mage
     */
    public boolean getColor() {
        return GREEN;
    }

    /**
     * @return 
     *      the name of the player
     */
    public String getName() {
        return NAME;
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
        String str="";
        if(this.GREEN){
            str+="green ";
        }
        else{
            str+="red ";
        }
        return str+=this.NAME;
    }
    
        
}
