package game.gui;

import game.Turn;
import game.cards.AbstractCard;
import java.awt.FlowLayout;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GuiPlayer extends javax.swing.JFrame {

    // Variables declaration
    private JPanel main;
    private Bet betPanel;
    private Card cards;
    private Statistics statistics;
    private FlowLayout layout = new FlowLayout();
    
    private final boolean PLAYER_1;
    // End of variables declaration


    /**
     * Creates new form GuiPlayer
     */
    public GuiPlayer(boolean player1) {
        PLAYER_1 = player1;
        initComponents();
        initContents();
        
        this.setVisible(true);
    }


    private void initContents(){

    	main.setLayout(layout); 

    	this.statistics = new Statistics();
        this.cards = new Card(PLAYER_1);
        this.betPanel = new Bet(PLAYER_1);
        
    	main.add(new JScrollPane(statistics));
    	main.add(betPanel);
    	main.add(new JScrollPane(cards));
    }

    
    public void update(Turn t){
        statistics.initContents();
        cards.initContents(t);
        betPanel.initContents(PLAYER_1);
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

    public HashSet<AbstractCard> askCards() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
