package arg.jorge.mainquest.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutWebController extends BaseWebController {
    public static final String LOGOUT_URL = BASE_URL + "/logout";

    @GetMapping(value = LOGOUT_URL)
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);

        SecurityContextHolder.clearContext();

        return redirect(LoginWebController.SIGN_IN_URL);
    }
}