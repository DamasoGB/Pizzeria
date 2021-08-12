package SQLPizzeria;

import objectsPizzeria.Comment;
import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;
import objectsPizzeria.User;


public class App {

   public static void main(String[] args) {
      Dao<Ingredient> ingredientDao = new IngredientDao();
      Dao<Pizza> pizzaDao = new PizzaDao();
      Dao<Comment> commentDao = new CommentDao();

      Ingredient ingredient = new Ingredient("masa", 0.5);
      Ingredient ingredient2 = new Ingredient("bacon", 1.8);
      Ingredient[] ingredients={ingredient, ingredient2};
      Pizza pizza = new Pizza("Barbacoa", "http://localhost:8080");

      
      ingredientDao.add(ingredient);
      ingredientDao.add(ingredient2);
      ingredient.setPrice(0.7);
      ingredientDao.update(ingredient);
      ingredientDao.get(ingredient);
      
      pizza.setIngredients(ingredients);
      pizzaDao.add(pizza);
      pizza.setUrl("http://localhost:3000");
      pizzaDao.update(pizza);
      pizzaDao.get(pizza);
      pizzaDao.delete(pizza);
      
      ingredientDao.delete(ingredient);
      ingredientDao.delete(ingredient2);
      //CommentDao (No hace falta que ese en el main) y Pizza_Ingredient en PizzaDao, grabar en el optional, metodo getAll devuelva lista de T en Dao
   }
}