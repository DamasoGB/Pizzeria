package SQLPizzeria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import objectsPizzeria.User;

public class UserDao {
    static final String DB_URL = System.getenv("JAVA_SERV");
    static final String USER = System.getenv("JAVA_USER");
    static final String PASS = System.getenv("JAVA_PASSWD");

    public static void add(User user){
        
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
            String consulta  = """
                            INSERT INTO user (id, name, lastName, email, password) 
                            VALUES (UUID_TO_BIN(UUID()), ?, ?, ?, ?);
                                """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, user.getNombre());
            sentencia.setString(2, user.getApellidos());
            sentencia.setString(3, user.getEmail());
            sentencia.setString(4, user.getContraseña());	              
            conn.close();	  
        } catch (SQLException e) {
             e.printStackTrace();
        }
        
    }
    public static void update(User user, String newEmail, String newPassword){
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
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
            UnitOfWork.executeNonQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void delete(User user){
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            DELETE user 
                            WHERE name = ? AND lastname = ? AND email = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, user.getNombre());
            sentencia.setString(2, user.getApellidos());
            sentencia.setString(3, user.getEmail());	              
            UnitOfWork.executeNonQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void select(User user){
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            SELECT id, name, lastname, email
                            FROM user 
                            WHERE name = ? AND lastname = ? AND email = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, user.getNombre());
            sentencia.setString(2, user.getApellidos());
            sentencia.setString(3, user.getEmail());	              
            UnitOfWork.executeQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
}
