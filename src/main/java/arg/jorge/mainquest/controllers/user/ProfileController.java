package arg.jorge.mainquest.controllers.user;

import arg.jorge.mainquest.controllers.BaseController;
import arg.jorge.mainquest.controllers.LoginController;
import arg.jorge.mainquest.domain.Imagen;
import arg.jorge.mainquest.forms.ProfileForm;
import arg.jorge.mainquest.services.ImagenService;
import arg.jorge.mainquest.services.JugadorServices;
import arg.jorge.mainquest.session.InfoSession;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ProfileController extends BaseController {
    public static final String VIEW_PROFILE_URL = "/profile/view";
    public static final String SAVE_PROFILE_URL = "/profile/save";
    public static final String VIEW_PICTURE_URL = "/profile/picture/view";
    private static final String PROFILE_VIEW = "user/profile";

    @Autowired
    private JugadorServices servicio;

    @Autowired
    private ImagenService imagenService;

    @GetMapping(value = VIEW_PROFILE_URL)
    public String viewProfile(HttpSession session, Model model) {
        if (!estaUsuarioLogueado(session)) {
            return redirect(LoginController.LOGIN_URL);
        }

        InfoSession info = getInfoSession(session);
        ProfileForm form = this.servicio.prepareProfile(info.getEmail());
        model.addAttribute(FORM_ATTRIBUTE, form);

        return PROFILE_VIEW;
    }

    @PostMapping(value = SAVE_PROFILE_URL)
    public String saveProfile(HttpSession session,
                              @ModelAttribute(FORM_ATTRIBUTE) ProfileForm form) {
        if (!estaUsuarioLogueado(session)) {
            return redirect(LoginController.LOGIN_URL);
        }

        InfoSession info = getInfoSession(session);
        ProfileForm actual = this.servicio.prepareProfile(info.getEmail());
        form.setId(actual.getId());
        form.setEmail(actual.getEmail());

        this.servicio.actualizarPerfil(form);

        return redirect(VIEW_PROFILE_URL);
    }






    @GetMapping(value = VIEW_PICTURE_URL)
    public void viewPicture(HttpServletResponse response,
                            @RequestParam(name = ID_PARAMETER, defaultValue = "-1") Long id) {
        Imagen imagen = this.imagenService.obtener(id);

        if (imagen == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            response.setContentType(imagen.getContentType());
            response.getOutputStream().write(imagen.getContent());
            response.flushBuffer();
        } catch (IOException ex) {
            System.out.println("La imagen no pudo ser enviada");
        }
    }
}