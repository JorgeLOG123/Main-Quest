package arg.jorge.mainquest.dtos;

public class UsuarioAdminDTO extends JugadorDTO {
    private Long id;
    private String email;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}