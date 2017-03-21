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

    /**
     * Mana reserve increases by 13 points. After I paid what I owe.
     * @param round
     * 
     * @author Adrien
     */
    @Override
    public void apply(Round round) {
        //Application for player 1
        if(isBelongPlayer1()){
            round.getLastTurn().getBridge().getPlayerState1().setMana(13);
        }
        //Application for player 2
        else{
            round.getLastTurn().getBridge().getPlayerState2().setMana(13);
        }
    }
}
