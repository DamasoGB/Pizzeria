package SQLPizzeria;

import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;


public class App {

   public static void main(String[] args) {
      Dao<Ingredient> ingredientDao = new IngredientDao();
      Dao<Pizza> pizzaDao = new PizzaDao();

      Ingredient ingredient = new Ingredient("masa", 0.5);
      Pizza pizza = new Pizza("Barbacoa", "http://localhost:8080");

      
      ingredientDao.add(ingredient);
      ingredient.setPrice(0.7);
      ingredientDao.update(ingredient);
      ingredientDao.getAll(ingredient);
      ingredientDao.delete(ingredient);

      
      pizzaDao.add(pizza);
      pizza.setUrl("http://localhost:3000");
      pizzaDao.update(pizza);
      pizzaDao.getAll(pizza);
      pizzaDao.delete(pizza);

      //CommentDao, UserDao y Pizza_IngredientDao (O en pizza)
   }
}