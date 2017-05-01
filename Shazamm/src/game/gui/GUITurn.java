package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class GUITurn extends JPanel{

	private JLabel title, spell;
	
    /**
     * Creates new form Statistics
     */
    public GUITurn() {
        initComponents();
    }

    private void initComponents() {

    	
        this.setBackground(new java.awt.Color(33, 41, 48));
        this.setBorder(new LineBorder(new Color(255, 255, 255)));
        
        this.setMinimumSize(new Dimension(280, 10));
        this.setMaximumSize(new Dimension(280, 380));
        
        Object[][] donnees = {
                {"Johnathan", 25, 13},
                {"Nicolas", 46, 5},
                {"Damien", 34, 10},
                {"Corinne", 22, 34},
                {"Emilie", 36, 7},
                {"Delphine", 41, 29},
        };

        String[] entetes = {"Turn", "Player 1", "Player 2"};
        JTable table = new JTable(donnees, entetes);

        
        this.spell = new JLabel("Spells used :");
        this.spell.setFont(new Font("Caladea", Font.BOLD, 16));
        this.spell.setForeground(new Color(174, 66, 74));
        
        this.add(table);
        this.add(spell);
    }

}
