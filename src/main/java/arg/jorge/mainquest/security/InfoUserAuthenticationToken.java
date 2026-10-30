package arg.jorge.mainquest.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class InfoUserAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private Long idImagen;

    public InfoUserAuthenticationToken(Long id, String email,
                                       Collection<? extends GrantedAuthority> authorities) {
        super(id, email, authorities);
    }

    @Override
    public Long getPrincipal() {
        return (Long) super.getPrincipal();
    }

    public Long getId() {
        return getPrincipal();
    }

    public String getEmail() {
        return (String) getCredentials();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Long idImagen) {
        this.idImagen = idImagen;
    }
}