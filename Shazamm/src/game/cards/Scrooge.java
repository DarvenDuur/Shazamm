/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.PlayerState;
import game.Round;

/**
 *
 * @author darven
 */
public class Scrooge extends AbstractCard {

    
    public Scrooge(boolean belongPlayer1) {
        this.id = CardsEnum.Scrooge.getId();
        this.belongPlayer1=belongPlayer1;
    }
    
    /**
     * If this turn is lost, the stake is not removed from my Mana reserve.
     * @param round
     * 
     * @author Adrien
     */
    @Override
    public void apply(Round round) {
        PlayerState player=super.getOwnerPLayer(round);
        player.addMana(super.getOwnerPLayer(round).getBet());
    }
}
