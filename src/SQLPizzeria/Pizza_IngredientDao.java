package SQLPizzeria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;

public class Pizza_IngredientDao {

    public static void add(Pizza pizza, Ingredient ingredient, Connection conn){
        
        try {
            String consulta  = """
                            INSERT INTO pizza (id, name, url)
                            VALUES (UUID_TO_BIN(UUID()),
                            SELECT id FROM pizza WHERE name=?,
                            SELECT id FROM ingredient WHERE name=?);
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, pizza.getName());
            sentencia.setString(2, ingredient.getName());	              
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
        
    }
    
    public static void delete(Pizza pizza,Ingredient ingredient, Connection conn){
        try {
            String consulta  = """
                            DELETE pizza_ingredient 
                            WHERE id_pizza = ? AND id_ingredient = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, ingredient.getId().toString());
            sentencia.setString(2, ingredient.getId().toString());              
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void select(Pizza pizza, Ingredient ingredient, Connection conn){
        try {
            String consulta  = """
                            SELECT id, id_pizza, id_ingredient
                            FROM pizza_ingredient 
                            WHERE id_pizza = ? AND id_ingredient = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, ingredient.getId().toString());
            sentencia.setString(2, ingredient.getId().toString()); 	              
            ResultSet rs = UnitOfWork.executeQuery(sentencia, conn);

            while(rs.next()){
                System.out.println(rs.getObject(1));
                System.out.println(rs.getObject(2));
                System.out.println(rs.getObject(3));
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
}
