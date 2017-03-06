/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Scanner;

/**
 * Fake classe
 * @author mg
 */
public class Test {
    private final static Scanner SCANNER=new Scanner(System.in);
    
    public static String printAndReception(String string) {
        System.out.println(string);
        return SCANNER.next();
    }
    
}
