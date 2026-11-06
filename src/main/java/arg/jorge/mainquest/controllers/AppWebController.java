package arg.jorge.mainquest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Punto de entrada de la aplicación.
 * La raíz "/" no renderiza contenido propio: redirige a /home
 * para que la URL siempre refleje la página real que se muestra.
 */
@Controller
public class AppWebController extends BaseWebController {

    @GetMapping("/")
    public String root() {
        return redirect(HomeWebController.HOME_URL);
    }
}
