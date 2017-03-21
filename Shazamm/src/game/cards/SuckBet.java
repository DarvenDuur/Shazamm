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
public class SuckBet extends AbstractCard {

    @Override
    public void apply(Round round) {
        if(isBelongPlayer1()){
            round.getLastTurn().getBridge().getPlayerState1().setMana(
                round.getLastTurn().getBridge().getPlayerState2().getBet());
        }
        else{
            round.getLastTurn().getBridge().getPlayerState2().setMana(
                round.getLastTurn().getBridge().getPlayerState1().getBet());
        }
    }
}
