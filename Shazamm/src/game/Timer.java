/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.gui.Console;
import game.gui.GuiConfig;
import java.util.Date;

/**
 *
 * @author MG
 */
public class Timer {
    //FAIRE TITRE
   
    public static void main(String[] args){
        //recuperation voir ou  inserer
        long timeLimit=Console.getIntInput(GuiConfig.TIME_LIMIT)*1000;
        
        
        //Algo debut du prog de decision
        long timeBegin=Config.DATE.getTime();
        //ligne de code 
        
        
        if(!(timeLimit<=0) && timeBegin-Config.DATE.getTime()>=timeLimit){
            
        }
        
        
        
        
        
    }    
}
