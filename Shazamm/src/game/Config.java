/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author darven
 */
public class Config {
    
    public static final int BRIDGE_MAX_SIZE = 19 / 2; /* half the maximum total 
    size of the bridge, 0 : only the central portion remains */
    
    public static final int MAX_MANA=30; /*init mana value */
    
    public static final int SUFFLE_STEPS = 100; /* number of time two cards are
    swapped when CardShuffleList.shuffle() is used */
    
    public static final int HAND_MAX_SIZE = 5; /* max size of the hand */
}
