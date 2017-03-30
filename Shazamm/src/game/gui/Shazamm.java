/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import java.awt.FlowLayout;
import javax.swing.JPanel;


/**
 *
 * @author Adrien
 */
public class Shazamm extends javax.swing.JFrame {

    // Variables declaration
    private JPanel board, statistics;
    private FlowLayout layout = new FlowLayout();
    // End of variables declaration
    
	
    /**
     * Creates new form Shazamm
     */
    public Shazamm() {
        initComponents();
        initContents();
    }

    
    private void initContents(){
    	
    	this.setLayout(layout); 
    	
    	this.statistics = new Statistics();
        this.board = new Board(0, 250);

    	this.add(statistics);
    	this.add(board);

    	this.add(new test());
    	this.add(new test());

    }

    private void initComponents() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Shazamm");
        this.setBackground(new java.awt.Color(0, 0, 0));
        this.setSize(new java.awt.Dimension(1024, 800));
        this.setResizable(false);
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
