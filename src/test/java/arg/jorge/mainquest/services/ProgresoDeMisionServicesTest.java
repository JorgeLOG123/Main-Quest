package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.domain.Mision;
import arg.jorge.mainquest.domain.ProgresoDeMision;
import arg.jorge.mainquest.utilities.EntityHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ProgresoDeMisionServicesTest {

    @Autowired
    private ProgresoDeMisionServices servicio;

    @Autowired
    private JugadorServices jugadorServicio;

    @Autowired
    private MisionServices misionServicio;

    @Test
    public void guardarProgresoCorrectamente() {
        Jugador jugador = EntityHelper.crearJugadorValido();
        this.jugadorServicio.guardar(jugador);

        Mision mision = EntityHelper.crearMisionValida();
        this.misionServicio.guardar(mision);

        ProgresoDeMision progreso = this.servicio.asignarMision(jugador, mision);

        List<ProgresoDeMision> progresos = this.servicio.listarTodos();
        assertEquals(1, progresos.size());
        assertEquals(progreso.getEstado(), progresos.get(0).getEstado());
    }
}