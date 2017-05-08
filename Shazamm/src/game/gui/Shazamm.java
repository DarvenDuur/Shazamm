/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.Config;
import game.Player;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * @author Adrien
 */
public class Shazamm extends javax.swing.JFrame {

    // Variables declaration
    private JPanel main;
    private static Board board;
    private FlowLayout layout = new FlowLayout();
    private static Player player1, player2;
    private static boolean onePlayer;
    // End of variables declaration


    /**
     * Creates new form Shazamm
     */
    public Shazamm(boolean onePlayer, Player p1, Player p2) {
        initComponents();
        initContents(onePlayer, p1, p2);
        
        this.add(main);
    }

    public static boolean YesNo(String s){
        if (JOptionPane.showConfirmDialog(null, s, "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
    
    public static String getName(String message){
        
        String s = (String) JOptionPane.showInputDialog(
            null,
            message,
            "Name",
            JOptionPane.PLAIN_MESSAGE,
            null,
            null,
            null);

        //If a string was returned, say so.
        if ((s == null) && (s.length() == 0) || s.equals(Config.AI_NAME)) {
            s = getName(message);
        }
        
        return s;
    }
    
    
    private void initContents(boolean onePlayer, Player p1, Player p2){

    	main.setLayout(layout);

        this.onePlayer = onePlayer;
    	
    	this.player1 = p1;
        p1.setGui(new GuiPlayer(true));
        if(!onePlayer){
            this.player2 = p2;
            p2.setGui(new GuiPlayer(false));
        }
    	
        this.board = new Board();
    	
    	main.add(board);
    }


    private void initComponents() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Shazamm");
        this.setSize(new java.awt.Dimension(700, 420));
        this.setResizable(false);

        this.main = new JPanel();
        this.main.setBackground(new java.awt.Color(0, 0, 0));
    }


    public static void run(boolean onePlayer, Player p1, Player p2){
        new Shazamm(onePlayer, p1, p2).setVisible(true);
    }


    public static void update(){
    	board.initContents();
    }
}