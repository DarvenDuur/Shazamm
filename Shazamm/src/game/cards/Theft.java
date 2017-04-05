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
public class Theft extends AbstractCard {
    
    public Theft(boolean belongPlayer1) {
        this.id = CardsEnum.Theft.getId();
        this.belongPlayer1=belongPlayer1;
    }
   

    @Override
    protected void apply(Round round) {
        if (this.isUsedPlayer1()){
            round.getLastTurn().setPlayer1Theft();
        }else{
            round.getLastTurn().setPlayer2Theft();
        }
    }
}
