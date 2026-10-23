package arg.jorge.mainquest.controllers;

import arg.jorge.mainquest.Validators.RegistracionFormValidator;
import arg.jorge.mainquest.forms.RegistracionForm;
import arg.jorge.mainquest.services.JugadorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistracionController extends BaseController {
    public static final String REGISTRACION_URL = "/registro";
    public static final String SIGN_UP_VIEW = "signup";

    @Autowired
    private JugadorServices servicio;

    @Autowired
    private RegistracionFormValidator validator;

    @InitBinder(value = FORM_ATTRIBUTE)
    void initFormValidator(WebDataBinder binder) {
        binder.addValidators(this.validator);
    }

    @GetMapping(value = REGISTRACION_URL)
    public String init(Model model) {
        model.addAttribute(FORM_ATTRIBUTE, new RegistracionForm());
        return SIGN_UP_VIEW;
    }

    @PostMapping(value = REGISTRACION_URL)
    public String registracion(@Validated @ModelAttribute(FORM_ATTRIBUTE) RegistracionForm formulario,
                               BindingResult results) {

        if (results.hasErrors()) return SIGN_UP_VIEW;

        this.servicio.registrar(
                formulario.getNombre(),
                formulario.getContrasena(),
                formulario.getEmail()
        );

        return redirect(HomeController.HOME_URL);
    }
}