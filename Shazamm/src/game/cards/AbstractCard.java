package game.cards;

import game.PlayerState;
import game.Round;

/**
 * Basic Shazaam card
 * @see CardsEnum for more precise information on the cards
 */
public abstract class AbstractCard implements Comparable<AbstractCard> {

    //card ID, from 1 to 14
    protected final int ID;

    //true if card belong to player 1
    protected final boolean BELONG_PLAYER_1; 
    
    //true if card is used by the posessor's adversary
    private boolean stolen = false; 

//**************************** CONSTRUCTOR *************************************
    /**
     * @see CardsEnum for more precise information on the IDs
     * @param belongPlayer1
     *      if true, will be considered as belonging to player 1, 
     *      otherwise to player 2
     * @param id 
     *      ID of the card
     */
    protected AbstractCard(boolean belongPlayer1, int id) {
        this.BELONG_PLAYER_1 = belongPlayer1;
        this.ID = id;
    }
    
    /**
     * Pseudo constructor, usable to create all cards from AbstractCard
     * @see CardsEnum for more precise information on the IDs
     * @param id
     *      ID of the card to create
     * @param belongPlayer1
     *      if true, created card will belong to player 1
     * @return 
     *      new card corresponding to inputed ID, null if ID invalid
     */
    public static AbstractCard create(int id, boolean belongPlayer1) {
        AbstractCard card = null;

        switch (id) {
            case 1:
                card = new Mutism(belongPlayer1);
                break;

            case 2:
                card = new Clone(belongPlayer1);
                break;

            case 3:
                card = new Theft(belongPlayer1);
                break;

            case 4:
                card = new EndOfRound(belongPlayer1);
                break;

            case 5:
                card = new Middle(belongPlayer1);
                break;

            case 6:
                card = new Recycling(belongPlayer1);
                break;

            case 7:
                card = new AttackBoost(belongPlayer1);
                break;

            case 8:
                card = new DoubleDose(belongPlayer1);
                break;

            case 9:
                card = new WhoWinLose(belongPlayer1);
                break;

            case 10:
                card = new Blaze(belongPlayer1);
                break;

            case 11:
                card = new Resiliance(belongPlayer1);
                break;

            case 12:
                card = new Scrooge(belongPlayer1);
                break;

            case 13:
                card = new StockBoost(belongPlayer1);
                break;

            case 14:
                card = new SuckBet(belongPlayer1);
                break;
        }

        return card;
    }
    
//***************************** COMPARABLE *************************************   
    /**
     * @see Comparable
     * @param card
     *      card to compare
     * @return 
     *      difference between IDs
     */
    @Override
    public int compareTo(AbstractCard card) {
        //ascending order of IDs
        return this.getId() - card.getId();
    }

    /**
     * @see 
     *      Object.equals(Object o)
     * @param o
     *      object to compare
     * @return
     *      true if object is equal
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof AbstractCard) {
            return o.hashCode() == this.hashCode();
        }
        return false;
    }

    /**
     * @see Comparable
     * @return 
     *      hashcode considering ID and player posessing the card
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.ID;
        hash = 59 * hash + (this.BELONG_PLAYER_1 ? 1 : 0);
        return hash;
    }

//***************************** GETTER *****************************************
    /**
     * @return 
     *      the card ID
     */
    public int getId() {
        return ID;
    }

    /**
     * @return 
     *      the name used to display the card
     */
    public String getName() {
        return CardsEnum.CARDS[ID - 1].getName();
    }

    /**
     * @return 
     *      the image file name, do not contain extension
     */
    public String getImageName() {
        return CardsEnum.CARDS[ID - 1].getImageName();
    }

    /**
     * @return 
     *      the decription of the card (usage and effects)
     */
    public String getDescription() {
        return CardsEnum.CARDS[ID - 1].getDescription();
    }

    /**
     * @return 
     *      true if stolen and belonging to player 2, 
     *      or not stolen and belonging to player 1
     */
    public boolean isUsedPlayer1() {
        return (BELONG_PLAYER_1 && !stolen) || (!BELONG_PLAYER_1 && stolen);
    }

    /**
     * @return 
     *      true if belonging to player 1
     */
    public boolean isBelongPlayer1() {
        return BELONG_PLAYER_1;
    }

    /**
     * @param round
     *      round from wich fetch the state
     * @return
     *      the state corresponding to the user of the card
     */
    protected PlayerState getUserPLayer(Round round) {
        return round.getLastPlayerState(this.isUsedPlayer1());
    }

    /**
     * @param round
     *      round from wich fetch the state
     * @return
     *      the state corresponding to the ennemy of the user of the card
     */
    protected PlayerState getNotUserPlayer(Round round) {
        return round.getLastPlayerState(!this.isUsedPlayer1());
    }

    /**
     * @return 
     *      true if is stolen (used by the player who do not posess it)
     */
    public boolean isStolen() {
        return stolen;
    }

//**************************** SETTER ******************************************
    /**
     * @param stolen 
     *      true if is stolen (used by the player who do not posess it)
     */
    public void setStolen(boolean stolen) {
        this.stolen = stolen;
    }

//**************************** OTHER *******************************************
    /**
     * Specific action, must called from generalApply()
     * @param round 
     *      round to which apply the card
     */
    protected abstract void apply(Round round);

    /**
     * wraps action call, with mutism check
     * @param round 
     *      round to which apply the card
     */
    public void generalApply(Round round) {
        if (!round.getLastTurn().isMute()) {
            //true if this card was stolen
            boolean cardTheft = (round.getLastTurn().isPlayer2Theft()
                    && this.BELONG_PLAYER_1) || (round.getLastTurn().isPlayer1Theft()
                    && !this.BELONG_PLAYER_1);

            //invert posessor
            this.setStolen(cardTheft);

            this.apply(round);

            //revert posessor
            this.setStolen(false);
        }
    }

    public void setClone(AbstractCard secondClonedCard) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toString(){
        return this.getId() + ". " + this.getName();
    }
}
