package objectsPizzeria;

import java.util.HashSet;
import java.util.Set;

public class Pizza extends Entity {
    private String name;
    private double price=0;
    private String url;
    private final Set<Ingredient> ingredients = new HashSet<Ingredient>();
	private final Set<Comment> comments = new HashSet<Comment>();

    public void validate() {
        super.validate();
        try {
            if (name == null || name.equals("")) {
                throw new Exception("Nombre para la pizza inválido");
            }
            if (price == 0) {
                throw new Exception("Precio para pizza inválido");
            }
            if (url == null || url.equals("")) {
                throw new Exception("Url de foto para pizza inválido");
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public Pizza(String name,String url){
        super();
		this.name=name;
		this.url=url;
	}

    public void setIngredients(Ingredient[] ingredients){
        for (Ingredient ingredient : ingredients) {
            this.ingredients.add(ingredient);
        }
    }

    public Set<Ingredient> getIngredients(){
        return this.ingredients;
    }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }

    public void setComment(Comment[] comments){
        for (Comment comment : comments) {
            this.comments.add(comment);
        }
    }

    public Set<Comment> getComment(){
        return this.comments;
    }

    public void removeComment(Comment comment){
        this.comments.remove(comment);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getUrl(){
        return this.url;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public double getPrice() {
        for(Ingredient ingred: ingredients){
            price += ingred.getPrice();
        }
        price *= 1.2;
        double d = price;
        double price = Math.round(d*100.0)/100.0;
        return price;
    }
}
