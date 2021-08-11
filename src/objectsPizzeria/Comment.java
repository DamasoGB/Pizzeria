package objectsPizzeria;

import java.util.Date;

public class Comment extends Entity{
	private String text;
	private int score;
	private final Date fecha=new Date();
	private final User user;
	
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

	public Comment(String text, int score, Date fecha, User user){
        super();
		this.text=text;
		this.score=score;
		this.user=user;

	}

    public String getTexto(){
        return this.text;
    }
    public void setTexto(String text){
        this.text=text;
    }

    public int getScore(){
        return this.score;
    }
    public void setScore(int score){
        this.score=score;
    }

    public Date getFecha(){
        return this.fecha;
    }

    public User getUser(){
        return this.user;
    }
    
}
