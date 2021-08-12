package SQLPizzeria;

import java.util.List;
import java.util.UUID;

import objectsPizzeria.Comment;
import objectsPizzeria.Ingredient;
import objectsPizzeria.Pizza;
import objectsPizzeria.User;


public class App {

   public static void main(String[] args) {
      Dao<Ingredient> ingredientDao = new IngredientDao();
      Dao<Pizza> pizzaDao = new PizzaDao();
      Dao<Comment> commentDao = new CommentDao();

      UUID id = UUID.randomUUID();
      Ingredient ingredient = new Ingredient("masa", 0.5);
      ingredient.generateUUID(id);

      id = UUID.randomUUID();
      Ingredient ingredient2 = new Ingredient("bacon", 1.8);
      ingredient2.generateUUID(id);
      Ingredient[] ingredients={ingredient, ingredient2};

      id = UUID.randomUUID();
      Pizza pizza = new Pizza("Barbacoa", "http://localhost:8080");
      pizza.generateUUID(id);

      
      ingredientDao.add(ingredient);
      ingredientDao.add(ingredient2);
      ingredient.setPrice(0.7);
      ingredientDao.update(ingredient);
      List<Ingredient> listIngredient = ingredientDao.getAll();
      
      pizza.setIngredients(ingredients);
      pizzaDao.add(pizza);
      pizza.setUrl("http://localhost:3000");
      pizzaDao.update(pizza);
      pizzaDao.get(pizza);

      User user=new User("@gmail","Si","Soyyo","xd123");
      Dao<User> userDao = new UserDao();
      userDao.add(user);
      Comment comment = new Comment("Hola", 2.3f,user.getId(),pizza.getId());
      commentDao.add(comment);

      
      pizzaDao.delete(pizza);
      ingredientDao.delete(ingredient);
      ingredientDao.delete(ingredient2);
      userDao.delete(user);
      commentDao.delete(comment);


   }
}