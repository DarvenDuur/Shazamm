/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shazamm;

import game.Game;
import game.gui.Console;

/**
 *
 * @author darven
 */
public class Shazamm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* // TEST
        boolean graphicalMode = Console.getConfirmation("G-Mode?");
        if (graphicalMode){graphicMode();}
        // END TEST */
        
        boolean activateAI = Console.getConfirmation(
                "Do you wish to play against AI?"
                        + " Otherwise you will play with two players.");
        Game game = new Game(activateAI);
        game.play();
    }
    
    /**
     * TEST USE ONLY
     */
    private static void graphicMode(){
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new game.gui.Shazamm().setVisible(true);
            }
        });
    }
}
