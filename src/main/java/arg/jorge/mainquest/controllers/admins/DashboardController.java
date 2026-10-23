package arg.jorge.mainquest.controllers.admins;

import arg.jorge.mainquest.services.JugadorServices;
import arg.jorge.mainquest.services.MisionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController extends AdminController {
    public static final String DASHBOARD_URL = ADMIN_URL + "/dashboard";
    private static final String DASHBOARD_VIEW = ADMIN_VIEW + "/dashboard";

    private static final String CANT_JUGADORES_KEY = "cant_jugadores";
    private static final String CANT_MISIONES_KEY = "cant_misiones";

    @Autowired
    private JugadorServices jugadorServices;

    @Autowired
    private MisionServices misionServices;

    @GetMapping(value = DASHBOARD_URL)
    public String view(Model model) {
        model.addAttribute(CANT_JUGADORES_KEY, jugadorServices.cantidadEntidades());
        model.addAttribute(CANT_MISIONES_KEY, misionServices.cantidadEntidades());
        return DASHBOARD_VIEW;
    }
}