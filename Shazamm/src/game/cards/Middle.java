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
public class Middle extends AbstractCard {
    
    public Middle(boolean belongPlayer1) {
        this.id = CardsEnum.Middle.getId();
        this.belongPlayer1=belongPlayer1;
    }
    
    @Override
    protected void apply(Round round){
        int player1Location = round.getLastPlayerState1().getPosition();
        int player2Location = round.getLastPlayerState2().getPosition();
        
        int newLocation;
        /* if players are separated by a pair number of tiles, round position
        in favor of the player using this card */
        //if player1Location + player2Location unpair
        if ((player1Location + player2Location) % 2 != 0){
            
            //if played by player 1
            if (this.isBelongPlayer1()){
                newLocation = (player1Location + player2Location + 1) / 2;
                
            //if played by player 2
            }else{
                newLocation = (player1Location + player2Location - 1) / 2;
            }
        }else{
            newLocation = (player1Location + player2Location) / 2;
        }
        
        round.getLastBridge().setFirewallLocation(newLocation);
    }
}
