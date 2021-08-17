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
        Iterable<Ingredient> iterable;

        EntityManager.buildConnection(Configuration.getConfiguration())
		     .addStatement(pizza,sql,(statement,entity)->{
				statement.setObject(1, entity.getId().toString());
                      })
                     .addRangeStatement(iterable,sql,(statement,entity)->{

		      })
		     .save();
    }
}
