package game;

import game.bdd.ConnexionBDD;
import java.util.LinkedList;
import java.util.Random;
import game.gui.Console;
import game.gui.GuiConfig;

/**
 * Main Shazamm game system, manages rounds and global victory
 */
public class Game {
    // all rounds of the game
    protected LinkedList<Round> rounds;
    
    //Player objects containing the data of the two players
    protected Player player1, player2;

//***************************** CONSTRUCTOR ************************************
    /**
     * Initialize the player data by calling inputs,
     *      and initialize the first round.
     * @param activateAI
     *      if true, the second player is controlled by AI
     */
    public Game(boolean activateAI) {
        setTimerLimit();
        
        // init list of rounds
        this.rounds = new LinkedList<>();

        definePlayers(activateAI);
        
        setFirstRound();
    }
    
    protected final void definePlayers(boolean activateAI){
        
        //Player versus AI
        if (activateAI){
            String  namePlayer = "";
            
            setPvEName(namePlayer);
            
            // create 2 players
            this.player1 = new Player(namePlayer);
            this.player2 = new BotPlayer();
            
        //Player versus player
        } else {
            String namePlayer1 = "",
                    namePlayer2 = "";
            setPvPNames(namePlayer1, namePlayer2);
            
            // attribute side
            Random  random = new Random();

            // create 2 players
            if(random.nextBoolean()){
                this.player1 = new Player(namePlayer1);
                this.player2 = new Player(namePlayer2);
            } else {
                this.player1 = new Player(namePlayer2);
                this.player2 = new Player(namePlayer1);
            }
        }
    }
    
    protected void setPvEName(String  namePlayer){
        namePlayer = Console.getInput("Player, what"
                + " is your username?");
        
        // forbiding use of AI name
        while (namePlayer.equals(Config.AI_NAME)) {
            Console.println("please try a new username");
            namePlayer = Console.getInput("Player, what"
                    + " is your username?");
        }
    }
    
    protected void setPvPNames(String namePlayer1, String namePlayer2){
        namePlayer1 = Console.getInput("Player 1, what"
                + " is your username?");
        
        // forbiding use of AI name
        while (namePlayer1.equals(Config.AI_NAME) || 
                "".equals(namePlayer1)) {
            System.out.println("please try a new username");
            namePlayer1 = Console.getInput("Player 1, what is your username?");
        }

        // uniqueness of name
        while (namePlayer1.equals(namePlayer2) ||
                namePlayer2.equals(Config.AI_NAME) || 
                "".equals(namePlayer2)) {
            System.out.println("please try a new username");
            namePlayer2 = Console.getInput("Player 2, what"
                    + " is your username?");
        }
    }
    
    protected void setFirstRound(){
        // create the first round of the game
        Round firstRound = new Round(this.player1, this.player2,
                Config.BRIDGE_MAX_SIZE, 0);

        // add the first round to list of rounds
        this.rounds.add(firstRound);
    }
    
    protected void setTimerLimit(){
        Timer.setTimeLimit(Console.getIntInput(GuiConfig.TIME_LIMIT));
    }
    
//***************************** GETTER *****************************************
    /**
     * @return the rounds
     */
    public LinkedList<Round> getRounds() {
        return rounds;
    }

//***************************** OTHER ******************************************
    /**
     * loop rounds until game end condition is met (a player is in the lava)
     */
    public void play(){
        
        while(!playRound()){
            //play rounds while the ending conditions are not met
        }
        
        //update database
        ConnexionBDD bdd = new ConnexionBDD(Config.BDD_NAME, 
                Config.BDD_USERNAME, Config.BDD_PASSWORD);
        bdd.updatePlayer(player1.getName(), this.winner());
        bdd.updatePlayer(player2.getName(), (short) -this.winner());
    }

    /**
     * makes the players play a round
     * @return
     *      true if the game has ended (if a player is in the lava)
     */
    public boolean playRound() {
        Round round;

        /*create a round from scratch if no round is available (just a safety);
        if the last round added to rounds is not ended, will use it;
        otherwize use the data of the last round to create a new one*/
        if (rounds.isEmpty()){
            round = new Round(this.player1, this.player2,
                Config.BRIDGE_MAX_SIZE, 0);

        } else {
            round = this.rounds.getLast();
            if (round.isEnded()){
                round = new Round(round);
            }
        }
            
        /*when the round is finished, add to list of rounds
        (could be after executing the round)*/
        rounds.addLast(round);

        /*execute the round, and returns true if the raoun fills the game ending
        condition*/
        return round.play();
    }

    /**
     * get the winer of the game
     * @return
     *      0 for draw, -1 for player1, 1 for player2
     *      -2 for no player out of the bridge
     */
    public short winner() {
        return this.getRounds().getLast().getWinner();
    }
}