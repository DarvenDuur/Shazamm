package game.gui;

import game.Player;
import game.Turn;
import game.cards.AbstractCard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Card extends javax.swing.JPanel {
	
    private ArrayList<Image> Cards;
    private ArrayList<JCheckBox> chbx;
    private JPanel main;
    private Turn turn;
	
	
    /**
     * Creates new panel of test
     */
    public Card(Turn turn) {
        initComponents();
        initContents(turn);
        
        this.add(new JScrollPane(main));
    }


    private void initComponents() {

    	main = new JPanel();
    	main.setBackground(new Color(0, 0, 0));

        main.setMaximumSize(new Dimension(2700, 350));
        
        this.setPreferredSize(new Dimension(700, 350));
        
    }
    
    
    protected void initContents(Turn t) {
        
    	this.setLayout(new java.awt.GridLayout(1, 10));
    	
        this.Cards = new ArrayList<>();
        this.chbx = new ArrayList<>();
        this.turn = t;
        
        for(int i = 1; i < 10; i++){
        	JPanel j = new JPanel();

        	j.setBackground(new Color(0, 0, 0));
        	j.setPreferredSize(new Dimension(220, 300));
        	j.setLayout(new FlowLayout());
        	
        	Cards.add(new ImageIcon(GuiConfig.PATH_IMG + "/cartes/0" + i + "v.jpg").getImage());
        	chbx.add(new JCheckBox());

        	Image icon = new ImageIcon(GuiConfig.PATH_IMG + "/cartes/0" + i + "v.jpg").getImage().getScaledInstance(200, 250, Image.SCALE_DEFAULT);
        	
        	JLabel lab = new JLabel();
        	lab.setIcon(new ImageIcon(icon));

        	JCheckBox c = new JCheckBox();
        	c.setBackground(new Color(0, 0, 0));
        	
        	j.add(lab);
        	j.add(c);
        	
        	main.add(j);
        }
    }
    
    public HashSet<AbstractCard> askCards(Player p){
        if(turn.getPlayerState(turn.getPlayerState(true)).equals(p)){
            
        }
        else{
            
        }
        return Console.askCards(turn, );
    }

}