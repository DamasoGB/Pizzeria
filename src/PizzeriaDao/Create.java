package PizzeriaDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create {
   static final String DB_URL = System.getenv("JAVA_SERV");
   static final String USER = System.getenv("JAVA_USER");
   static final String PASS = System.getenv("JAVA_PASSWD");

    public static void createDB() {
        // Open a connection
        
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
           Statement stmt = conn.createStatement();
        ) {		      
           String sql = "CREATE DATABASE IF NOT EXISTS pizzeria";
           stmt.executeUpdate(sql);
           System.out.println("Base de Datos creada.");
           
            conn.close();
        } catch (SQLException e) {
           e.printStackTrace();
        } 
    }

    public static void createTables() {
      
      try(Connection conn = DriverManager.getConnection(DB_URL+"pizzeria", USER, PASS);
         Statement stmt = conn.createStatement();
      ) {		      
        String sql = "CREATE TABLE IF NOT EXISTS ingredient("+
        "id BINARY(16) PRIMARY KEY,"+
        "name TEXT(1000) NOT NULL,"+
        "price DOUBLE PRECISION NOT NULL);";
        stmt.executeUpdate(sql);
        System.out.println("Tabla Ingredientes creada");

        sql = """
            CREATE TABLE IF NOT EXISTS user (
                id BINARY(16) PRIMARY KEY,
                name TEXT(1000) NOT NULL,
                lastName TEXT(1000) NOT NULL,
                email TEXT(1000) NOT NULL,
                password TEXT(1000) NOT NULL);
            """;
        stmt.executeUpdate(sql);
        System.out.println("Tabla User creada");

        sql = """
            CREATE TABLE IF NOT EXISTS pizza (
                id BINARY(16) PRIMARY KEY,
                name TEXT(1000) NOT NULL,
                url TEXT(1000) NOT NULL);
            """;
        stmt.executeUpdate(sql);
        System.out.println("Tabla Pizza creada");

        sql = """
            CREATE TABLE IF NOT EXISTS comment (
                id BINARY(16) PRIMARY KEY,
                text TEXT NOT NULL,
                rating FLOAT NOT NULL,
                date DATE NOT NULL,
                id_user BINARY(16) NOT NULL,
                id_pizza BINARY(16) NOT NULL,
                CONSTRAINT FK_user_comment FOREIGN KEY (id_user) REFERENCES user(id) ON DELETE CASCADE,
                CONSTRAINT FK_pizza_comment FOREIGN KEY (id_pizza) REFERENCES pizza(id) ON DELETE CASCADE);
            """;
        stmt.executeUpdate(sql);
        System.out.println("Tabla Coment creada");

        sql = """
            CREATE TABLE IF NOT EXISTS pizza_ingredient (
                id BINARY(16) PRIMARY KEY,
                id_pizza BINARY(16) NOT NULL,
                id_ingredient BINARY(16) NOT NULL,
                FOREIGN KEY (id_pizza) REFERENCES pizza(id) ON DELETE CASCADE,
                FOREIGN KEY (id_ingredient) REFERENCES ingredient(id) ON DELETE CASCADE,
                UNIQUE(id_pizza, id_ingredient));
            """;
        stmt.executeUpdate(sql);
        System.out.println("Tabla pizza_ingredient creada");
        

        sql = "CREATE INDEX IX_user_comment ON comment(id_user);";
        stmt.executeUpdate(sql);
        sql = "CREATE INDEX IX_pizza_comment ON comment(id_pizza);";
        stmt.executeUpdate(sql);
        sql = "CREATE INDEX IX_pizza_pizza_ingredient ON pizza_ingredient(id_pizza);";
        stmt.executeUpdate(sql);
        sql = "CREATE INDEX IX_ingredient_pizza_ingredient ON pizza_ingredient(id_ingredient);";
        stmt.executeUpdate(sql);
        
        conn.close();
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
        
}
