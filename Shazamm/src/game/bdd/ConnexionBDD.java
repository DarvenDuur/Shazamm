/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bdd;

import game.Config;
import game.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 * 
 */
public class ConnexionBDD {

    private final String dbname; 
    private final String username;
    private final String password;
    private Connection con = null;

    public ConnexionBDD(String dbn, String u, String p) {
        this.dbname = dbn;
        this.username = u;
        this.password = p;
    }

    /*
     * open the mysql connexion
     */
    public void openConnexion() {
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

    /*
     * close the mysql connexion
     */
    public void closeConnexion() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Database connection terminated.");
            } catch (Exception e) { /* ignore close errors */ }
        }
    }
    
    /**
     * init the default config
     * @return 
     */
    public Config initConfig(){
        return this.initConfig("default");
    }
    
    /**
     * init config of the mod
     * @param mod
     * @return 
     */
    public Config initConfig(String mod){
        String query = "SELECT * FROM Config WHERE ID_CONFIG=";
        query = query + "\"" + mod + "\";";
        ResultSet rs = getTuples(query);

        try {
            return new Config(rs.getInt("BRIDGE_SIZE"),
                    rs.getInt("MAX_MANA"),
                    rs.getInt("SHUFFLE_STEPS"),
                    rs.getInt("HAND_REFILL_SIZE"),
                    rs.getInt("END_OF_ROUND_DRAW"),
                    rs.getInt("FIRST_ROUND_DRAW")
            );
        } catch (SQLException ex) {
            return new Config();
        }
    }
    
    /**
     * add a player in the db
     * @param name 
     */
    public void addPlayer(String name){
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
    
    public ResultSet getPlayer(String name){
        return getTuples("SELECT * FROM Player WHERE USERNAME='"+name +"';");
    }
   
    /**
     * TO DO
     * @deprecated 
     * @param name
     * @param win 
     */
    public void updatePlayer(String name,short win){
        ResultSet rs=getPlayer(name);
        if(rs!=null){
            try {
                int totalOfGame=rs.getInt(2);
                
                int totalOfVictory=rs.getInt(3);
                int totalOfEquality=rs.getInt(4);
            }
            catch(Exception e){
                
            }
        }
        
    
    }
    
    
    public void getCard(){
        String query="SELECT * FROM Card;";
        ResultSet rs=getTuples(query);
        try {
            while(rs.next()){
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                
            }
        } catch (SQLException ex) {
            System.out.println("ex");
        }
    }
    
    
    /**
     * add tuples in the db
     * @param query 
     * @return  
     *          true if the method a tuple without error
     */
    private boolean updateTuples(String query){
        try{
            System.out.println(query);
            
            Statement statement =con.createStatement();
            statement.executeUpdate(query);
            
            return true;
        }
        catch(Exception e){
            System.out.println("impossible");
            System.out.println(e);
            return false;
        }
        
    }
    
    /**
     * take a query in parameter and return the resultSet
     * @param query
     * @return 
     */
    private ResultSet getTuples(String query){
        try{
            Statement statement =con.createStatement();
            return statement.executeQuery(query);
        }
        catch(Exception e){
            return null;
        }
    }
}
