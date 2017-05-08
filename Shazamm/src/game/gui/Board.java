package game.gui;


import game.Bridge;
import game.Config;
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
    private int player1Pos = 7, player2Pos = 13, fireWallPos = 10;
    private int size = Config.BRIDGE_MAX_SIZE;
    private Image title;
    private ArrayList<Image> Bridge;
    private ArrayList<Image> Player;
    private ArrayList<Image> Firewall;
    private int posX, posY;
    // End of variables declaration

    /**
     * Creates new form Board
     */
    public Board() {
        initComponents();
        initContents();
    }

    public void initContents() {
        this.posX = 0;
        this.posY = 250;
        
        this.title = new ImageIcon(GuiConfig.PATH_IMG + "logo_shazamm.gif").getImage();
        this.Bridge = new ArrayList<>();
        this.Player = new ArrayList<>();
        this.Firewall = new ArrayList<>();
        
        int leftBridge = Config.BRIDGE_MAX_SIZE+1 - (size),
                rightBridge = Config.BRIDGE_MAX_SIZE+1 + (size);
        

        for(int i = 1; i < 20; i++){
            //background
            String tileType = "/lave/lave_";
            if (i >= leftBridge && i <= rightBridge){
        	tileType = "/pont/pont_";
            }
            
            if(i < 10){
                Bridge.add(new ImageIcon(GuiConfig.PATH_IMG + tileType + "0" + i + ".gif").getImage());
            } else {
                Bridge.add(new ImageIcon(GuiConfig.PATH_IMG + tileType + i + ".gif").getImage());
            }
            
            //players
            if (i == player2Pos){
        	Player.add(new ImageIcon(GuiConfig.PATH_IMG + "/perso/vert.gif").getImage());
            } else if (i == player1Pos){
                Player.add(new ImageIcon(GuiConfig.PATH_IMG + "/perso/rouge.gif").getImage());
            } else {
                Player.add(null);
            }
            
            //firewall
            if (i == fireWallPos){
                if(i < 10){
                    Firewall.add(new ImageIcon(GuiConfig.PATH_IMG + "/perso/feugauche.gif").getImage());
                } else if (i > 10){
                    Firewall.add(new ImageIcon(GuiConfig.PATH_IMG + "/perso/feudroite.gif").getImage());
                } else {
                    Firewall.add(new ImageIcon(GuiConfig.PATH_IMG + "/perso/feu.gif").getImage());
                }
            } else {
                Firewall.add(null);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(title, 150, 25, null);
        
        int bridgeX = 50, height;
        Image img;
        
        for (int i = 0; i< Bridge.size(); i++) {
            img = Bridge.get(i);
            height = img.getHeight(null);
            g.drawImage(img, bridgeX, 400 - height, null);
            
            img = Player.get(i);
            if (img != null){
                g.drawImage(img, bridgeX, 400 - height - img.getHeight(null), null);
            }
            
            img = Firewall.get(i);
            if (img != null){
                g.drawImage(img, bridgeX, 400 - height - img.getHeight(null), null);
            }
        
            bridgeX += 32;
        }
        
        this.repaint();
    }

                        
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(700, 400));
        
    }

    public void update (Bridge bridge){
        this.size = bridge.getSize();
        this.player1Pos = Config.BRIDGE_MAX_SIZE+1 +
                bridge.getPlayerState(true).getPosition();
        this.player2Pos = Config.BRIDGE_MAX_SIZE+1 +
                bridge.getPlayerState(false).getPosition();
        this.fireWallPos = Config.BRIDGE_MAX_SIZE+1 +
                bridge.getFirewallLocation();
        
        initContents();
    }
}