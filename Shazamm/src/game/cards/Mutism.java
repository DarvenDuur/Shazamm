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
public class Mutism extends AbstractCard {

    public Mutism() {
        this.id = CardsEnum.Mutism.getId();
        this.name = CardsEnum.Mutism.getName();
        this.imageName = CardsEnum.Mutism.getImageName();
        this.description = CardsEnum.Mutism.getDescription();
    }
    
    @Override
    public void apply(Bridge bridge, boolean firstPlayer){
        if (super.generalApply(bridge, firstPlayer)){
            bridge.setMute();
        }
    }
}
