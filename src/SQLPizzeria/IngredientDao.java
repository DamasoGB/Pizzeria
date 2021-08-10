package SQLPizzeria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objectsPizzeria.Ingredient;

public class IngredientDao {

    public static void add(Ingredient ingredient, Connection conn){
        
        try {
            String consulta  = """
                            INSERT INTO ingredient (id, name, price) 
                            VALUES (UUID_TO_BIN(UUID()), ?, ?);
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, ingredient.getName());
            sentencia.setDouble(2, ingredient.getPrice());	              
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
        
    }
    public static void update(Ingredient ingredient, Double newPrice, Connection conn){
        try {
            String consulta  = """
                            UPDATE ingredient 
                            SET price = ? 
                            WHERE name = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setDouble(1, newPrice);
            sentencia.setString(2, ingredient.getName());	              
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void delete(Ingredient ingredient, Connection conn){
        try {
            String consulta  = """
                            DELETE ingredient 
                            WHERE name = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, ingredient.getName());	              
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void select(Ingredient ingredient, Connection conn){
        try {
            String consulta  = """
                            SELECT id, name, price
                            FROM ingredient 
                            WHERE name = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, ingredient.getName());	              
            ResultSet rs = UnitOfWork.executeQuery(sentencia, conn);

            while(rs.next()){
                System.out.println(rs.getObject(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getDouble(3));
            }
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
}
