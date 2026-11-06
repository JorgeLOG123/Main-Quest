package arg.jorge.mainquest.controllers;

import org.springframework.stereotype.Controller;
import arg.jorge.mainquest.security.InfoUserAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;



@Controller
public abstract class BaseWebController {
    public static final String BASE_URL = "/web";
    protected static final String ID_PARAMETER = "id";

    // Clave compartida por todos los controladores para el atributo de formulario en el Model
    protected static final String FORM_ATTRIBUTE = "form";
    protected static final String INFO_ATTRIBUTE = "info";
    protected final static String LIST_ATTRIBUTE = "listado";

    /**
     * Helper para redirigir a otra URL.
     * Hace que el navegador realice una nueva petición GET a esa ruta,
     * en lugar de renderizar una vista sin cambiar la URL actual.
     */

    public InfoUserAuthenticationToken authenticationToken() {
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication auth = null;
        if (context != null && context.getAuthentication() != null) {
            auth = context.getAuthentication();
        }

        return (InfoUserAuthenticationToken) auth;
    }

    public boolean estaUsuarioLogueado() {
        Authentication auth = authenticationToken();
        return auth != null;
    }


    protected String redirect(String path) {
        return "redirect:" + path;
    }
}
