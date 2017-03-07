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
        if (super.generalApply(bridge, firstPlayer)){
            if (firstPlayer){
                throw new UnsupportedOperationException("Clone does not work yet");
            }else{
                throw new UnsupportedOperationException("Clone does not work yet");
            }
        }
    }
}
