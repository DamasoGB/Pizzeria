package SQLPizzeria;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import objectsPizzeria.Comment;

public class CommentDao {
    static final String DB_URL = System.getenv("JAVA_SERV");
    static final String USER = System.getenv("JAVA_USER");
    static final String PASS = System.getenv("JAVA_PASSWD");

    public void add(Comment comment){
        
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                        INSERT INTO comment (id, text, score, fecha, user) 
                        VALUES (UUID_TO_BIN(UUID()), ?, ?, ?, 
                        SELECT id FROM user WHERE name=?);
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, comment.getTexto());
            sentencia.setInt(2, comment.getScore());
            sentencia.setDate(3, (Date) comment.getFecha());
            sentencia.setString(4, comment.getUser().getNombre());              
            UnitOfWork.executeNonQuery(sentencia);
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
