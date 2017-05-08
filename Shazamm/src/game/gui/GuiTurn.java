package game.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiTurn extends JPanel{

    private JPanel main;
    private JLabel spell, bet;
    private Color color;
    private FlowLayout layout = new FlowLayout();


	public GuiTurn(int playerBet, ArrayList<String> cards, boolean player){
        initComponents();
        initContents(playerBet, cards, player);
	}
	
    private void initContents(int playerBet, ArrayList<String> cards, boolean player){
    	
    	if(player){
            this.color = new Color(175, 65, 75);
    	}
    	else{
            this.color = new Color(50, 189, 189);
    	}
    	
        JPanel one = new JPanel();
        one.setBackground(new java.awt.Color(33, 41, 48));
        one.setSize(new java.awt.Dimension(400, 100));
        
        JPanel two = new JPanel();
        two.setBackground(new java.awt.Color(33, 41, 48));
        two.setPreferredSize(new java.awt.Dimension(400, 100));
        two.setMaximumSize(new java.awt.Dimension(400, 500));
        
        this.bet = new JLabel("Bet: " + playerBet);
        this.bet.setFont(new Font("Caladea", Font.BOLD, 16));
        this.bet.setForeground(color);
    	
        this.spell = new JLabel("Spells used :\n");
        this.spell.setFont(new Font("Caladea", Font.BOLD, 16));
        this.spell.setForeground(color);

        
        for(String s : cards){
            spell.setText(spell.getText() + "\t -" + s + "\n");
        }
        
        one.add(bet);
        two.add(spell);
        
    	this.add(one);
    	this.add(two);
    }
    
    
    private void initComponents() {

    	main = new JPanel();
    	main.setLayout(layout); 
        main.setBackground(new java.awt.Color(33, 41, 48));
        
    	this.setBackground(new java.awt.Color(255, 255, 255));
    }
	

}
