package arg.jorge.mainquest.dtos;

public class JugadorDTO implements DTO {
    private String nombre;
    private Long xp;
    private int nivel;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Long getXp() { return xp; }
    public void setXp(Long xp) { this.xp = xp; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }
}