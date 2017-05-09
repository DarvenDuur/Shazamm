/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bdd;

import game.Config;

/**
 *
 * @author darven
 */
public class test {
    public static void main(String[] args) {
        //update database
        ConnexionBDD bdd = new ConnexionBDD(Config.BDD_NAME, 
                Config.BDD_USERNAME, Config.BDD_PASSWORD);
        bdd.test();
        
    }
}
