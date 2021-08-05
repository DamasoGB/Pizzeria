package SQLPizzeria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import objectsPizzeria.Comment;

public class CommentDao {
    static final String DB_URL = System.getenv("JAVA_SERV");
    static final String USER = System.getenv("JAVA_USER");
    static final String PASS = System.getenv("JAVA_PASSWD");

    public void add(Comment comment){
        
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
            String consulta  = "INSERT INTO ingredient (id, name, price) VALUES (UUID_TO_BIN(UUID()), '?', ?);";	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, "");
            sentencia.setDouble(2, 0.1);	              
            conn.close();	  
        } catch (SQLException e) {
             e.printStackTrace();
        }
        
    }
    public void update(Comment comment){

    }
    public void delete(Comment comment){

    }
    public void select(Comment comment){

    }
}
