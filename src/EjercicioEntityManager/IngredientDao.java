package EjercicioEntityManager;

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
    public Ingredient select(){
        Ingredient ingredient;
        String sql = "SELECT BIN_TO_UUID(id),name,price FROM ingredients WHERE id=UUID_TO_BIN(?)";
        ingredient = EntityManager.buildConnection(Configuration.getConfiguration())
                                .addStatement(ingredient, sql, (statement,entity)->{
                                    statement.setObject(1, entity.id.toString());
                                }).select(Ingredient.class, (resultSet,entity)->{
                                    entity.setUUID()=resultSet.getObject("id");
                                    entity.setName()=resultSet.setString("name");
                                    entity.setPrice()=resultSet.setDouble("price");
                                }).orElseThrow();
        return ingredient;
    }
}
