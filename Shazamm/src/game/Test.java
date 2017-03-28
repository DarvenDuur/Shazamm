/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.gui.Console;
import java.util.Scanner;

/**
 * Fake classe
 * @author mg
 */
public class Test {
    private final static Scanner SCANNER=new Scanner(System.in);
    
    public static String printAndReception(String string) {
        return Console.getInput(string);
    }
    public static int printAndIntReception(String string) {
        return Console.getIntInput(string);
    }
    
}
