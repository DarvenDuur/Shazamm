/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.cards;

import game.Bridge;

/**
 *
 * @author darven
 */
public class Clone extends AbstractCard {
 
    
    
    @Override
    public void apply(Bridge bridge, boolean firstPlayer){
        if (super.generalApply(bridge, this, firstPlayer)){
            if (firstPlayer){
                //bridge.getPlayer2().get;
            }else{
                
            }
        }
    }
}
