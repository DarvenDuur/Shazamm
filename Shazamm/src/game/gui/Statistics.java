package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Reiji
 */
public class Statistics extends JPanel {

	private JPanel main;
	private JLabel title;

	
    /**
     * Creates new form Statistics
     */
    public Statistics() {
        initComponents();
        initContents();
    }


    protected void initContents(){
    	
    	JPanel p = new JPanel();
    	p.setPreferredSize(new Dimension(1000, 100));
    	p.setBackground(new Color(33, 41, 48));
    	
        this.title = new JLabel("Preceding round :");
        this.title.setFont(new Font("Caladea", Font.BOLD, 22));
        this.title.setForeground(new Color(174, 66, 74));
        this.setPreferredSize(new Dimension(990, 400));
    	
    	p.add(title);

    	this.add(p);
    	update();
    }
    
    
    private void initComponents() {

    	this.main = new JPanel();

        main.setBackground(new Color(33, 41, 48));
        main.setMaximumSize(new Dimension(1000, 1200));

        main.setPreferredSize(new Dimension(1000, 400));

    	this.setBackground(new Color(33, 41, 48));
    }
    
    
    public void update(){
    	ArrayList<String> a = new ArrayList<>();
    	a.add("Test");
    	a.add("Test");
    	a.add("Test");
    	a.add("Test");
    	a.add("Test");
    	
    	main.add(new GuiTurn(13, a, true));
    	main.add(new GuiTurn(22, a, false));
    	
    	this.add(main);
    }

}