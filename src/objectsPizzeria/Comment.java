package objectsPizzeria;

import java.util.Date;
import java.util.UUID;

public class Comment extends Entity{
	private String text;
	private float score;
    private java.util.Date fecha;
	private final User user= new User("","","","");
    private final Pizza pizza=new Pizza("","");
	
    public void validate(){
        super.validate();
        try {
            if (text == null || text.equals("")) {
                throw new Exception("text de comentario inválido");
            }
            if (user == null) {
                throw new Exception("ID para user inválido");
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public Comment(String text, float score, UUID user, UUID pizza){
        super();
		this.text=text;
		this.score=score;
        this.fecha=new Date();
        this.user.generateUUID(user);
        this.pizza.generateUUID(pizza);
	}

    public Comment(String text, float score){
        super();
		this.text=text;
		this.score=score;
        this.fecha=new Date();
	}
    public String getTexto(){
        return this.text;
    }
    public void setTexto(String text){
        this.text=text;
    }
    public Pizza getPizza(){
        return this.pizza;
    }
    public float getScore(){
        return this.score;
    }
    public void setScore(float score){
        this.score=score;
    }

    public Date getFecha(){
        java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
        return sqlFecha;
    }

    public void setFecha(java.sql.Date fecha){
        this.fecha = new java.util.Date(fecha.getTime());
    }

    public User getUser(){
        return this.user;
    }
    public void setUser(String id){
        this.user.generateStringUUID(id);
    }
    public void setPizza(String id){
        this.pizza.generateStringUUID(id);
    }
}
