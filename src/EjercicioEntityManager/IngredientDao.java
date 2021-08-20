package EjercicioEntityManager;

import java.util.UUID;

import objectsPizzeria.Ingredient;

public class IngredientDao {
    public void add(Ingredient ingredient){
        String sql = "INSERT INTO ingredient (id, name, price) VALUES (UUID_TO_BIN(?),?,?);";
    EntityManager
        .buildConnection(Configuration.getConfiguration())
        .addStatement(ingredient, sql, (statement, entity)->{
           statement.setObject(1, entity.id.toString());
           statement.setString(2, entity.getName());
           statement.setDouble(3, entity.getPrice()); 
        }).save();
    }
    public Ingredient select(String name){
        Ingredient ingredient= new Ingredient();
        String sql = "SELECT BIN_TO_UUID(id),name,price FROM ingredients WHERE id=(SELECT id FROM ingredient WHERE name=?));";
        ingredient = EntityManager.buildConnection(Configuration.getConfiguration())
                                .addStatement(ingredient, sql, (statement,entity)->{
                                    statement.setString(1, name);
                                }).Select(Ingredient.class, (resultSet,entity)->{
                                    entity.generateStringUUID(resultSet.getString("BIN_TO_UUID(id)"));
                                    entity.setName(resultSet.getString("name"));
                                    entity.setPrice(resultSet.getDouble("price"));
                                });
        return ingredient;
    }
}
