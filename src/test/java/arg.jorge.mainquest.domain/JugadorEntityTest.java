package arg.jorge.mainquest.domain;
import arg.jorge.mainquest.Enums.Rol;
import  arg.jorge.mainquest.domain.Jugador;
import org.junit.jupiter.api.Test;
import arg.jorge.mainquest.utilities.EntityHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JugadorEntityTest {

    @Test
    public void testCrearJugadorExitosamente() {
        Jugador jugador = EntityHelper.crearJugadorValido();

        assertEquals(EntityHelper.VALID_EMAIL, jugador.getEmail());
        assertNotNull(jugador.getNombre());
        assertNotNull(jugador.getContrasena());
    }
    @Test
    public void testCrearJugadorConEmailNuloLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Jugador("Jorge", "pass1234", null);
        });
    }


    @Test
    public void testCrearJugadorConEmailVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Jugador("Jorge", "pass1234", "   ");
        });
    }

    @Test
    public void testJugadorNuevoTieneRolJugador() {
        Jugador jugador = new Jugador("Jorge", "pass1234", "jorge@mail.com");

        assertEquals(List.of(Rol.JUGADOR), jugador.getRoles());
    }

    @Test
    public void testAdminNoTieneRolJugador() {
        Jugador admin = new Jugador("Admin", "pass1234", "admin@mail.com", true);

        assertEquals(List.of(Rol.ADMINISTRADOR), admin.getRoles());
    }




}