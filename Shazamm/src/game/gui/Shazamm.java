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
 *
 * @author Adrien
 */
public class Shazamm extends javax.swing.JFrame {

    // Variables declaration
    private JPanel board, statistics, cards, main;
    private FlowLayout layout = new FlowLayout();
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
        this.board = new Board(0, 250);
        this.cards = new Card();

    	main.add(statistics);
    	main.add(board);
    	main.add(new bet());
    	main.add(new JScrollPane(cards));
    }

    public void run() {
        new Shazamm().setVisible(true);
    }

    private void initComponents() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Shazamm");
        this.setSize(new java.awt.Dimension(1024, 800));
        this.setResizable(false);

        this.main = new JPanel();
        this.main.setBackground(new java.awt.Color(65, 75, 86));
        this.main.setPreferredSize(new java.awt.Dimension(1024, 800));
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