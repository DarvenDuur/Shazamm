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
    
    private PlayerState player1;
    
    private PlayerState player2;
    
    private final int SIZE; /*half the total size of the bridge, 0 : only the central 
    portion remains*/
    
    private int firewallLocation; /*0 for the middle of the bridge, < 0 for the 
    left side of the bridge, > 0 for the right side of the bridge*/

//**************************** MUTISM ******************************************
    
    private boolean mute;
    
//**************************** CONSTRUCTOR *************************************    
    
    public Bridge(PlayerState player1, PlayerState player2, int size, int firewallLocation) {
        this.player1 = player1;
        this.player2 = player2;
        this.SIZE = size;
        this.firewallLocation = firewallLocation;
        
        this.mute = false;
    }
    
    public Bridge(PlayerState player1, PlayerState player2) {
        this(player1, player2, Config.BRIDGE_MAX_SIZE, 0);
    }

//**************************** GETTER ******************************************
    
    /**
     * @return the player1
     */
    public PlayerState getPlayer1() {
        return this.player1;
    }

    /**
     * @return the player2
     */
    public PlayerState getPlayer2() {
        return this.player2;
    }

    /**
     * @return the SIZE
     */
    public int getSize() {
        return this.SIZE;
    }
   
    /**
     * @return the firewallLocation
     */
    public int getFirewallLocation() {
        return this.firewallLocation;
    }

//**************************** SETTER ******************************************
    
    /**
     * move the firewall by specified amount
     * @param amount
     *      amount of slots to move the firewall by
     *      negative for the left side, positive for the right side
     */
    public void moveFirewallLocation(int amount) {
        this.firewallLocation += amount;
        //if firewall is too far on the left, move it back to aceptable limit
        if (this.firewallLocation < -this.SIZE){
            this.firewallLocation = -this.SIZE;
            
        //if firewall is too far on the right, move it back to aceptable limit
        }else if (this.firewallLocation > this.SIZE){
            this.firewallLocation = this.SIZE;
        }
    }

//******************************************************************************
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        Bridge clone = (Bridge) super.clone();
        clone.player1 = (PlayerState) clone.player1.clone();
        clone.player2 = (PlayerState) clone.player2.clone();
        return clone;
    }

    /**
     * mute to false
     */
    public void setMute() {
        this.mute = true;
    }

    /**
     * @return the mute
     */
    public boolean isMute() {
        return mute;
    }
}
