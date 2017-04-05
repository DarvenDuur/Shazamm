package game.bdd;

import game.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    /**
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
     * Get default config from database
     * @return 
     *      Config object
     */
    public Config initConfig(){
        return this.initConfig("default");
    }
    
    /**
     * Get config from database
     * @param mod
     *      config type
     * @return 
     *      Config object
     * @deprecated 
     */
    public Config initConfig(String mod){
        String query = "SELECT * FROM Config WHERE ID_CONFIG=";
        query = query + "\"" + mod + "\";";
        ResultSet rs = getTuples(query);

        //Extraction of values from database to Config object
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
     * add a player in the database
     * @param name
     *      Name of the player to add
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
    
    /**
     * get a player from the database
     * @param name
     *      Name of the player to get
     * @return 
     *      ResultSet containing player data
     */
    public ResultSet getPlayer(String name){
        return getTuples("SELECT * FROM Player WHERE USERNAME='"+name +"';");
    }
   
    /**
     * update player scores in the database
     * @deprecated [TO DO]
     * @param name
     *      name of the player to update
     * @param win 
     *      ???
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
    
    /**
     * Get all card informations from database.
     *      Curently only printing them.
     * @deprecated [WIP]
     */
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
     * Try to execute an update query to database
     * @param query 
     *      query to execute
     * @return  
     *      true if the querry returns a tuple without error
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
     * Try to execute a get query to database
     * @param query
     *      query to execute
     * @return 
     *      ResultSet returned by the query
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
