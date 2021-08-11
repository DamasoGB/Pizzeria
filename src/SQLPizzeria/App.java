package SQLPizzeria;

import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;
import objectsPizzeria.User;

public class App {

   public static void main(String[] args) {
      Dao<Ingredient> ingredientDao = new IngredientDao();
      Dao<Pizza> pizzaDao = new PizzaDao();
      Dao<User> userDao = new UserDao();

      Ingredient ingredient = new Ingredient("tomate", 0.5);
      ingredientDao.add(ingredient);
      ingredient.setPrice(0.7);
      ingredient.setName("cebolla");
      ingredientDao.update(ingredient);
      ingredientDao.getAll(ingredient);
      ingredientDao.delete(ingredient);

      Pizza pizza = new Pizza("Carbonara", "http://localhost:8080");
      pizzaDao.add(pizza);
      pizza.setUrl("http://localhost:3000");
      pizza.setName("Margarita");
      pizzaDao.update(pizza);
      pizzaDao.getAll(pizza);
      pizzaDao.delete(pizza);

      User user = new User("damaso.garcia-bravo@capgemini.com","Dámaso","García","Hola123");
      userDao.add(user);
      user.setEmail("damasogb@gmail.com");
      userDao.update(user);
      userDao.getAll(user);
      userDao.delete(user);
   }
}