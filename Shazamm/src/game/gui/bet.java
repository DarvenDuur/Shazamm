package game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author Reiji
 */
public class bet extends JPanel {

	private JLabel betLabel, end;
	private JTextArea betTextF;
	private JButton betButt;
	private JPanel p;

    
    /**
     * Creates new form Statistics
     */
    public bet() {
        initComponents();
    }


    private void initComponents() {

    	p = new JPanel();
        p.setBackground(new Color(33, 41, 48));
        p.setPreferredSize(new Dimension(200, 200));


        this.betLabel = new JLabel("Enter your bet :");
        this.betLabel.setFont(new Font("Caladea", Font.BOLD, 22));
        this.betLabel.setForeground(new Color(174, 66, 74));
        p.add(betLabel);

        this.betTextF = new JTextArea(1, 8);
        this.betTextF.setFont(new Font("Caladea", Font.TRUETYPE_FONT, 22));
        p.add(betTextF);

        this.end = new JLabel("/50");
        this.end.setFont(new Font("Caladea", Font.BOLD, 22));
        this.end.setForeground(new Color(174, 66, 74));
        p.add(end);

        this.betButt = new JButton("Validate !");
        this.betButt.setPreferredSize(new Dimension(120, 30));
        p.add(betButt);
        
        
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
               
        this.setBackground(new Color(33, 41, 48));
        this.setPreferredSize(new Dimension(300, 350));
        
        this.setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());
        //this.add(p, BorderLayout.CENTER);
    }

}