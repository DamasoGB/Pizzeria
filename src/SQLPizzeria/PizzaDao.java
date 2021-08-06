package SQLPizzeria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import objectsPizzeria.Pizza;

public class PizzaDao {
    static final String DB_URL = System.getenv("JAVA_SERV");
    static final String USER = System.getenv("JAVA_USER");
    static final String PASS = System.getenv("JAVA_PASSWD");

    public void add(Pizza pizza){
        
        try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);) {
            String consulta  = """
                            INSERT INTO pizza (id, name, url) 
                            VALUES (UUID_TO_BIN(UUID()), ?, ?);
                            """;	
            PreparedStatement sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, pizza.getName());
            sentencia.setString(2, pizza.getUrl());	              
            UnitOfWork.executeNonQuery(sentencia);
            conn.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
        
    }
    public void update(Pizza pizza){

    }
    public void delete(Pizza pizza){

    }
    public void select(Pizza pizza){

    }
}
