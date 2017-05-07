package game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 *
 * @author Reiji
 */
public class Bet extends JPanel {

	private JLabel betLabel, end;
	private JTextArea betTextF;
	private JButton betButt;
	private JPanel main;
	private int bet = -1;
	
    
    /**
     * Creates new form Statistics
     */
    public Bet() {
        initComponents();
        initContents();
    }


    public int getBet(){
        return bet;
    }
    
    protected void initContents(){

        this.betLabel = new JLabel("Enter your bet :");
        this.betLabel.setFont(new Font("Caladea", Font.BOLD, 22));
        this.betLabel.setForeground(new Color(174, 66, 74));
        main.add(betLabel);

        this.betTextF = new JTextArea(1, 8);
        this.betTextF.setFont(new Font("Caladea", Font.TRUETYPE_FONT, 22));
        main.add(betTextF);

        this.end = new JLabel("/50");
        this.end.setFont(new Font("Caladea", Font.BOLD, 22));
        this.end.setForeground(new Color(174, 66, 74));
        main.add(end);

        this.betButt = new JButton("Validate !");
        this.betButt.setPreferredSize(new Dimension(120, 30));
        this.betButt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    try{
                            bet = Integer.parseInt(betTextF.getText());
                    }
                    catch(java.lang.NumberFormatException expt){}
            }
        });
        main.add(betButt);
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(betButt)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(betTextF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(betLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(end)))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(betLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(betTextF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(end))
                .addGap(18, 18, 18)
                .addComponent(betButt)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        
    	this.add(main);
    }
    

    private void initComponents() {

    	main = new JPanel();
    	
    	main.setBackground(new Color(33, 41, 48));
    	main.setPreferredSize(new Dimension(200, 200));

        this.setBackground(new Color(33, 41, 48));
        this.setPreferredSize(new Dimension(300, 350));
    }

}