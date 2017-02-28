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
    private int size; /*half the total size of the bridge, 0 : only the central portion 
    remains*/
    private int firewallLocation; /*0 for the middle of the bridge, < 0 for the left 
    side of the bridge, > 0 for the left side of the bridge*/

    public Bridge(PlayerState player1, PlayerState player2, int size, int firewallLocation) {
        this.player1 = player1;
        this.player2 = player2;
        this.size = size;
        this.firewallLocation = firewallLocation;
    }
    public Bridge(PlayerState player1, PlayerState player2, int firewallLocation) {
        this(player1, player2, Config.BRIDGE_MAX_SIZE, 0);
    }
    public Bridge(PlayerState player1, PlayerState player2) {
        this(player1, player2, 0);
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        Bridge clone = (Bridge) super.clone();
        clone.player1 = (PlayerState) clone.player1.clone();
        clone.player2 = (PlayerState) clone.player2.clone();
        return clone;
    }
    
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
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the firewallLocation
     */
    public int getFirewallLocation() {
        return this.firewallLocation;
    }

    /**
     * @param firewallLocation the firewallLocation to set
     */
    public void setFirewallLocation(int firewallLocation) {
        this.firewallLocation = firewallLocation;
    }
}
