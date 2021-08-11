package SQLPizzeria;

import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;


public class App {

   public static void main(String[] args) {
      Dao<Ingredient> ingredientDao = new IngredientDao();
      Dao<Pizza> pizzaDao = new PizzaDao();

      Ingredient ingredient = new Ingredient("tomate", 0.5);
      Pizza pizza = new Pizza("Carbonara", "http://localhost:8080");

      
      ingredientDao.add(ingredient);
      ingredient.setPrice(0.7);
      ingredient.setName("cebolla");
      ingredientDao.update(ingredient);
      ingredientDao.getAll(ingredient);
      ingredientDao.delete(ingredient);

      
      pizzaDao.add(pizza);
      pizza.setUrl("http://localhost:3000");
      pizza.setName("Margarita");
      pizzaDao.update(pizza);
      pizzaDao.getAll(pizza);
      pizzaDao.delete(pizza);

      
   }
}