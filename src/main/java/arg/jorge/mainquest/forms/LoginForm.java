package arg.jorge.mainquest.forms;

import arg.jorge.mainquest.domain.Jugador;

public class LoginForm {
    private String email;
    private String contrasena;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public int getEmailMaxLength() { return Jugador.EMAIL_MAXLEGTH; }
}