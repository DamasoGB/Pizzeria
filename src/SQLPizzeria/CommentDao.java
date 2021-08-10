package SQLPizzeria;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objectsPizzeria.Comment;

public class CommentDao {

    public static void add(Comment comment, Connection conn){
        
        try{
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
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
        
    }
    public static void update(Comment comment, String newText, int newScore, Connection conn){
        try{
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
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void delete(Comment comment, Connection conn){
        try{
            String consulta  = """
                            DELETE comment 
                            WHERE user = ? AND fecha = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, comment.getUser().getNombre());
            sentencia.setDate(2, (Date) comment.getFecha());  	              
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void select(Comment comment, Connection conn){
        try{
            String consulta  = """
                            SELECT id, text, score, fecha, user
                            FROM comment 
                            WHERE user = ? AND fecha = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, comment.getUser().getNombre());
            sentencia.setDate(2, (Date) comment.getFecha());	              
            ResultSet rs = UnitOfWork.executeQuery(sentencia, conn);

            while(rs.next()){
                System.out.println(rs.getObject(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getInt(3));
                System.out.println(rs.getDate(4));
                System.out.println(rs.getObject(5));
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
}
