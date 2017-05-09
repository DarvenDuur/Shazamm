/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.Turn;
import game.cards.AbstractCard;
import game.cards.Clone;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


/**
 * @author Reiji
 */
public class Card extends javax.swing.JPanel {

    private ArrayList<AbstractCard> cards;
    private ArrayList<GuiCard> guiCards;
    private Turn turn;
    private final boolean PLAYER_1;
    private FlowLayout layout = new FlowLayout();

    /**
     * Creates new form Card
     */
    public Card(boolean player1) {
        this.PLAYER_1 = player1;
        initComponents();
    }

    protected void initContents(Turn t) {

        this.guiCards = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.turn = t;

        HashSet<AbstractCard> cards = (HashSet<AbstractCard>) this.turn.getPlayerState(PLAYER_1).
                getCardManager().getHand().clone();
      
        this.cards.addAll(cards);
     
        guiCards.clear();
    
        for (int index = 0; index < cards.size(); index++) {
            
            guiCards.add(new GuiCard(this.cards.get(index)));
            guiCards.get(index).setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 0));
            main.add(guiCards.get(index));
        }
        
        main.setLayout(new java.awt.GridLayout(1,
                this.turn.getPlayerState(PLAYER_1).getCardManager().getHand().size()));
        
        update();
    }

    public void update() {

    }
    
    public HashSet<AbstractCard> askCards(){
        HashSet<AbstractCard> select = new HashSet<>();

        for (GuiCard guiCard : guiCards) {
            if (guiCard.isActive()) {
                select.add((AbstractCard) guiCard.getCard());
            }
        }
        
        return select;
    }
    
    public static Clone getClone(ArrayList<AbstractCard> cards){ 
        Clone clone = null;
        Iterator it = cards.iterator();
        while(clone == null && it.hasNext()){
            AbstractCard abCard=(AbstractCard)it.next();
            
            if(abCard instanceof Clone){
                clone = (Clone) abCard;
            }
        }
        
        return clone;
    }

    private ArrayList<AbstractCard> getEnnemyPlayedCards() {
        //get last discarded cards of ennemy player
        ArrayList<AbstractCard> cards = new ArrayList<>();
        cards.addAll(this.turn.getBridge().getPlayerState(!this.PLAYER_1).
                getCardManager().getLastDiscard());
                
        return cards;
    }

    protected ArrayList<AbstractCard> getSelfPlayedCards() {
        //get last discarded cards of ennemy player
        ArrayList<AbstractCard> cards = new ArrayList<>();
        cards.addAll(this.turn.getBridge().getPlayerState(this.PLAYER_1).
                getCardManager().getLastDiscard());
                
        //remove clone
        cards.remove(getClone(cards));
        
        return cards;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        main = new javax.swing.JPanel();

        setBackground(new java.awt.Color(0, 255, 204));
        setMaximumSize(new java.awt.Dimension(3080, 350));
        setMinimumSize(new java.awt.Dimension(730, 350));
        setPreferredSize(new java.awt.Dimension(730, 350));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(3080, 350));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(730, 350));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(730, 350));

        main.setBackground(new java.awt.Color(0, 0, 0));
        main.setMaximumSize(new java.awt.Dimension(3080, 350));
        main.setMinimumSize(new java.awt.Dimension(730, 350));
        main.setPreferredSize(new java.awt.Dimension(730, 350));

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(main);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main;
    // End of variables declaration//GEN-END:variables
}
