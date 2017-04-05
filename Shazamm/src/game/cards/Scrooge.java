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
        this.ID = CardsEnum.Scrooge.getId();
        this.BELONG_PLAYER_1=belongPlayer1;
    }
    
    /**
     * If this turn is lost, the stake is not removed from my Mana reserve.
     * @param round
     * 
     * @author Adrien
     */
    @Override
    protected void apply(Round round) {
        if ((this.isUsedPlayer1() && round.getLastTurn().getWinner() == -1) || 
                (!this.isUsedPlayer1() && round.getLastTurn().getWinner() == 1)){
            
            PlayerState player=super.getUserPLayer(round);
            player.addMana(super.getUserPLayer(round).getBet());
        }
    }
}
