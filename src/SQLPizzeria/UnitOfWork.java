package SQLPizzeria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UnitOfWork {
    static final String DB_URL = System.getenv("JAVA_SERV");
    static final String USER = System.getenv("JAVA_USER");
    static final String PASS = System.getenv("JAVA_PASSWD");
    public static void createDatabase(){
        Create.createDB();
        Create.createTables();
    }
    public static void executeNonQuery(PreparedStatement sentencia){
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
      ) {		              
         sentencia.executeUpdate();
         conn.close();	  
      } catch (SQLException e) {
         e.printStackTrace();
      } 
    }//Inserts, Updates, Deletes
    public static void executeQuery(){}//Selects
}
