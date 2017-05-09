package game.gui;

import game.cards.AbstractCard;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * @author Reiji
 */
public class GuiCard extends javax.swing.JPanel{
    private AbstractCard card;
    private boolean active;

    public GuiCard(AbstractCard card) {
        this.card = card;

        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(220, 300));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        Image icon = new ImageIcon(GuiConfig.PATH_IMG + "/cartes/" +
            card.getImageName()).getImage().getScaledInstance(200,
            250, Image.SCALE_DEFAULT);

        JLabel lab = new JLabel();
        lab.setIcon(new ImageIcon(icon));

        this.add(lab);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                active = !active;
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent me) {}

            @Override
            public void mouseReleased(MouseEvent me) {}
        });
    }
    
}