package arg.jorge.mainquest.forms;

import arg.jorge.mainquest.domain.ValidationUtils;

public abstract class EntityForm {
    private Long id;

    public boolean esCreacion(){
        return !ValidationUtils.isValidID(this.id);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
