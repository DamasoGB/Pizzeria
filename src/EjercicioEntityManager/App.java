package EjercicioEntityManager;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;

public class App {
    public static void main(String[] args) throws Exception {
        Pizza pizza = new Pizza("Barbacoa","telepizza");
        pizza.id=UUID.randomUUID();
        Ingredient ingredient = new Ingredient("Carne",1);
        ingredient.id= UUID.randomUUID();
        Set<Ingredient> ingredients = new HashSet<Ingredient>();
        ingredients.add(ingredient);
        Iterable<Ingredient> iterable = ingredients;
        String sql = "INSERT INTO pizza (id, name, url) VALUES (UUID_TO_BIN(?),?,?);";
        String sql2 = "INSERT INTO ingredient (id, name, price) VALUES (UUID_TO_BIN(?),?,?);";
        String sql3 = "INSERT INTO pizza_ingredient (id, id_pizza, id_ingredient) VALUES (UUID_TO_BIN(?),(SELECT id FROM pizza WHERE name=?),(SELECT id FROM ingredient WHERE name=?));";
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

        Ingredient ingredient2 = new Ingredient("Pollo",1);
        ingredient2.id= UUID.randomUUID();
        sql = "INSERT INTO ingredient (id, name, price) VALUES (UUID_TO_BIN(?),?,?)";
        EntityManager
        .buildConnection(Configuration.getConfiguration())
        .addStatement(ingredient2, sql, (statement, entity)->{
           statement.setObject(1, entity.id.toString());
           statement.setString(2, entity.getName());
           statement.setDouble(3, entity.getPrice());   
        }).save();

        sql = "UPDATE ingredient SET price = ? WHERE name = ?";
        EntityManager
        .buildConnection(Configuration.getConfiguration())
        .addStatement(ingredient2, sql, (statement, entity)->{
            statement.setDouble(1, 1.5);
            statement.setString(2, ingredient2.getName());  
        }).save();
    
        sql = "DELETE FROM ingredient WHERE name = ?";
        EntityManager
        .buildConnection(Configuration.getConfiguration())
        .addStatement(ingredient2, sql, (statement, entity)->{
            statement.setString(1, ingredient2.getName());  
        }).save();





    //    Pizzas p =  EntityManager
    //     .buildConnection(Configuration.getConfiguration())
    //     .Select<Pizza>((resulset,entidad)->{
    //         entidad.id = resulset.getString("name")
    //     });
    }
}
