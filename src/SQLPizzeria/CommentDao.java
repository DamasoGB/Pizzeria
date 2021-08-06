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

    public static void add(Comment comment){
        
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
    public static void update(Comment comment, String newText, int newScore){
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            UPDATE comment 
                            SET texto = ?, score = ?
                            WHERE user = ? AND fecha = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, newText);
            sentencia.setInt(2, newScore);
            sentencia.setString(3, comment.getUser().getNombre());
            sentencia.setDate(4, (Date) comment.getFecha());              
            UnitOfWork.executeNonQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void delete(Comment comment){
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            DELETE comment 
                            WHERE user = ? AND fecha = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, comment.getUser().getNombre());
            sentencia.setDate(2, (Date) comment.getFecha());  	              
            UnitOfWork.executeNonQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void select(Comment comment){
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            SELECT id, name, lastname, email
                            FROM user 
                            WHERE user = ? AND fecha = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, comment.getUser().getNombre());
            sentencia.setDate(2, (Date) comment.getFecha());	              
            UnitOfWork.executeQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
}
