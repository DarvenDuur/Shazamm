package game.gui;

import game.Turn;
import game.cards.AbstractCard;
import game.cards.Clone;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class Card extends javax.swing.JPanel {

    private ArrayList<AbstractCard> cards;
    private ArrayList<GuiCard> guiCards;
    private JPanel main;
    private Turn turn;
    private final boolean PLAYER_1;


    /**
     * Creates new panel of test
     */
    public Card(boolean player1) {
        this.PLAYER_1 = player1;
        this.guiCards = new ArrayList<>();
        
        initComponents();
        this.add(new JScrollPane(main));
    }

    protected void initComponents() {
    	main = new JPanel();
    	main.setBackground(Color.BLACK);
        main.setMaximumSize(new Dimension(2700, 350));

        this.setPreferredSize(new Dimension(700, 350));   
    }
    
    protected void initContents(Turn t) {

        this.cards = new ArrayList<>();
        this.turn = t;
        
        HashSet<AbstractCard> cards = this.turn.getPlayerState(PLAYER_1).
                getCardManager().getHand();
        
        this.cards.addAll(cards);
        guiCards.clear();

        for(int index = 0; index < cards.size(); index ++){
            guiCards.add(new GuiCard(this.cards.get(index)));
            main.add(guiCards.get(index));
        }
        update();
    }
    
    public HashSet<AbstractCard> askCards(){
        HashSet<AbstractCard> select = new HashSet<>();
        
        

        return select;
    }

    public void addCards(){//on click
        CardPopup c = new CardPopup(this, this.getEnnemyPlayedCards(), 
                getClone(this.cards));
        new JOptionPane(c);
        c.askClone();
    }

    public static Clone getClone(ArrayList<AbstractCard> cards){ 
        Clone clone = null;
        Iterator it = cards.iterator();
        while(clone == null && it.hasNext()){
            AbstractCard abCard=(AbstractCard)it.next();
            
            if(abCard instanceof Clone){
                clone =(Clone) abCard;
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
    
    public void update(){
        for(GuiCard card : guiCards){
            card.update();
        }
    }
    
    public void update(Turn t){
        this.cards = new ArrayList<>();
        this.turn = t;
        
        HashSet<AbstractCard> cards = this.turn.getPlayerState(PLAYER_1).
                getCardManager().getHand();
        
        this.cards.addAll(cards);
        guiCards.clear();

        for(int index = 0; index < cards.size(); index ++){
            guiCards.add(new GuiCard(this.cards.get(index)));
            main.add(guiCards.get(index));
        }
        update();
    }

}