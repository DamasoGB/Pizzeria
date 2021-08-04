import java.util.HashSet;

public class App {
    public static void main(String[] args) throws Exception {
        Ingrediente tomate = new Ingrediente("Tomate", 2.25);
        Ingrediente albahaca = new Ingrediente("albahaca", 1.10);
        Ingrediente sal = new Ingrediente("sal", 1.05);
        Ingrediente aceite = new Ingrediente("aceite", 1.10);
        Ingrediente mozzarella = new Ingrediente("mozzarella", 1.10);
        
        HashSet<Ingrediente> ingredientes = new HashSet<>();
        ingredientes.add(tomate);
        ingredientes.add(albahaca);
        ingredientes.add(sal);
        ingredientes.add(aceite);
        ingredientes.add(mozzarella);

        Pizza margarita = new Pizza("Margarita", "margarita.png");
        Ingrediente[] ingMargarita = {tomate,albahaca,sal,aceite,mozzarella};
        margarita.setIngredientes(ingMargarita);
        double precio = margarita.getPrice();
        System.out.println(precio);
        
    }
}
