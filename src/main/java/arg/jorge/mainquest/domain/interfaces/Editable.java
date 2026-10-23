package arg.jorge.mainquest.domain.interfaces;

import arg.jorge.mainquest.forms.EntityForm;

public interface Editable<F extends EntityForm> {

    void copyPropertiesToForm(F form);

    void updatePropertiesFromForm(F form);


}