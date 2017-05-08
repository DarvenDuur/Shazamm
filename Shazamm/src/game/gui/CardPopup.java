package game.gui;

import game.cards.AbstractCard;
import game.cards.Clone;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author Reiji
 */
public class CardPopup extends JOptionPane{

    private final ArrayList<AbstractCard> CARDS;
    private final Card GUI;
    private final Clone CLONE;
    
    public CardPopup(Card gui, ArrayList<AbstractCard> cards, Clone clone){
        this.CARDS = cards;
        this.GUI = gui;
        this.CLONE = clone;
        
        this.add(gui);
    }
    
    public void askClone(){// on click
        AbstractCard card = this.getCard();
        CLONE.setClone(card);
        this.hide();
        
        if (card instanceof Clone){
            new CardPopup(this.GUI, this.GUI.getSelfPlayedCards(), Card.getClone(this.CARDS));
        }
    }
    
    public AbstractCard getCard(){
        
        
        return null;
    }

}