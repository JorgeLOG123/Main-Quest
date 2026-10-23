package arg.jorge.mainquest.services;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.services.JugadorServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import arg.jorge.mainquest.utilities.EntityHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class JugadorServicesTest {

    @Autowired
    private JugadorServices servicio;

    @Test
    public void guardarJugadorCorrectamente() {
        Jugador jugador = EntityHelper.crearJugadorValido();

        this.servicio.guardar(jugador);

        List<Jugador> jugadores = this.servicio.listartodos();
        assertEquals(1, jugadores.size());
        assertEquals(jugador.getNombre(), jugadores.get(0).getNombre());
    }
}