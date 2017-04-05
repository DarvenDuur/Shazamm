package game;

/**
 * Bridge, with gestion of size, size reduction, and firewall movement.
 * Cards modifying firewall movement modify Bridge.
 */
public class Bridge implements Cloneable {

    //current player's state
    private PlayerState playerState1, playerState2;

    // half the total size of the bridge, 0 : only the central portion remains
    private final int SIZE;

    /* 0 for the middle of the bridge, < 0 for the left side of the bridge,
     * > 0 for the right side of the bridge */
    private int firewallLocation;

    //if true, invert firewall movement
    private boolean invertWinLose;

    //if true, block firewall movement in player's direction
    private boolean rezilliancePlayer1, rezilliancePlayer2;

    //if true, double firewall movement
    private boolean blaze;

//**************************** CONSTRUCTOR *************************************
    /**
     * Call constructor with default vales
     * @see 
     *      Bridge(PlayerState, PlayerState, int, int)
     * @param playerState1 
     *      state of the player 1
     * @param playerState2 
     *      state of the player 2
     */
    public Bridge(PlayerState playerState1, PlayerState playerState2) {
        this(playerState1, playerState2, Config.BRIDGE_MAX_SIZE, 0);
    }

    /**
     * @param playerState1 
     *      state of the player 1
     * @param playerState2 
     *      state of the player 2
     * @param size 
     *      half the total size of the brige - 1 
     *      (excluding the central tile, necessarly existing)
     * @param firewallLocation
     *      position of the firewall
     */
    public Bridge(PlayerState playerState1, PlayerState playerState2,
            int size, int firewallLocation) {
        //initialize fields with parameters
        this.playerState1 = playerState1;
        this.playerState2 = playerState2;
        this.SIZE = size;
        this.firewallLocation = firewallLocation;

        //initialize boolean used when applying
        this.invertWinLose = false;
        this.blaze = false;
    }

//**************************** CLONE *******************************************
    /**
     * @see 
     *      Object.clone()
     * @return 
     *      clone of the bridge, with card effects reset
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Bridge clone = (Bridge) super.clone();

        this.invertWinLose = false;
        this.blaze = false;
        this.rezilliancePlayer1 = false;
        this.rezilliancePlayer2 = false;

        clone.playerState1 = (PlayerState) clone.playerState1.clone();
        clone.playerState2 = (PlayerState) clone.playerState2.clone();

        return clone;
    }

//**************************** SETTER ******************************************
    /**
     * Move the firewall by specified amount (subject to win/lose inversion)
     * @param amount 
     *      Amount of slots to move the firewall by.
     *      Negative for the left side, positive for the right side.
     */
    public void moveFirewallLocation(int amount) {
        //invert amount if victory and loss are inverted
        amount *= this.invertWinLose ? -1 : 1;

        //block firewall if a player with resilliance is menaced
        if ((this.rezilliancePlayer1 && amount < 0)
                || (this.rezilliancePlayer2 && amount > 0)) {
            amount = 0;
        }

        amount *= this.blaze ? 2 : 1;

        //move firewall by amount
        this.setFirewallLocation(this.firewallLocation + amount);
    }

    /**
     * Set the position of the firewall to a specific value
     * @param location 
     *      New firewall location. 
     *      Negative for the left side, positive for the right side.
     */
    public void setFirewallLocation(int location) {
        this.firewallLocation = location;

        // if firewall is too far on the left, move it back to aceptable limit
        if (this.firewallLocation < -this.SIZE) {
            this.firewallLocation = -this.SIZE;

            // if firewall is too far on the right, move it back to aceptable limit
        } else if (this.firewallLocation > this.SIZE) {
            this.firewallLocation = this.SIZE;
        }
    }

    /**
     * Set invertWinLose to true. (invert movement of firewall)
     */
    public void setInvertWinLose() {
        this.invertWinLose = true;
    }

    /**
     * @param rezilliancePlayer1
     *      new rezilliancePlayer1 value
     */
    public void setRezilliancePlayer1(boolean rezilliancePlayer1) {
        this.rezilliancePlayer1 = rezilliancePlayer1;
    }

    /**
     * @param rezilliancePlayer2 
     *      new rezilliancePlayer2 value
     */
    public void setRezilliancePlayer2(boolean rezilliancePlayer2) {
        this.rezilliancePlayer2 = rezilliancePlayer2;
    }

    /**
     * Set blaze to true. (Enable blaze effect)
     */
    public void setBlaze() {
        this.blaze = true;
    }

//**************************** GETTER ******************************************
    /**
     * Check if a player has fallen out of the bridge
     * @return 
     *      0 for draw, -1 for player1, 1 for player2.
     *      -2 for no player out of the bridge
     */
    public short hasOutOfBridge() {
        //both out
        if ((this.playerState1.getPosition() < -this.getSize())
                && (this.playerState2.getPosition() > this.getSize())) {
            return 0;

            //player 1 out
        } else if ((this.playerState1.getPosition() < -this.getSize())) {
            return -1;

            //player 2 out
        } else if ((this.playerState2.getPosition() > this.getSize())) {
            return 1;

            //no player out
        } else {
            return -2;
        }
    }

    /**
     * @return 
     *      the location of the firewall
     */
    public int getFirewallLocation() {
        return this.firewallLocation;
    }

    /**
     * @return 
     *      the player 1
     */
    public Player getPlayer1() {
        return this.playerState1.getPlayer();
    }

    /**
     * @return 
     *      the player 2
     */
    public Player getPlayer2() {
        return this.playerState2.getPlayer();
    }

    /**
     * @return 
     *      the current PlayerState for player 1
     */
    public PlayerState getPlayerState1() {
        return this.playerState1;
    }

    /**
     * @return 
     *      the current PlayerState for player 2
     */
    public PlayerState getPlayerState2() {
        return this.playerState2;
    }

    /**
     * @return 
     *      the size of the bridge (half the total size of the bridge,
     *      0 if only the central portion remains)
     */
    public int getSize() {
        return this.SIZE;
    }

    /**
     * @return 
     *      invertWinLose
     */
    public boolean getInvertWinLose() {
        return this.invertWinLose;
    }

//***************************** OTHER ******************************************
    /**
     * Move players 3 tiles away from firewall
     */
    public void replacePlayers() {
        //set players 3 tiles away
        this.playerState1.setPosition(this.firewallLocation - 3);
        this.playerState2.setPosition(this.firewallLocation + 3);
    }

    /**
     * For console printing
     * @see 
     *      Object.toString()
     * @return 
     *      String representing the bridge
     */
    @Override
    public String toString() {
        String str = "";

        //get player positions
        int positionPlayer1 = this.playerState1.getPosition();
        int positionPlayer2 = this.playerState2.getPosition();

        //add destroyed parts
        for (int i = 0; i < Config.BRIDGE_MAX_SIZE - this.SIZE; i++) {
            str += '.';
        }

        //add all bridge sections
        for (int i = -this.SIZE; i < this.SIZE + 1; i++) {
            //player 1
            if (positionPlayer1 == i) {
                str += '1';

            //player 2
            } else if (positionPlayer2 == i) {
                str += '2';   

            //firewall
            } else if (i == this.firewallLocation) {
                str += "|";

            //empty tiles
            } else {
                str += "-";
            }
        }

        //add destroyed parts
        for (int i = 0; i < Config.BRIDGE_MAX_SIZE - this.SIZE; i++) {
            str += '.';
        }

        return str;
    }
}
