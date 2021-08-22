package EjercicioEntityManager;

import java.util.UUID;

import objectsPizzeria.Ingredient;

public class App {
    public static void main(String[] args) throws Exception {
        IngredientDao ingredientDao = new IngredientDao();
        Ingredient ingredient = new Ingredient();
        ingredient.setName("tomate");
        ingredient.setPrice(1.3);
        ingredient.id= UUID.randomUUID();
        ingredientDao.add(ingredient);
        Ingredient ingredient2 = ingredientDao.select(ingredient.getName());
        
        
    }
}
