package EjercicioEntityManager;

import objectsPizzeria.Pizza;

import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.Set;

import objectsPizzeria.Ingredient;

public class PizzaEntityManager {
    public void add(Pizza pizza){
        String sql="";
        PreparedStatement statement;
        Set<Ingredient> ingredients = pizza.getIngredients();
        Iterator<Ingredient> iterable = ingredients.iterator();

        EntityManager.buildConnection(Configuration.getConfiguration())
		     .addStatement(pizza,sql,(statement,pizza)->{
				statement.setInt(pizza.getId())
                      })
                     .addRangeStatement<Ingredient>(iterable,sql,(statement,T)->{

		      })
		     .save();
    }
}
