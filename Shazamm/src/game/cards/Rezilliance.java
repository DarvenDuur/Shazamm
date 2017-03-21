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
public class Rezilliance extends AbstractCard {
 
    /**
     * If the wall of fire were to advance towards the player, it does not move.
     * @param round
     * 
     * @author Adrien
     */
    @Override
    public void apply(Round round) {
        //Application for player 1
        if(isBelongPlayer1() && !round.getSecondLastTurn().getIsPlayer1Winner()){
            round.getLastTurn().getBridge().getPlayerState1().setMana(
                round.getLastTurn().getBridge().getPlayerState1().getBet());
        }
        //Application for player 2
        else if(!isBelongPlayer1() && round.getSecondLastTurn().getIsPlayer1Winner()){
            round.getLastTurn().getBridge().getPlayerState2().setMana(
                round.getLastTurn().getBridge().getPlayerState2().getBet());
        }
    }
}
