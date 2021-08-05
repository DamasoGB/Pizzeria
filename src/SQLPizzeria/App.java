package SQLPizzeria;

import objectsPizzeria.Ingredient;

public class App {

   public static void main(String[] args) {
      //UnitOfWork.createDatabase();
      Ingredient ingredient = new Ingredient("masa", 1.5);
      IngredientDao.add(ingredient);
   }
}