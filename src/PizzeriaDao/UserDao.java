package PizzeriaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import objectsPizzeria.User;

public class UserDao implements Dao<User> {

    Connection conn = null;
	PreparedStatement sentencia = null;
	ResultSet resultSet = null;
    List<User> users = new ArrayList<User>();

    private Connection getConnection() throws SQLException {
        Connection connection;
        connection = ConnectionFactory.getInstance().getConnection();
        return connection;
    }
    
    public void add(User user){
        try {
            String consulta  = """
                            INSERT INTO user (id, name, lastName, email, password) 
                            VALUES (UUID_TO_BIN(?), ?, ?, ?, ?);
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
                        UPDATE user 
                        SET name = ?, lastName = ?, email = ?, password = ?
                        WHERE id = UUID_TO_BIN(?);
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
                            WHERE id = UUID_TO_BIN(?);
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
    public Optional<User> get(User user){
        
        try {
            String consulta  = """
                            SELECT BIN_TO_UUID(id), name, lastname, email
                            FROM user 
                            WHERE id = UUID_TO_BIN(?);
                            """;	
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, user.getIdCadena());
            	              
            resultSet = UnitOfWork.executeQuery(sentencia);

            while(resultSet.next()){
                user.generateStringUUID(resultSet.getString(1));
                user.setNombre(resultSet.getString(2));
                user.setApellidos(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
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
        Optional<User> oUser = Optional.of(user);
        return oUser;
    }

    public List<User> getAll() {
        try {
            String consulta  = """
                            SELECT id, name, lastname, email, password
                            FROM user;
                            """;	
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
	              
            resultSet = UnitOfWork.executeQuery(sentencia);

            while(resultSet.next()){
                User user = new User(resultSet.getString("email"),
                                    resultSet.getString("name"),
                                    resultSet.getString("lastname"),
                                    resultSet.getString("password"));
                user.generateStringUUID(resultSet.getString("BIN_TO_UUID(id)"));
                users.add(user);
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
        return users;
    }

}

