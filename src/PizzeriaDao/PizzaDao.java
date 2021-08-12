package PizzeriaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;

public class PizzaDao implements Dao<Pizza> {

    Connection conn = null;
	PreparedStatement sentencia = null;
	ResultSet resultSet = null;
    List<Pizza> pizzas = new ArrayList<Pizza>();

    private Connection getConnection() throws SQLException {
        Connection connection;
        connection = ConnectionFactory.getInstance().getConnection();
        return connection;
    }
    
    public void add(Pizza pizza){
        try {
            conn=getConnection();
            String consulta = "BEGIN;";
            sentencia= conn.prepareStatement(consulta);
            UnitOfWork.executeNonQuery(sentencia);
            consulta = """
                        INSERT INTO Pizza (id, name, url) 
                        VALUES (UUID_TO_BIN(?), ?, ?);
                        """;
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, pizza.getIdCadena());
            sentencia.setString(2, pizza.getName());
            sentencia.setString(3, pizza.getUrl());
            UnitOfWork.executeNonQuery(sentencia);

            consulta = """
                    INSERT INTO pizza_ingredient (id, id_pizza, id_ingredient)
                    VALUES (UUID_TO_BIN(UUID()),
                    (SELECT id FROM pizza WHERE name=?),
                    (SELECT id FROM ingredient WHERE name=?));
                    """;

            sentencia= conn.prepareStatement(consulta);
            Set<Ingredient> ingredients = pizza.getIngredients();
            Iterator<Ingredient> iterator = ingredients.iterator();
            while(iterator.hasNext()){
                Ingredient ingredient = iterator.next();
                sentencia.setString(1, pizza.getName());
                sentencia.setString(2, ingredient.getName());
                sentencia.addBatch();
            }
            UnitOfWork.executeNonQuery(sentencia);

            consulta = "COMMIT";
            sentencia= conn.prepareStatement(consulta);
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
    public void update(Pizza pizza){
        try {
            String consulta  = """
                        UPDATE Pizza 
                        SET url = ?
                        WHERE id = UUID_TO_BIN(?);
                            """;
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setString(1, pizza.getUrl());
            sentencia.setObject(2, pizza.getIdCadena());

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
    public void delete(Pizza pizza){
        try {
            String consulta  = """
                            DELETE FROM Pizza 
                            WHERE id = UUID_TO_BIN(?);
                            """;
            conn = getConnection();	
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, pizza.getIdCadena());

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
    public Optional<Pizza> get(Pizza pizza){
        
        try {
            String consulta  = """
                            SELECT BIN_TO_UUID(id), name, url
                            FROM Pizza 
                            WHERE id = UUID_TO_BIN(?);
                            """;	
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
            sentencia.setObject(1, pizza.getIdCadena());
            	              
            resultSet = UnitOfWork.executeQuery(sentencia);
            
            
            while(resultSet.next()){
                pizza.generateStringUUID(resultSet.getString(1));
                pizza.setName(resultSet.getString(2));
                pizza.setUrl(resultSet.getString(3));
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
        Optional<Pizza> oPizza = Optional.of(pizza);
        return oPizza;
    }

    public List<Pizza> getAll() {
        try {
            String consulta  = """
                            SELECT BIN_TO_UUID(id), name, url
                            FROM pizza;
                            """;	
            conn = getConnection();
            sentencia= conn.prepareStatement(consulta);
	              
            resultSet = UnitOfWork.executeQuery(sentencia);

            while(resultSet.next()){
                Pizza pizza = new Pizza(resultSet.getString("name"),
                                        resultSet.getString("url"));
                pizza.generateStringUUID(resultSet.getString("BIN_TO_UUID(id)"));
                pizzas.add(pizza);
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
        return pizzas;
    }

}

