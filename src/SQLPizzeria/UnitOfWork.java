package SQLPizzeria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UnitOfWork {
    

   public static void createDatabase(){
        Create.createDB();
        Create.createTables();
    }
    public static void executeNonQuery(PreparedStatement sentencia, Connection conn){
        try(Statement stmt = conn.createStatement();) {		              
         sentencia.executeUpdate();	  
      } catch (SQLException e) {
         e.printStackTrace();
      } 
    }
    public static ResultSet executeQuery(PreparedStatement sql, Connection conn) throws SQLException{
      ResultSet rs = sql.executeQuery();
        
      return rs;
    }
}
