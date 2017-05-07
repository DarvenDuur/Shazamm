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
    private Board board;
	private Statistics statistics;
    private FlowLayout layout = new FlowLayout();
    private GuiPlayer player1, player2;
    // End of variables declaration


    /**
     * Creates new form Shazamm
     */
    public Shazamm() {
        initComponents();
        initContents();
        
        this.add(main);
    }

    
    private void initContents(){

    	main.setLayout(layout); 

    	this.statistics = new Statistics();
    	
    	this.player1 = new GuiPlayer(statistics); 
    	this.player2 = new GuiPlayer(statistics); 
    	
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


    public void run() {
        new Shazamm().setVisible(true);
    }
    
    
    public void update(){
    	board.initContents();
    	player1.update();
    	player2.update();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Shazamm().setVisible(true);
            }
        });
    }
}