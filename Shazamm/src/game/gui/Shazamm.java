/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * @author Adrien
 */
public class Shazamm extends javax.swing.JFrame {

    // Variables declaration
    private JPanel main;
    private static Board board;
    private Statistics statistics;
    private FlowLayout layout = new FlowLayout();
    private static GuiPlayer player1, player2;
    private static boolean onePlayer;
    // End of variables declaration


    /**
     * Creates new form Shazamm
     */
    public Shazamm(boolean onePlayer) {
        initComponents();
        initContents(onePlayer);
        
        this.add(main);
    }

    
    private void initContents(boolean onePlayer){

    	main.setLayout(layout);

        this.onePlayer = onePlayer;
    	this.statistics = new Statistics();
    	
    	this.player1 = new GuiPlayer(statistics);
        if(!onePlayer){
            this.player2 = new GuiPlayer(statistics); 
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


    public static void run(boolean onePlayer){
        new Shazamm(onePlayer).setVisible(true);
    }


    public static void update(){
    	board.initContents();
    	player1.update();
        
        if(!onePlayer){
            player2.update(); 
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        run(false);
    }
}