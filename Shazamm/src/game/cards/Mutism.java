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
public class Mutism extends AbstractCard {

    public Mutism() {
        this.id = CardsEnum.Mutism.getId();
    }
    
    @Override
    public void apply(Round round){
        round.getLastTurn().setMute();
    }
}
