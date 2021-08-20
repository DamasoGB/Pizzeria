package EjercicioEntityManager;

import java.util.UUID;

import objectsPizzeria.Ingredient;

public class App {
    public static void main(String[] args) throws Exception {
        IngredientDao ingredientDao = new IngredientDao();
        Ingredient ingredient = new Ingredient("Carne",1);
        ingredient.id= UUID.randomUUID();
        ingredientDao.add(ingredient);
        Ingredient ingredient2 = ingredientDao.select("Carne");
        // System.out.println(ingredient2.getIdCadena());
        // System.out.println(ingredient2.getName());
        // System.out.println(ingredient2.getPrice());
        
    }
}
