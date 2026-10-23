package arg.jorge.mainquest.forms;

import arg.jorge.mainquest.domain.Jugador;

public class JugadorAdminForm  extends EntityForm{

    private String nombre;
    private String email;
    private String contrasena;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public int getContrasenaMinLength() { return Jugador.CONTRASENA_MIN_LENGTH; }
}
