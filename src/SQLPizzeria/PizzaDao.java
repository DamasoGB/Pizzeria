package SQLPizzeria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objectsPizzeria.Pizza;

public class PizzaDao {

    public static void add(Pizza pizza, Connection conn){
        
        try {
            String consulta  = """
                            INSERT INTO pizza (id, name, url) 
                            VALUES (UUID_TO_BIN(UUID()), ?, ?);
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, pizza.getName());
            sentencia.setString(2, pizza.getUrl());	              
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
        
    }
    public static void update(Pizza pizza, String newUrl, Connection conn){
        try {
            String consulta  = """
                            UPDATE pizza 
                            SET url = ? 
                            WHERE name = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, newUrl);
            sentencia.setString(2, pizza.getName());	              
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void delete(Pizza pizza, Connection conn){
        try {
            String consulta  = """
                            DELETE pizza 
                            WHERE name = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, pizza.getName());	              
            UnitOfWork.executeNonQuery(sentencia, conn);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    public static void select(Pizza pizza, Connection conn){
        try {
            String consulta  = """
                            SELECT id, name, url
                            FROM pizza
                            WHERE name = ?;
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, pizza.getName());	              
            ResultSet rs = UnitOfWork.executeQuery(sentencia, conn);

            while(rs.next()){
                System.out.println(rs.getObject(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
}
