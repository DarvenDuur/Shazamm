/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package game;

/**
 *
 * @author darven
 */
public class Bridge implements Cloneable {
    private PlayerState playerState1;
    private PlayerState playerState2;
    // half the total size of the bridge, 0 : only the central portion remains
    private final int SIZE; 
    /* 0 for the middle of the bridge, < 0 for the left side of the bridge,
     * > 0 for the right side of the bridge
     */
    private int firewallLocation;    

//**************************** CONSTRUCTOR *************************************
    
    public Bridge(PlayerState player1, PlayerState player2) {
        this(player1, player2, Config.BRIDGE_MAX_SIZE, 0);
    }    
    
    public Bridge(PlayerState player1, PlayerState player2, int size, int firewallLocation) {
        this.playerState1 = player1;
        this.playerState2 = player2;
        this.SIZE = size;
        this.firewallLocation = firewallLocation;
        
        this.invertWinLose = false;
        this.blaze = false;
    }

//******************************************************************************
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        Bridge clone = (Bridge) super.clone();
        
        this.invertWinLose = false;
        this.blaze = false;

        clone.playerState1 = (PlayerState) clone.playerState1.clone();
        clone.playerState2 = (PlayerState) clone.playerState2.clone();

        return clone;
    }

//**************************** SETTER ******************************************

    /**
     * move the firewall by specified amount
     *      subject to win/lose inversion
     * @param amount
     *      amount of slots to move the firewall by
     *      negative for the left side, positive for the right side
     */
    public void moveFirewallLocation(int amount) {
        //invert amount if victory and loss are inverted
        amount *= this.invertWinLose ? -1 : 1;
        
        //block firewall if a player with resilliance is menaced
        if((this.rezilliancePlayer1 && amount > 0)||
                (this.rezilliancePlayer2 && amount > 0)){
            amount = 0;
        }
        
        amount *= this.blaze ? 2 : 1;
        
        //move firewall by amount
        this.setFirewallLocation(this.firewallLocation + amount);
    }
    
    /**
     * set the position of the firewall
     * @param location
     *      new firewall location
     *      negative for the left side, positive for the right side
     */
    public void setFirewallLocation(int location){
        this.firewallLocation = location;
        
        // if firewall is too far on the left, move it back to aceptable limit
        if (this.firewallLocation < -this.SIZE) {
            this.firewallLocation = -this.SIZE;

            // if firewall is too far on the right, move it back to aceptable limit
        } else if (this.firewallLocation > this.SIZE) {
            this.firewallLocation = this.SIZE;
        }
    }

//**************************** GETTER ******************************************

    /**
     * check if a player has fallen out of the bridge
     * @return
     *      0 for draw, -1 for player1, 1 for player2
     *      -2 for no player out of the bridge
     */
    public short hasOutOfBridge() {
        //both out
        if ((this.playerState1.getPosition() < -this.getSize())
                && (this.playerState2.getPosition() > this.getSize())){
            return 0;
            
        //player 1 out
        }else if ((this.playerState1.getPosition() < -this.getSize())) {
            return -1;
            
        //player 2 out
        }else if ((this.playerState2.getPosition() > this.getSize())) {
            return 1;
            
        //no player out
        }else{
            return -2;
        }
    }
    
    /**
     * @return the firewallLocation
     */
    public int getFirewallLocation() {
        return this.firewallLocation;
    }
    
    /**
     * @return the playerState1
     */
    public Player getPlayer1() {
        return this.playerState1.getPlayer();
    }

    /**
     * @return the playerState2
     */
    public Player getPlayer2() {
        return this.playerState2.getPlayer();
    }

    /**
     * @return the current PlayerState for playerState1
     */
    public PlayerState getPlayerState1() {
        return this.playerState1;
    }

    /**
     * @return the current PlayerState for playerState2
     */
    public PlayerState getPlayerState2() {
        return this.playerState2;
    }

    /**
     * @return the SIZE
     */
    public int getSize() {
        return this.SIZE;
    }
    
//***************************** WHO WIN LOSE ***********************************
    
    private boolean invertWinLose;

    /**
     * set invertWinLose to true
     */
    public void setInvertWinLose() {
        this.invertWinLose = true;
    }
    
    /**
     * @return invertWinLose
     */
    public boolean getInvertWinLose() {
        return this.invertWinLose;
    }
    
//***************************** REZILLIANCE ************************************
    private boolean rezilliancePlayer1;
    private boolean rezilliancePlayer2;

     /**
     * @param rezilliancePlayer1 the rezilliancePlayer1 to set
     */
    public void setRezilliancePlayer1(boolean rezilliancePlayer1) {
        this.rezilliancePlayer1 = rezilliancePlayer1;
    }

    /**
     * @param rezilliancePlayer2 the rezilliancePlayer2 to set
     */
    public void setRezilliancePlayer2(boolean rezilliancePlayer2) {
        this.rezilliancePlayer2 = rezilliancePlayer2;
    }

//***************************** WHO WIN LOSE ***********************************
    
    private boolean blaze;

    /**
     * set invertWinLose to true
     */
    public void setBlaze() {
        this.blaze = true;
    }
    
//***************************** OTHER ******************************************

    /**
     * set players 3 tiles away from firewall
     */
    public void replacePlayers(){
        //set players 3 tiles away
        this.playerState1.setPosition(this.firewallLocation - 3);
        this.playerState2.setPosition(this.firewallLocation + 3);
    }
    
    /**
     * @see Object.toString()
     * @return
     *      String representing the bridge
     */
    @Override
    public String toString(){
        String str="";
        
        //get player positions
        int positionPlayer1=this.playerState1.getPosition();
        int positionPlayer2=this.playerState2.getPosition();
        
        //add destroyed parts
        for (int i=0;i<Config.BRIDGE_MAX_SIZE - this.SIZE;i++) {
            str+='.';
        }
        
        //add all bridge sections
        for (int i=-this.SIZE;i<this.SIZE+1;i++) {
            //player 1
            if (positionPlayer1==i) {
               str+='1';
               //str+=playerState1.getPlayer().getName();
               
            //player 2
            } else if (positionPlayer2==i) {
               str+='2';
               //str+=playerState2.getPlayer().getName();    
            
            //firewall
            } else if(i==this.firewallLocation){
                    str+="|";
                    
            //empty tiles
            } else {
                str+="-";
            }
        }
        
        //add destroyed parts
        for (int i=0;i<Config.BRIDGE_MAX_SIZE - this.SIZE;i++) {
            str+='.';
        }
        
        return str;
    }

   
   
}
