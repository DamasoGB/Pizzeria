package PizzeriaDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UnitOfWork {


    public static void executeNonQuery(PreparedStatement sentencia){
        try{		              
         sentencia.executeUpdate();	  
      } catch (SQLException e) {
         e.printStackTrace();
      } 
    }
    public static ResultSet executeQuery(PreparedStatement sql) throws SQLException{
      ResultSet rs = sql.executeQuery();
      
      return rs;
    }
}
