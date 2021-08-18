package EjercicioEntityManager;

import java.util.UUID;

import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;

public class App {
    public static void main(String[] args) throws Exception {
        Pizza pizza = new Pizza("Barbacoa","telepizza");
        pizza.id = UUID.randomUUID();
        Ingredient ingredient = new Ingredient("Carne",1);
        ingredient.id= UUID.randomUUID();
        Ingredient[] ingredients = {ingredient};
        pizza.setIngredients(ingredients);
        PizzaDao pizzaDao = new PizzaDao(); 

        pizzaDao.add(pizza);

    //    Pizza pizza2 =  EntityManager
    //     .buildConnection(Configuration.getConfiguration())
    //     .Select<Pizza>((resulset,entidad)->{
    //         entidad.id = resulset.setObject("id");
    //         entidad.setName = resulset.getString("name");
    //     });
    }
}
