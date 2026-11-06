package arg.jorge.mainquest.controllers.admins;

import arg.jorge.mainquest.controllers.BaseWebController;

public abstract class AdminWebController extends BaseWebController {
    public static final String ADMIN_URL = BASE_URL + "/admin";
    protected static final String ADMIN_VIEW = "adminusers";
}