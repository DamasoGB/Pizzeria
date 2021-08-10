package SQLPizzeria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    static final String DB_URL = System.getenv("JAVA_SERV");
    static final String USER = System.getenv("JAVA_USER");
    static final String PASS = System.getenv("JAVA_PASSWD");

   public static Connection ConnectDB() throws SQLException{
      Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);
      return conn;
   }

   public static void CloseConnDB(Connection conn){
      try {
         conn.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
