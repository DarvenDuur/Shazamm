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
public class Rezilliance extends AbstractCard {
 
    /**
     * If the wall of fire were to advance towards the player, it does not move.
     * @param round
     * 
     * @author Adrien
     */
    @Override
    public void apply(Round round) {
        
        short winner =round.getLastTurn().getWinner();
        
        //case where the player 1 use this card and the card is effective
        if((isBelongPlayer1() &&  winner==1)){
            round.getLastBridge().setRezilliancePlayer1(true);
        }
        //case where the player 2 use this card and the card is effective
        else if(!isBelongPlayer1() && winner==-1){
            round.getLastBridge().setRezilliancePlayer2(true);
        }
    }
}
