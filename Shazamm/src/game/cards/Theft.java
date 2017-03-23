/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.Bridge;
import game.Round;

/**
 *
 * @author darven
 */
public class Theft extends AbstractCard {
    
    public Theft(boolean belongPlayer1) {
        this.id = CardsEnum.Theft.getId();
        this.belongPlayer1=belongPlayer1;
    }
   

    @Override
    public void apply(Round round) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
