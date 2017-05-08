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

    public GuiCard(AbstractCard card) {
        this.card = card;

        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(220, 300));

        Image icon = new ImageIcon(GuiConfig.PATH_IMG + "/cartes/" +
            card.getImageName()).getImage().getScaledInstance(200,
            250, Image.SCALE_DEFAULT);

        JLabel lab = new JLabel();
        lab.setIcon(new ImageIcon(icon));

        this.add(lab);
    }
    
    public AbstractCard mouseClicked(MouseEvent e) {
        return card;
    }
    
    public void mouseEntered(MouseEvent e) {
        this.setBackground(Color.WHITE);
    }
    
    public void update(){
        this.setBackground(Color.BLACK);
    }
}