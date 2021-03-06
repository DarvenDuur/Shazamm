/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shazamm;

import game.Game;
import game.GameGraphical;
import game.gui.Console;
import game.gui.GuiConfig;

/**
 * main class, get modes for game (gui and AI)
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
            } else {
                GuiConfig.guiMode = game.gui.Shazamm.YesNo(GuiConfig.GUI_MODE_ASK);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            GuiConfig.guiMode = game.gui.Shazamm.YesNo(GuiConfig.GUI_MODE_ASK);
        }
        
        //apply gui choice
        boolean activateAI = false;
        if (GuiConfig.guiMode){
            //ask ai mode
            activateAI = game.gui.Shazamm.YesNo(GuiConfig.AI_MODE_ASK);
        } else {
            activateAI = Console.getConfirmation(GuiConfig.AI_MODE_ASK);
        }
        
        if (GuiConfig.guiMode){
            GameGraphical game = new GameGraphical(activateAI);
            game.play();
        } else {
            Game game = new Game(activateAI);
            game.play();
        }
        
        
    }
}
