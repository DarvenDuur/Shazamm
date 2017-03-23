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
public class Clone extends AbstractCard {
    
     public Clone(boolean belongPlayer1) {
        this.id = CardsEnum.Clone.getId();
        this.belongPlayer1=belongPlayer1;
    }
   
    @Override
    protected void apply(Round round) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
