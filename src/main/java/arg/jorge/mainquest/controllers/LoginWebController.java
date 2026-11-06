package arg.jorge.mainquest.controllers;

import arg.jorge.mainquest.Validators.LoginFormValidator;
import arg.jorge.mainquest.forms.LoginForm;
import arg.jorge.mainquest.services.JugadorServices;

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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
@Controller
public class LoginWebController extends BaseWebController {
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String SIGN_IN_URL = BASE_URL + "/signin";
    public static final String LOGIN_VIEW = "login";

    @Autowired
    private JugadorServices servicio;

    @Autowired
    private LoginFormValidator validator;

    @InitBinder(value = FORM_ATTRIBUTE)
    void initFormValidator(WebDataBinder binder) {
        binder.addValidators(this.validator);
    }



    @GetMapping(value = SIGN_IN_URL)
    public String init(Model model) {
        model.addAttribute(FORM_ATTRIBUTE, new LoginForm());
        return LOGIN_VIEW;
    }

    @PostMapping(value = LOGIN_URL)
    public String login(HttpSession session, @Validated @ModelAttribute(FORM_ATTRIBUTE) LoginForm formulario,
                        BindingResult results) {

        if (results.hasErrors()) return LOGIN_VIEW;

        this.servicio.login(formulario.getEmail());

        SecurityContext context = SecurityContextHolder.getContext();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        return redirect(GameWebController.GAME_URL);
    }
}

