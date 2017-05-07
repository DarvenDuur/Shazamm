/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shazamm;

import game.Game;
import game.gui.Console;
import game.gui.GuiConfig;

/**
 *
 * @author darven
 */
public class Shazamm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            //extract mode fr
            if (args[0].equals("console") || args[0].equals("-c")){
                //run as console
                GuiConfig.guiMode = false;

            } else if (args[0].equals("graphical") || args[0].equals("-g")){
                //run graphical
                GuiConfig.guiMode = true;
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            
        }
        
        //apply gui choice
        boolean activateAI = false;
        if (GuiConfig.guiMode){
            //ask ai mode
            //activateAI = false;
        } else {
            activateAI = Console.getConfirmation(
                    "Do you wish to play against AI?"
                            + " Otherwise you will play with two players.");
        }
            
        Game game = new Game(activateAI);
        
        // start gui
        game.gui.Shazamm.run(activateAI);
        
        game.play();
    }
}
