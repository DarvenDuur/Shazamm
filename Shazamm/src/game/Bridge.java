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
public class Bridge {
    private PlayerState player1;
    private PlayerState player2;
    private int size; /*half the total size of the bridge, 0 : only the central portion 
    remains*/
    private int firewallLocation; /*0 for the middle of the bridge, < 0 for the left 
    side of the bridge, > 0 for the left side of the bridge*/

    public Bridge(){
        
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
