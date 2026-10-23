package arg.jorge.mainquest.controllers;


import arg.jorge.mainquest.Validators.RegistracionFormValidator;
import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.dtos.DTO;
import arg.jorge.mainquest.dtos.JugadorDTO;
import arg.jorge.mainquest.forms.RegistracionForm;
import arg.jorge.mainquest.services.JugadorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;                       // ← NUEVO

@RestController
public class JugadorAPI {
    public static final String LIST_JUGADORES_URL = "/api/jugadores";
    public static final String SAVE_JUGADORES_URL = "/api/jugadores/registro";

    @Autowired
    private JugadorServices servicio;

    @Autowired
    private RegistracionFormValidator validator;

    @GetMapping(value = LIST_JUGADORES_URL,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DTO>> list() {
        List<DTO> jugadores = this.servicio.exponer();

        return ResponseEntity.ok(jugadores);
    }

    @PostMapping(value = SAVE_JUGADORES_URL,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registrar(@RequestBody RegistracionForm form) {
        ResponseEntity<String> respuesta = null;

        Errors errors = this.validator.validateObject(form);
        if (errors.hasErrors()) {
            String detalle = errors.getFieldErrors().stream()                        // ← NUEVO
                    .map(error -> error.getField() + ": " + error.getCode())
                    .collect(Collectors.joining(", "));

            respuesta = ResponseEntity.badRequest()
                    .body("No se pudo registrar el jugador -> " + detalle);         // ← NUEVO
        } else {
            this.servicio.registrar(form.getNombre(), form.getContrasena(), form.getEmail());
            respuesta = ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return respuesta;




    }
}