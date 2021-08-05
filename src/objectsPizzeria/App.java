package objectsPizzeria;

import java.util.HashSet;

public class App {
    public static void main(String[] args) throws Exception {
        Ingredient tomate = new Ingredient("Tomate", 2.25);
        Ingredient albahaca = new Ingredient("albahaca", 1.10);
        Ingredient sal = new Ingredient("sal", 1.05);
        Ingredient aceite = new Ingredient("aceite", 1.10);
        Ingredient mozzarella = new Ingredient("mozzarella", 1.10);
        
        HashSet<Ingredient> ingredients = new HashSet<>();
        ingredients.add(tomate);
        ingredients.add(albahaca);
        ingredients.add(sal);
        ingredients.add(aceite);
        ingredients.add(mozzarella);

        Pizza margarita = new Pizza("Margarita", "margarita.png");
        Ingredient[] ingMargarita = {tomate,albahaca,sal,aceite,mozzarella};
        margarita.setIngredients(ingMargarita);
        double precio = margarita.getPrice();
        System.out.println(precio);
        
    }
}
