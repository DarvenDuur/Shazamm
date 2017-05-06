package game.gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GuiPlayer extends javax.swing.JFrame {

    // Variables declaration
    private JPanel cards, main;
    private Statistics statistics;
    private FlowLayout layout = new FlowLayout();
    // End of variables declaration


    /**
     * Creates new form GuiPlayer
     */
    public GuiPlayer(Statistics stat) {
        initComponents();
        initContents(stat);
        
        this.setVisible(true);
    }


    private void initContents(Statistics stat){

    	main.setLayout(layout); 

    	this.statistics = stat;
        this.cards = new Card();

    	main.add(new JScrollPane(statistics));
    	main.add(new bet());
    	main.add(new JScrollPane(cards));
    }


    private void initComponents() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Player");
        this.setSize(new java.awt.Dimension(1024, 800));
        this.setResizable(false);

        this.main = new JPanel();
        this.main.setBackground(new java.awt.Color(65, 75, 86));
        this.main.setPreferredSize(new java.awt.Dimension(1024, 800));
        
        this.add(main);

    }
}
