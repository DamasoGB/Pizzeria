package PizzeriaDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    static final String driverClassName = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = System.getenv("JAVA_SERV")+"pizzeria";
    static final String USER = System.getenv("JAVA_USER");
    static final String PASS = System.getenv("JAVA_PASSWD");

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {
       try {
          Class.forName(driverClassName);
       } catch (ClassNotFoundException e) {
          e.printStackTrace();
       }
    }
 
    public Connection getConnection() throws SQLException {
       Connection conn = null;
       conn = DriverManager.getConnection(DB_URL, USER, PASS);
       return conn;
    }
 
    public static ConnectionFactory getInstance() {
       if (connectionFactory == null) {
          connectionFactory = new ConnectionFactory();
       }
       return connectionFactory;
    }
 }
