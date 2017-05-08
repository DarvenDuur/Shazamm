package game.gui;

import game.Player;
import game.Turn;
import game.cards.AbstractCard;
import game.cards.Clone;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

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
    private final boolean PLAYER_1;
	
	
    /**
     * Creates new panel of test
     */
    public Card(boolean player1) {
        initComponents();
        this.PLAYER_1 = player1;
        //initContents(turn);
        
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
        
        HashSet<AbstractCard> cards = this.turn.getPlayerState(PLAYER_1).
                getCardManager().getHand();
        
        for(AbstractCard card : cards){
        	JPanel j = new JPanel();

        	j.setBackground(new Color(0, 0, 0));
        	j.setPreferredSize(new Dimension(220, 300));
        	j.setLayout(new FlowLayout());
        	
                Image icon = new ImageIcon(GuiConfig.PATH_IMG + "/cartes/" + 
                        card.getImageName()).getImage().getScaledInstance(200, 
                                250, Image.SCALE_DEFAULT);
        	
                Cards.add(icon);
        	chbx.add(new JCheckBox());
        	
        	JLabel lab = new JLabel();
        	lab.setIcon(new ImageIcon(icon));

        	JCheckBox c = new JCheckBox();
        	c.setBackground(new Color(0, 0, 0));
        	
        	j.add(lab);
        	j.add(c);
        	
        	main.add(j);
        }
    }
    
    public HashSet<AbstractCard> askCards(){
        HashSet<AbstractCard> cards = this.turn.getPlayerState(PLAYER_1).
                getCardManager().getHand(),
                output = new HashSet<>();
        
        HashSet<Integer> integers = ;
        
        for (AbstractCard card : cards) {
            if (integers.contains(card.getId())) {
                output.add(card);

                //if card is a Clone card, get cloned card
                if (card instanceof Clone) {
                    ((Clone) card).setClone(askClone(turn, player1));
                }
            }
        }
        return output;
    }

}