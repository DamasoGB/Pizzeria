package SQLPizzeria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objectsPizzeria.User;

public class UserDao {

    public static void add(User user, Connection conn){
        
        try {
            String consulta  = """
                            INSERT INTO user (id, name, lastName, email, password) 
                            VALUES (UUID_TO_BIN(UUID()), ?, ?, ?, ?);
                                """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, user.getNombre());
            sentencia.setString(2, user.getApellidos());
            sentencia.setString(3, user.getEmail());
            sentencia.setString(4, user.getContraseña());	              
            	  
        } catch (SQLException e) {
             e.printStackTrace();
        }
        
    }
    public static void update(User user, String newEmail, String newPassword, Connection conn){
        try {
            String consulta  = """
                            UPDATE ingredient 
                            SET email = ?, password = ? 
                            WHERE name = ? && lastname = ? AND email = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, user.getEmail());
            sentencia.setString(2, user.getContraseña());
            sentencia.setString(3, user.getNombre());
            sentencia.setString(4, user.getApellidos());
            sentencia.setString(5, user.getEmail());	              
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void delete(User user, Connection conn){
        try {
            String consulta  = """
                            DELETE user 
                            WHERE name = ? AND lastname = ? AND email = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, user.getNombre());
            sentencia.setString(2, user.getApellidos());
            sentencia.setString(3, user.getEmail());	              
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void select(User user, Connection conn){
        try {
            String consulta  = """
                            SELECT id, name, lastname, email
                            FROM user 
                            WHERE name = ? AND lastname = ? AND email = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, user.getNombre());
            sentencia.setString(2, user.getApellidos());
            sentencia.setString(3, user.getEmail());	              
            ResultSet rs = UnitOfWork.executeQuery(sentencia, conn);

            while(rs.next()){
                System.out.println(rs.getObject(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
}
