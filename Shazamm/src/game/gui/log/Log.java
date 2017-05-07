package game.gui.log;

import game.gui.Console;

/**
 * Basic log containing its creation date
 */
public abstract class Log {
    // Creation date, used when printing
    private final String DATE;
    
//***************************** CONSTRUCTOR ************************************
    /**
     * Initialize the date at the current date;
     */
    public Log() {
        this.DATE = Console.getCurrentDate();
    }

//***************************** GETTER *****************************************
    /**
     * @return 
     *      creation date
     */
    public String getDate() {
        return this.DATE;
    }
    
//***************************** OTHER ******************************************
    /**
     * For console printing
     * @see 
     *      Object.toString()
     * @return 
     *      String containing formated creation date
     */
    @Override
    public String toString() {
        return this.DATE;
    }

}
