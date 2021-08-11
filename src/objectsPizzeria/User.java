package objectsPizzeria;

public class User extends Entity{
	public String email;
	public String nombre;
	public String apellidos;
	public String contraseña;
	
	public User(String email, String nombre, String apellidos, String contraseña){
        super();
		this.email=email;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.contraseña=contraseña;
	}

    public void validate(){
        super.validate();
        try {
            if (email == null || email.equals("")) {
                throw new Exception("Email inválido");
            }
            if (nombre == null || nombre.equals("")) {
                throw new Exception("Nombre para usuario inválido");
            }
            if (contraseña == null || contraseña.equals("")) {
                throw new Exception("Contraseña para usuario inválida");
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getApellidos(){
        return this.apellidos;
    }
    public void setApellidos(String apellidos){
        this.apellidos=apellidos;
    }

    public String getContraseña(){
        return this.contraseña;
    }
    public void setContraseña(String contraseña){
        this.contraseña=contraseña;
    }
}
