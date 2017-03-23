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

    /**
     * Constructor for Mutism
     * @param belongPlayer1 
     */
    public Mutism(boolean belongPlayer1) {
        this.id = CardsEnum.Mutism.getId();
        this.belongPlayer1=belongPlayer1;
    }
    
    @Override
    public void apply(Round round){
        round.getLastTurn().setMute();
    }
}
