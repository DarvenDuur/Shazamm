/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.Round;

/**
 *
 * @author darven
 */
public class StockBoost extends AbstractCard {

    public StockBoost(boolean belongPlayer1) {
        this.id = CardsEnum.StockBoost.getId();
        this.belongPlayer1=belongPlayer1;
    }
    
    /**
     * Mana reserve increases by 13 points. After I paid what I owe.
     * @param round
     * 
     * @author Adrien
     */
    @Override
    protected void apply(Round round) {
        super.getOwnerPLayer(round).addMana(13);
    }
}
