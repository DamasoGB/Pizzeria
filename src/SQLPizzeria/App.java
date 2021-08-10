package SQLPizzeria;

import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;

import java.sql.Connection;
import java.sql.SQLException;

public class App {

   public static void main(String[] args) {
      Connection conn;
      try {
         conn = Connect.ConnectDB();

         //UnitOfWork.createDatabase();
         Ingredient ingredient = new Ingredient("masa", 1.5);
         IngredientDao.update(ingredient, 1.5, conn);

         Pizza pizza = new Pizza("Margarita", "URL");
         //PizzaDao.add(pizza);
         IngredientDao.select(ingredient, conn);
         PizzaDao.select(pizza, conn);

         Connect.CloseConnDB(conn);

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}