package game.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUITurn extends JPanel{

	private JPanel main;
	private JLabel spell, bet;
	private Color color;
    private FlowLayout layout = new FlowLayout();


	public GUITurn(int playerBet, ArrayList<String> cards, boolean player){
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
    	
        this.bet = new JLabel("Bet: " + playerBet);
        this.bet.setFont(new Font("Caladea", Font.BOLD, 16));
        this.bet.setForeground(new Color(174, 66, 74));
    	
        this.spell = new JLabel("Spells used :\n");
        this.spell.setFont(new Font("Caladea", Font.BOLD, 16));
        this.spell.setForeground(new Color(174, 66, 74));

        main.add(bet);
        main.add(spell);
        
        for(String s : cards){
        	main.add(new JLabel("\t -" + s + "\n"));
        }
        
    	this.add(main);
    }
    
    
    private void initComponents() {

    	main = new JPanel();
    	main.setLayout(layout); 

        main.setBackground(new java.awt.Color(33, 41, 48));
        
        main.setPreferredSize(new java.awt.Dimension(400, 100));

    	this.setBackground(new java.awt.Color(255, 255, 255));
    }
	

}
