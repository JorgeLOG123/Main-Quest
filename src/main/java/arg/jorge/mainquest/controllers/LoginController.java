package arg.jorge.mainquest.controllers;

import arg.jorge.mainquest.Validators.LoginFormValidator;
import arg.jorge.mainquest.forms.LoginForm;
import arg.jorge.mainquest.services.JugadorServices;
import arg.jorge.mainquest.session.InfoSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;   // ✅
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController extends BaseController {
    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_VIEW = "login";

    @Autowired
    private JugadorServices servicio;

    @Autowired
    private LoginFormValidator validator;

    @InitBinder(value = FORM_ATTRIBUTE)
    void initFormValidator(WebDataBinder binder) {
        binder.addValidators(this.validator);
    }

    @GetMapping(value = LOGIN_URL)
    public String init(Model model) {
        model.addAttribute(FORM_ATTRIBUTE, new LoginForm());
        return LOGIN_VIEW;
    }

    @PostMapping(value = LOGIN_URL)
    public String login(HttpSession session, @Validated @ModelAttribute(FORM_ATTRIBUTE) LoginForm formulario,
                        BindingResult results) {

        if (results.hasErrors()) return LOGIN_VIEW;

        session.setAttribute(INFO_ATTRIBUTE, new InfoSession(formulario.getEmail()));

        return redirect(GameController.GAME_URL);
    }
}


