package game.gui;

import game.cards.AbstractCard;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * @author Reiji
 */
public class GuiCard extends javax.swing.JPanel {
    private AbstractCard card;
    private boolean active;
    

    public GuiCard(AbstractCard card) {
        this.card = card;
        this.active = false;
        
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(210, 340));

        Image icon = new ImageIcon(GuiConfig.PATH_IMG + "/cartes/" +
            card.getImageName()).getImage().getScaledInstance(200,
            320, Image.SCALE_DEFAULT);

        JLabel lab = new JLabel();
        lab.setIcon(new ImageIcon(icon));

        this.add(lab);
    }
    
    public void mouseClicked(MouseEvent e) {
        this.active = !active;
    }
    
    public void mouseEntered(MouseEvent e) {
        this.setBackground(Color.WHITE);
    }
    
    public AbstractCard getActive(){
        if(active){
            return card;
        }
        else{
            return null;
        }
    }
    
    public void update(){
        if(active){
            this.setBackground(Color.WHITE);
        }
    }
}