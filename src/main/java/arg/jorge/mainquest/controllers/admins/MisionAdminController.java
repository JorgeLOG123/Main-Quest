package arg.jorge.mainquest.controllers.admins;

import arg.jorge.mainquest.Enums.MisionType;
import arg.jorge.mainquest.forms.DeletionForm;
import arg.jorge.mainquest.forms.MisionForm;
import arg.jorge.mainquest.services.MisionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MisionAdminController extends AdminController {
    public static final String CHOOSE_MISIONES_URL = ADMIN_URL + "/mision/choose";
    public static final String EDIT_MISIONES_URL   = ADMIN_URL + "/mision/edit/{type}";
    public static final String SAVE_MISIONES_URL   = ADMIN_URL + "/mision/save/{type}";

    private static final String CHOOSE_MISIONES_VIEW = ADMIN_VIEW + "/misiones-selector";
    private static final String FORM_MISIONES_VIEW   = ADMIN_VIEW + "/mision-form";

    private static final String TIPOS_MISIONES_KEY = "misiones_type";
    public static final String LIST_MISIONES_URL   = ADMIN_URL + "/mision/list";
    public static final String DELETE_MISIONES_URL = ADMIN_URL + "/mision/delete";
    protected static final String LIST_MISIONES_VIEW = ADMIN_VIEW + "/misiones-list";

    @Autowired
    private MisionServices misionServices;

    @GetMapping(value = CHOOSE_MISIONES_URL)
    public String choose(Model model) {
        model.addAttribute(TIPOS_MISIONES_KEY, MisionType.values());
        return CHOOSE_MISIONES_VIEW;
    }

    @GetMapping(value = EDIT_MISIONES_URL)
    public String edit(@PathVariable("type") MisionType type, Model model) {
        model.addAttribute(FORM_ATTRIBUTE, new MisionForm(type));
        return FORM_MISIONES_VIEW;
    }

    @PostMapping(value = SAVE_MISIONES_URL)
    public String save(@PathVariable("type") MisionType type,
                       @ModelAttribute(FORM_ATTRIBUTE) MisionForm form,
                       BindingResult results) {
        form.setType(type);

        try {
            this.misionServices.crear(form);
        } catch (IllegalArgumentException ex) {
            results.reject("mision.error", ex.getMessage());
            return FORM_MISIONES_VIEW;
        }

        return redirect(DashboardController.DASHBOARD_URL);


    }

    @GetMapping(LIST_MISIONES_URL)
    public String list(Model model) {
        model.addAttribute(LIST_ATTRIBUTE, misionServices.listarmisiones());
        return LIST_MISIONES_VIEW;
    }

    @PostMapping(DELETE_MISIONES_URL)
    public String delete(DeletionForm form) {
        misionServices.eliminar(form.getId());
        return redirect(LIST_MISIONES_URL);
    }

}