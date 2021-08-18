package EjercicioEntityManager;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;

public class PizzaDao {
    public void add(Pizza pizza){
        Set<Ingredient> ingredients = new HashSet<Ingredient>();
        Iterable<Ingredient> iterable = ingredients;
        String sql2="";
        String sql3="";
        if(pizza.getIngredients()!=null){
            for (Ingredient ingredient : pizza.getIngredients()) {
                ingredients.add(ingredient);
            }
            sql2 = "INSERT INTO ingredient (id, name, price) VALUES (UUID_TO_BIN(?),?,?);";
            sql3 = "INSERT INTO pizza_ingredient (id, id_pizza, id_ingredient) VALUES (UUID_TO_BIN(?),(SELECT id FROM pizza WHERE name=?),(SELECT id FROM ingredient WHERE name=?));";
        }
        String sql = "INSERT INTO pizza (id, name, url) VALUES (UUID_TO_BIN(?),?,?);";
        EntityManager
        .buildConnection(Configuration.getConfiguration())
        .addStatement(pizza, sql, (statement, entity)->{
           statement.setObject(1, entity.id.toString());
           statement.setString(2, entity.getName());
           statement.setString(3, entity.getUrl()); 
        }).addRangeStatement(iterable, sql2, (statement, entity)->{
            statement.setObject(1, entity.id.toString());
            statement.setString(2, entity.getName());
            statement.setDouble(3, entity.getPrice()); 
        }).addRangeStatement(iterable, sql3, (statement, entity)->{
            statement.setObject(1, UUID.randomUUID().toString());
            statement.setObject(2, pizza.getName().toString());
            statement.setObject(3, entity.getName().toString());
        }).save();
    }
}
