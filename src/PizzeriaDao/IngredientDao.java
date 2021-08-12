package PizzeriaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import objectsPizzeria.Ingredient;

public class IngredientDao implements Dao<Ingredient> {

    Connection conn = null;
	PreparedStatement sentencia = null;
	ResultSet resultSet = null;
    List<Ingredient> ingredients = new ArrayList<Ingredient>();

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
        
        try {
            String consulta  = """
                            SELECT BIN_TO_UUID(id), name, price
                            FROM ingredient 
                            WHERE id = UUID_TO_BIN(?);
                            """;	
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, ingredient.getIdCadena());
            	              
            resultSet = UnitOfWork.executeQuery(sentencia);

            while(resultSet.next()){
                ingredient.generateStringUUID(resultSet.getString(1));
                ingredient.setName(resultSet.getString(2));
                ingredient.setPrice(resultSet.getDouble(3));
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
        Optional<Ingredient> oIngredient = Optional.of(ingredient);
        return oIngredient;
    }

    public List<Ingredient> getAll(){
        try {
            String consulta  = """
                            SELECT BIN_TO_UUID(id), name, price
                            FROM ingredient;
                            """;	
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
	              
            resultSet = UnitOfWork.executeQuery(sentencia);

            while(resultSet.next()){
                Ingredient ingredient = new Ingredient(resultSet.getString("name"),
                                                    resultSet.getDouble("price"));
                ingredient.generateStringUUID(resultSet.getString("BIN_TO_UUID(id)"));
                ingredients.add(ingredient);
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
        return ingredients;
    }
}
