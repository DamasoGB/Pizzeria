package SQLPizzeria;

import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;

public class App {

   public static void main(String[] args) {
      //UnitOfWork.createDatabase();
      Ingredient ingredient = new Ingredient("masa", 1.5);
      IngredientDao.update(ingredient, 1.5);

      Pizza pizza = new Pizza("Margarita", "URL");
      //PizzaDao.add(pizza);
      IngredientDao.select(ingredient);
      PizzaDao.select(pizza);
   }
}