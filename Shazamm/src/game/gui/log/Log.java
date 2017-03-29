/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui.log;

import game.gui.Console;
import java.awt.Color;

/**
 *
 * @author mg
 */
public class Log {
    
    private final String DATE;
    
    /**
     * init the date at the current date;
     */
    public Log() {
        this.DATE = Console.getCurrentDate();
    }

    /**
     * @return the DATE
     */
    public String getDate() {
        return this.DATE;
    }
    
    public String toString(){
        return this.DATE+"\t";
    }
   
   
    
}
