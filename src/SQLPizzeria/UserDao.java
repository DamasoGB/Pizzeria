package SQLPizzeria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objectsPizzeria.User;

public class UserDao implements Dao<User> {

    Connection conn = null;
	PreparedStatement sentencia = null;
	ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection connection;
        connection = ConnectionFactory.getInstance().getConnection();
        return connection;
    }
    
    public void add(User user){
        try {
            String consulta  = """
                            INSERT INTO user (id, name, lastName, email, password) 
                            VALUES (?, ?, ?, ?, ?);
                            """;
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, user.getIdCadena());
            sentencia.setString(2, user.getNombre());
            sentencia.setString(3, user.getApellidos());
            sentencia.setString(4, user.getEmail());
            sentencia.setString(5, user.getContraseña());

            UnitOfWork.executeNonQuery(sentencia);
            
        } catch (SQLException e) {
             e.printStackTrace();
        } finally {
			try {
				if (sentencia != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
        
    }
    public void update(User user){
        try {
            String consulta  = """
                        UPDATE ingredient 
                        SET name = ?, lastName = ?, email = ?, password = ?
                        WHERE id = ?;
                            """;
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, user.getNombre());
            sentencia.setString(2, user.getApellidos());
            sentencia.setString(3, user.getEmail());
            sentencia.setString(4, user.getContraseña());
            
            sentencia.setString(5, user.getIdCadena());

            UnitOfWork.executeNonQuery(sentencia);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }finally {
			try {
				if (sentencia != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
    }
    public void delete(User user){
        try {
            String consulta  = """
                            DELETE FROM user 
                            WHERE id = ?;
                            """;
            conn = getConnection();	
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, user.getIdCadena());

            UnitOfWork.executeNonQuery(sentencia);
            
        } catch (SQLException e) {
             e.printStackTrace();
        }finally {
			try {
				if (sentencia != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
    }
    public void getAll(User user){
        try {
            String consulta  = """
                            SELECT id, name, lastname, email
                            FROM user 
                            WHERE id = ?;
                            """;	
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, user.getIdCadena());
            	              
            resultSet = UnitOfWork.executeQuery(sentencia);

            while(resultSet.next()){
                System.out.println(resultSet.getObject(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println();
            }
            
        } catch (SQLException e) {
             e.printStackTrace();
        } finally {
			try {
                if(resultSet != null)
                    conn.close();
				if (sentencia != null)
					conn.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
    }

}

