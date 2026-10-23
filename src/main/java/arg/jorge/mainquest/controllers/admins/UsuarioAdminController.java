package arg.jorge.mainquest.controllers.admins;

import arg.jorge.mainquest.Validators.JugadorAdminFormValidator;
import arg.jorge.mainquest.controllers.BaseController;
import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.forms.DeletionForm;
import arg.jorge.mainquest.forms.JugadorAdminForm;
import arg.jorge.mainquest.services.JugadorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsuarioAdminController extends BaseController {

    public static final String LIST_USERS_URL = "/admin/user/list";
    public static final String EDIT_USERS_URL = "/admin/user/edit";
    public static final String SAVE_USERS_URL = "/admin/user/save";
    public static final String ID_PARAMETER = "id";
    protected static final String LIST_USERS_VIEW = "adminusers/list";
    protected static final String FORM_USERS_VIEW = "adminusers/form";
    public static final String DELETE_USERS_URL = "/admin/user/delete";
    @Autowired
    private JugadorServices services;

    @Autowired
    private JugadorAdminFormValidator validator;

    @InitBinder(value = FORM_ATTRIBUTE)
    void initFormValidator(WebDataBinder binder) {
        binder.addValidators(this.validator);
    }

    @GetMapping(value = LIST_USERS_URL)
    public String init(Model model) {
        List<Jugador> jugadores = this.services.listartodos();
        model.addAttribute(LIST_ATTRIBUTE, jugadores);
        return LIST_USERS_VIEW;
    }

    @GetMapping(value = EDIT_USERS_URL)
    public String edit(Model model,
                       @RequestParam(name = ID_PARAMETER, defaultValue = "-1") Long id) {

        model.addAttribute(FORM_ATTRIBUTE, this.services.prepareEntity(id));

        return FORM_USERS_VIEW;
    }

    @PostMapping(value = SAVE_USERS_URL)
    public String save(@Validated @ModelAttribute(FORM_ATTRIBUTE) JugadorAdminForm formulario,
                       BindingResult results) {

        if (results.hasErrors()) return FORM_USERS_VIEW;

        if (formulario.esCreacion()) {
            this.services.registrar(formulario.getNombre(),
                    formulario.getContrasena(),
                    formulario.getEmail());
        } else {
            this.services.actualizar(formulario);
        }

        return redirect(LIST_USERS_URL);

    }

    @PostMapping(value = DELETE_USERS_URL)
    public String delete(DeletionForm form) {

        this.services.eliminar(form);

        return redirect(LIST_USERS_URL);
    }

}