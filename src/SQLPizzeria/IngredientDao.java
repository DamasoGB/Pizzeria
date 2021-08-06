package SQLPizzeria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import objectsPizzeria.Ingredient;

public class IngredientDao {
    static final String DB_URL = System.getenv("JAVA_SERV");
    static final String USER = System.getenv("JAVA_USER");
    static final String PASS = System.getenv("JAVA_PASSWD");

    public static void add(Ingredient ingredient){
        
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            INSERT INTO ingredient (id, name, price) 
                            VALUES (UUID_TO_BIN(UUID()), ?, ?);
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, ingredient.getName());
            sentencia.setDouble(2, ingredient.getPrice());	              
            UnitOfWork.executeNonQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
        
    }
    public static void update(Ingredient ingredient, Double newPrice){
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            UPDATE ingredient 
                            SET price = ? 
                            WHERE name = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setDouble(1, newPrice);
            sentencia.setString(2, ingredient.getName());	              
            UnitOfWork.executeNonQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void delete(Ingredient ingredient){
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            DELETE ingredient 
                            WHERE name = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, ingredient.getName());	              
            UnitOfWork.executeNonQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void select(Ingredient ingredient){
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            SELECT id, name, price
                            FROM ingredient 
                            WHERE name = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, ingredient.getName());	              
            UnitOfWork.executeQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
}
