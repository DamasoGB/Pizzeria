import java.util.HashSet;
import java.util.Set;

public class Pizza extends Entity {
    private String name;
    private double price=0;
    private String url;
    private final Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
	private final Set<Comentario> comentarios = new HashSet<Comentario>();

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
		this.name=name;
		this.url=url;
	}

    public void setIngredientes(Ingrediente[] ingredientes){
        for (Ingrediente ingrediente : ingredientes) {
            this.ingredientes.add(ingrediente);
        }
    }

    public Set<Ingrediente> getIngredientes(){
        return this.ingredientes;
    }

    public void removeIngrediente(Ingrediente ingrediente){
        this.ingredientes.remove(ingrediente);
    }

    public void setComentario(Comentario[] comentarios){
        for (Comentario comentario : comentarios) {
            this.comentarios.add(comentario);
        }
    }

    public Set<Comentario> getComentario(){
        return this.comentarios;
    }

    public void removeComentario(Comentario comentario){
        this.comentarios.remove(comentario);
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
        for(Ingrediente ingred: ingredientes){
            price += ingred.getPrice();
        }
        price *= 1.2;
        double d = price;
        double price = Math.round(d*100.0)/100.0;
        return price;
    }
}
