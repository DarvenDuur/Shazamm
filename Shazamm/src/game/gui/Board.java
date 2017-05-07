package game.gui;


import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;


/**
 *
 * @author Adrien
 */
public class Board extends javax.swing.JPanel {

    // Variables declaration
    private Image title;
    private ArrayList<Image> Bridge;
    private int posX, posY;
    // End of variables declaration

    /**
     * Creates new form Board
     */
    public Board(int x, int y) {
        initComponents();
        initContents(x, y);
    }

    private void initContents(int x, int y) {
        this.posX = x;
        this.posY = y;
        
        this.title = new ImageIcon(GuiConfig.PATH_IMG + "logo_shazamm.gif").getImage();
        this.Bridge = new ArrayList<>();
        
        for(int i = 1; i < 20; i++){
        	if(i < 10){
                    Bridge.add(new ImageIcon(GuiConfig.PATH_IMG + "/pont/pont_0" + i + ".gif").getImage());
        	}else{
                    Bridge.add(new ImageIcon(GuiConfig.PATH_IMG + "/pont/pont_" + i + ".gif").getImage());
        	}
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(title, 150, 25, null);
        
        int bridgeX = 50;
        
        for (Image img : Bridge){
            g.drawImage(img, bridgeX, 400 - img.getHeight(null), null);
            bridgeX += 32;
        }
        this.repaint();
    }

                        
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(700, 400));
        
    }

}