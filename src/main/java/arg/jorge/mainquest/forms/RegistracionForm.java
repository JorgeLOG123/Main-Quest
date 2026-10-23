package arg.jorge.mainquest.forms;

public class RegistracionForm {

    private String nombre;
    private String email;
    private String contrasena;

    // Constructor sin argumentos (obligatorio para Spring)
    public RegistracionForm() {
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}