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

//**************************** MUTISM ******************************************
    private boolean mute;

    public Bridge(PlayerState player1, PlayerState player2) {
        this(player1, player2, Config.BRIDGE_MAX_SIZE, 0);
    }

//**************************** CONSTRUCTOR *************************************    
    public Bridge(PlayerState player1, PlayerState player2, int size, int firewallLocation) {
        this.playerState1          = player1;
        this.playerState2     = player2;
        this.SIZE             = size;
        this.firewallLocation = firewallLocation;
        this.mute             = false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Bridge clone = (Bridge) super.clone();

        clone.playerState1      = (PlayerState) clone.playerState1.clone();
        clone.playerState2 = (PlayerState) clone.playerState2.clone();

        return clone;
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

        // if firewall is too far on the left, move it back to aceptable limit
        if (this.firewallLocation < -this.SIZE) {
            this.firewallLocation = -this.SIZE;

            // if firewall is too far on the right, move it back to aceptable limit
        } else if (this.firewallLocation > this.SIZE) {
            this.firewallLocation = this.SIZE;
        }
    }

    /**
     * @return the firewallLocation
     */
    public int getFirewallLocation() {
        return this.firewallLocation;
    }

    /**
     * @return the mute
     */
    public boolean isMute() {
        return mute;
    }

    /**
     * mute to false
     */
    public void setMute() {
        this.mute = true;
    }

//******************************************************************************

    /**
     * check if a player has fallen out of the bridge
     * @return
     *      true if at least one of the player is out of the bridge
     */
    public boolean hasOutOfBridge() {
        return ((this.playerState1.getPosition() < -this.getSize())
                || (this.playerState1.getPosition() > this.getSize())
                || (this.playerState2.getPosition() < -this.getSize())
                || (this.playerState2.getPosition() > this.getSize()));
    }

//**************************** GETTER ******************************************

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
}
