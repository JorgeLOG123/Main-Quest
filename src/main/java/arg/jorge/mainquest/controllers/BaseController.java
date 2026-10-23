package arg.jorge.mainquest.controllers;

import arg.jorge.mainquest.session.InfoSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

@Controller
public abstract class BaseController {
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

    protected InfoSession getInfoSession(HttpSession session) {
        return (InfoSession) session.getAttribute(INFO_ATTRIBUTE);
    }

    protected boolean estaUsuarioLogueado(HttpSession session) {
        InfoSession info = getInfoSession(session);
        return info != null && info.isValidEmail();
    }


    protected String redirect(String path) {
        return "redirect:" + path;
    }
}
