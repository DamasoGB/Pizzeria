package EjercicioEntityManager;

import java.util.UUID;

import objectsPizzeria.Pizza;

public class App {
    public static void main(String[] args) throws Exception {
        Pizza pizza = new Pizza("Barbacoa","telepizza");
        pizza.id=UUID.randomUUID();
        String sql = "INSERT INTO pizza (id, name, url) VALUES (UUID_TO_BIN(?),?,?)";
        EntityManager
        .buildConnection(Configuration.getConfiguration())
        .addStatement(pizza, sql, (statement, entity)->{
           statement.setObject(1, pizza.id.toString());
           statement.setString(2, pizza.getName());
           statement.setString(3, pizza.getUrl());   
        }).save();


    //    Pizzas p =  EntityManager
    //     .buildConnection(Configuration.getConfiguration())
    //     .Select<Pizza>((resulset,entidad)->{
    //         entidad.id = resulset.getString("name")
    //     });
    }
}
