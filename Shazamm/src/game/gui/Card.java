package game.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Card extends javax.swing.JPanel {
	
	private final String path = "../Graphique/img/cartes/";
	
    private ArrayList<Image> Cards;
	
	
    /**
     * Creates new panel of test
     */
    public Card() {
        initComponents();
        initContents();
    }

    private void initContents() {
        
        this.Cards = new ArrayList<>();
        
        for(int i = 1; i < 6; i++){
        	Cards.add(new ImageIcon(path + "0" + i + "v.jpg").getImage());
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        int bridgeX = 30;
        
        for (Image img : Cards){
            g.drawImage(img, bridgeX, 70, img.getWidth(null) / 2, img.getHeight(null) / 2, null);
            bridgeX += 130;
        }
        this.repaint();
    }

    

    private void initComponents() {

        this.setBackground(new java.awt.Color(33, 41, 48));
        this.setPreferredSize(new java.awt.Dimension(700, 300));
        
        this.setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());
    }
}
