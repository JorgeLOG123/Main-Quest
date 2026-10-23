package arg.jorge.mainquest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController {

    public static final String HOME_URL = "/home";

    @GetMapping(HOME_URL)
    public String home(){
        return "home";
    }

}
