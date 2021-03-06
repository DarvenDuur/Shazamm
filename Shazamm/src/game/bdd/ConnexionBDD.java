package game.bdd;

import game.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * System managing connection to a Shazamm database (localhost)
 * @deprecated [WIP]
 */
public class ConnexionBDD {
    //database's name
    private final String dbname;
    
    //database user's username
    private final String username;
    
    //database user's password
    private final String password;
    
    //current conection to the database
    private Connection con = null; 

//***************************** CONSTRUCTOR ************************************ 
    /**
     * @param dbn
     *      name of the database
     * @param u
     *      username
     * @param p 
     *      password
     */
    public ConnexionBDD(String dbn, String u, String p) {
        this.dbname = dbn;
        this.username = u;
        this.password = p;
    }

//***************************** OTHER ******************************************
    /**
     * open the mysql connexion
     */
    private void openConnexion() {
        String connectUrl = "jdbc:mysql://localhost/"+ this.dbname;
        if (con != null) {
            this.closeConnexion();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(connectUrl, username, password);
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Cannot load db driver: com.mysql.jdbc.Driver");
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur inattendue");
            e.printStackTrace();
        }
    }

    /**
     * close the mysql connexion
     */
    private void closeConnexion() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Database connection terminated.");
            } catch (Exception e) { /* ignore close errors */ }
        }
    }
    
    /**
     * add a player in the database
     * @param name
     *      Name of the player to add
     */
    private void addPlayer(String name){
        this.updateTuples("INSERT INTO `Player`(`USERNAME`,"
                    + " `TOTAL_OF_GAME`, "
                    + "`TOTAL_OF_VICTORY`, "
                    + "`TOTAL_OF_EQUALITY`)"
                    + " VALUES "
                    + "("
                    +  "\""+name.toString()+"\""+","
                    + "0,"
                    + "0,"
                    + "0);");
    }
    
    /**
     * get a player from the database
     * @param name
     *      Name of the player to get
     * @return 
     *      ResultSet containing player data
     */
    private ResultSet getPlayer(String name){
        ResultSet tuples = getTuples("SELECT * FROM `Player` WHERE `USERNAME` = '"+name +"';");
        try {
            tuples.next();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tuples;
    }
   
    /**
     * update player scores in the database
     * @param name
     *      name of the player to update
     * @param win 
     *      -1 for loss, 0 for draw, 1 for victory
     */
    public void updatePlayer(String name, short win){
        openConnexion();
        ResultSet rs = getPlayer(name);
        
        int totalOfDraw = 0;
        int totalOfVictory = 0;
        int totalOfGame = 0;
        
        try {
            totalOfGame = rs.getInt(2);
            totalOfVictory = rs.getInt(3);
            totalOfDraw = rs.getInt(4);
                
        //if player not in the DB yet
        } catch (Exception e) {
            addPlayer(name);
            rs = getPlayer(name);
            System.out.println("Create " + name);
        }
        
        totalOfGame++;
        
        switch (win){
            case 1:
                totalOfVictory++;
                break;
                
            case 0:
                totalOfDraw++;
        }
        
        updateTuples("UPDATE `Player`"
                + " SET `TOTAL_OF_GAME` = " + totalOfGame
                + ", `TOTAL_OF_VICTORY` = " + totalOfVictory
                + ", `TOTAL_OF_EQUALITY` = " + totalOfDraw
                + " WHERE `USERNAME`= \"" + name.toString() + "\";");
        
        closeConnexion();
    }
    
    /**
     * Try to execute an update query to database
     * @param query 
     *      query to execute
     * @return  
     *      true if the querry returns a tuple without error
     */
    private boolean updateTuples(String query){
        try{
            if (con == null){
                openConnexion();
            }
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            
            return true;
        }
        catch(Exception e){
            System.out.println("\"" + query + "\" impossible : " + e);
            return false;
        }
        
    }
    
    /**
     * Try to execute a get query to database
     * @param query
     *      query to execute
     * @return 
     *      ResultSet returned by the query
     */
    private ResultSet getTuples(String query){
        try{
            Statement statement = con.createStatement();
            return statement.executeQuery(query);
        } catch(Exception e){
            System.out.println(e + "\n" + query);
            System.out.println("4786532");
            return null;
        }
    }
}
