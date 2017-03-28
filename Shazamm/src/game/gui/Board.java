package game.gui;


import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Adrien
 */
public class Board extends javax.swing.JPanel {

    // Variables declaration
    private Image title;
    private ArrayList<Image> Bridge;
    // End of variables declaration     

    /**
     * Creates new form Board
     */
    public Board() {
        initComponents();
        initSecond();
        
    }

    private void initSecond() {
        String path = "http://www.boiteajeux.net/jeux/shz/img/logo_shazamm.gif";
        this.title = new ImageIcon(path).getImage();
        this.Bridge = new ArrayList<>();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(title, 100, 200, 400, 100, null);
        
        this.repaint();
    }

    // Generate Code
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 255));
        setSize(new java.awt.Dimension(550, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel1)
                .addContainerGap(260, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addContainerGap(231, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}