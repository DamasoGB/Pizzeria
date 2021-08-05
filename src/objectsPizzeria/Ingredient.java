package objectsPizzeria;

public class Ingredient extends Entity{
    private String name;
    private double price;
    public void validate(){
        super.validate();
        try {
            if (name == null || name.equals("")) {
                throw new Exception("Nombre para el ingrediente inválido");
            }
            if (price == 0) {
                throw new Exception("Precio para el ingrediente inválido");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Ingredient(String name, double price){
		this.name=name;
		this.price=price;
	}

    public double getPrice(){
        return this.price;
    }
    public String getName(){
        return this.name;
    }
 }
