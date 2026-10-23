package arg.jorge.mainquest.Validators;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.exceptions.MultipleJugadoresFoundException;
import arg.jorge.mainquest.forms.RegistracionForm;
import arg.jorge.mainquest.services.JugadorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistracionFormValidator implements Validator {

    @Autowired
    private JugadorServices servicio;

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistracionForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistracionForm form = (RegistracionForm) target;

        // Nombre
        if (form.getNombre() == null || form.getNombre().isBlank()) {
            errors.rejectValue("nombre", "nombre.empty");
        } else if (this.servicio.existePorNombre(form.getNombre())) {
            errors.rejectValue("nombre", "nombre.taken");
        }

        // Email
        if (form.getEmail() == null || form.getEmail().isBlank()) {
            errors.rejectValue("email", "email.empty");
        } else {
            try {
                if (this.servicio.obtenerPorEmail(form.getEmail()) != null) {
                    errors.rejectValue("email", "email.taken");
                }
            } catch (MultipleJugadoresFoundException ex) {
                errors.rejectValue("email", "email.taken");
            }
        }

        // Contraseña
        if (form.getContrasena() == null || form.getContrasena().isBlank()) {
            errors.rejectValue("contrasena", "contrasena.empty");
        } else if (form.getContrasena().length() < Jugador.CONTRASENA_MIN_LENGTH) {
            errors.rejectValue("contrasena", "contrasena.short",
                    new Integer[] { Jugador.CONTRASENA_MIN_LENGTH }, null);
        }
    }
    public int getEmailMaxlength(){
        return Jugador.EMAIL_MAXLEGTH;
    }
    public int getNombreMaxLength()
    { return Jugador.NOMBRE_MAX_LENGTH; }

    public int getContrasenaMinLength()
    { return Jugador.CONTRASENA_MIN_LENGTH; }
}

