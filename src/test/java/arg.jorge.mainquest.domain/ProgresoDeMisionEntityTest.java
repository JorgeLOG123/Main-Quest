package arg.jorge.mainquest.domain;

import arg.jorge.mainquest.Enums.EstadoMision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProgresoDeMisionEntityTest {
    private Jugador crearJugadorValido() {
        return new Jugador("Jorge", "pass1234", "jorge@mail.com");
    }

    private Mision crearMisionValida() {
        return new MisionGeneral("Derrotar al jefe final", 100);
    }

    @Test
    public void testCrearProgresoExitosamente() {
        Jugador jugador = crearJugadorValido();
        Mision mision = crearMisionValida();

        ProgresoDeMision progreso = new ProgresoDeMision(jugador, mision);

        assertEquals(jugador, progreso.getJugador());
        assertEquals(mision, progreso.getMision());
        assertEquals(EstadoMision.EN_CURSO, progreso.getEstado());
        assertFalse(progreso.estaCompleta());
    }

    @Test
    public void testProgresoConJugadorNuloLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgresoDeMision(null, crearMisionValida());
        });
    }

    @Test
    public void testProgresoConMisionNulaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgresoDeMision(crearJugadorValido(), null);
        });
    }

    @Test
    public void testCompletarMisionCambiaElEstado() {
        ProgresoDeMision progreso = new ProgresoDeMision(crearJugadorValido(), crearMisionValida());

        progreso.completar();

        assertEquals(EstadoMision.COMPLETADA, progreso.getEstado());
        assertTrue(progreso.estaCompleta());
    }

    @Test
    public void testCompletarDosVecesLanzaExcepcion() {
        ProgresoDeMision progreso = new ProgresoDeMision(crearJugadorValido(), crearMisionValida());
        progreso.completar();

        assertThrows(IllegalStateException.class, () -> {
            progreso.completar();
        });
    }
}