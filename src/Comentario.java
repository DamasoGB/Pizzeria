import java.util.Date;

public class Comentario extends Entity{
	private String texto;
	private int puntuacion;
	private final Date fecha=new Date();
	private final Usuario usuario;
	
    public void validate(){
        super.validate();
        try {
            if (texto == null || texto.equals("")) {
                throw new Exception("texto de comentario inválido");
            }
            if (usuario == null) {
                throw new Exception("ID para usuario inválido");
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public Comentario(String texto, int puntuacion, Date fecha, Usuario usuario){
		this.texto=texto;
		this.puntuacion=puntuacion;
		this.usuario=usuario;

	}

    public String getTexto(){
        return this.texto;
    }
    public void setTexto(String texto){
        this.texto=texto;
    }

    public int getPuntuacion(){
        return this.puntuacion;
    }
    public void setPuntuacion(int puntuacion){
        this.puntuacion=puntuacion;
    }

    public Date getFecha(){
        return this.fecha;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }
    
}
