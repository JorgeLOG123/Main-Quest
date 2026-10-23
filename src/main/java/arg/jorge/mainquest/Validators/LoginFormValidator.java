package arg.jorge.mainquest.Validators;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.exceptions.MultipleJugadoresFoundException;
import arg.jorge.mainquest.forms.LoginForm;
import arg.jorge.mainquest.services.JugadorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginFormValidator implements Validator {

    @Autowired
    private JugadorServices servicio;

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm form = (LoginForm) target;

        if (form.getEmail() == null || form.getEmail().isBlank()) {
            errors.rejectValue("email", "email.empty");
        } else {
            try {
                Jugador jugador = this.servicio.obtenerPorEmail(form.getEmail());
                if (jugador == null) errors.rejectValue("email", "email.not.exists");
            } catch (MultipleJugadoresFoundException ex) {
                errors.rejectValue("email", "email.multiple.found");
            }
        }
    }
}



