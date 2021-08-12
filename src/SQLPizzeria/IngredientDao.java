package SQLPizzeria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import objectsPizzeria.Ingredient;

public class IngredientDao implements Dao<Ingredient> {

    Connection conn = null;
	PreparedStatement sentencia = null;
	ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection connection;
        connection = ConnectionFactory.getInstance().getConnection();
        return connection;
    }
    
    public void add(Ingredient ingredient){
        try {
            String consulta  = """
                            INSERT INTO ingredient (id, name, price) 
                            VALUES (UUID_TO_BIN(?), ?, ?);
                            """;
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, ingredient.getIdCadena());
            sentencia.setString(2, ingredient.getName());
            sentencia.setDouble(3, ingredient.getPrice());

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
    public void update(Ingredient ingredient){
        try {
            String consulta  = """
                        UPDATE ingredient 
                        SET price = ?, name = ? 
                        WHERE id = UUID_TO_BIN(?);
                            """;
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setDouble(1, ingredient.getPrice());
            sentencia.setString(2, ingredient.getName());
            sentencia.setObject(3, ingredient.getIdCadena());

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
    public void delete(Ingredient ingredient){
        try {
            String consulta  = """
                            DELETE FROM ingredient 
                            WHERE id = UUID_TO_BIN(?);
                            """;
            conn = getConnection();	
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, ingredient.getIdCadena());

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
    public Optional<Ingredient> get(Ingredient ingredient){
        Optional<Ingredient> oIngredient = Optional.of(ingredient);
        try {
            String consulta  = """
                            SELECT id, name, price
                            FROM ingredient 
                            WHERE id = UUID_TO_BIN(?);
                            """;	
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, ingredient.getIdCadena());
            	              
            resultSet = UnitOfWork.executeQuery(sentencia);

            while(resultSet.next()){
                System.out.println(resultSet.getObject(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getDouble(3));
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
        return oIngredient;
    }

}
