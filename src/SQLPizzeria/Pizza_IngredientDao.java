package SQLPizzeria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;

public class Pizza_IngredientDao {
    static final String DB_URL = System.getenv("JAVA_SERV");
    static final String USER = System.getenv("JAVA_USER");
    static final String PASS = System.getenv("JAVA_PASSWD");

    public static void add(Pizza pizza, Ingredient ingredient){
        
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            INSERT INTO pizza (id, name, url)
                            VALUES (UUID_TO_BIN(UUID()),
                            SELECT id FROM pizza WHERE name=?,
                            SELECT id FROM ingredient WHERE name=?);
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, pizza.getName());
            sentencia.setString(2, ingredient.getName());	              
            UnitOfWork.executeNonQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
        
    }
    
    public static void delete(Pizza pizza,Ingredient ingredient){
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            DELETE pizza_ingredient 
                            WHERE id_pizza = ? AND id_ingredient = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, ingredient.getId().toString());
            sentencia.setString(2, ingredient.getId().toString());              
            UnitOfWork.executeNonQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void select(Pizza pizza, Ingredient ingredient){
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            SELECT id, id_pizza, id_ingredient
                            FROM pizza_ingredient 
                            WHERE id_pizza = ? AND id_ingredient = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, ingredient.getId().toString());
            sentencia.setString(2, ingredient.getId().toString()); 	              
            UnitOfWork.executeQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
}
