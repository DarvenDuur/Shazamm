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
public class Scrooge extends AbstractCard {

    /**
     * If this turn is lost, the stake is not removed from my Mana reserve.
     * @param round
     * 
     * @author Adrien
     */
    @Override
    public void apply(Round round) {
        //Application for player 1
        if(isBelongPlayer1()){
            round.getLastTurn().getBridge().getPlayerState1().setMana(
                round.getLastTurn().getBridge().getPlayerState1().getBet());
        }
        //Application for player 2
        else{
            round.getLastTurn().getBridge().getPlayerState2().setMana(
                round.getLastTurn().getBridge().getPlayerState2().getBet());
        }
    }
}
