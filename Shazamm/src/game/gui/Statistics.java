package game.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 *
 * @author Reiji
 */
public class Statistics extends javax.swing.JPanel {

	private JLabel title;

    /**
     * Creates new form Statistics
     */
    public Statistics() {
        initComponents();
    }


    private void initComponents() {

        this.setBackground(new java.awt.Color(33, 41, 48));
        this.setPreferredSize(new java.awt.Dimension(300, 400));
        
        this.title = new JLabel("Preceding round :");
        this.title.setFont(new Font("Caladea", Font.BOLD, 22));
        this.title.setForeground(new Color(174, 66, 74));
        
        this.add(title);
        this.add(new GUITurn());
        this.add(new GUITurn());
        
    }

}
