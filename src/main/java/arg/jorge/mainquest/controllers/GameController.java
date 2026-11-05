package arg.jorge.mainquest.controllers;

import arg.jorge.mainquest.session.InfoSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController extends BaseController {
    public static final String GAME_URL = "/game";
    public static final String GAME_AS_GUEST_URL = "/game/guest";
    private static final String GAME_WELCOME_VIEW = "game/welcome";

    @GetMapping(value = GAME_URL)
    public String init(HttpSession session) {
        session.setAttribute(INFO_ATTRIBUTE, new InfoSession());
        return GAME_WELCOME_VIEW;
    }

    @GetMapping(value = GAME_AS_GUEST_URL)
    public String initAsGuest(HttpSession session) {
        session.setAttribute(INFO_ATTRIBUTE, new InfoSession());
        return GAME_WELCOME_VIEW;
    }
}