package arg.jorge.mainquest.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MisionEntityTest {

    @Test
    public void testMisionGeneralAgregadaExitosamente(){
        MisionGeneral  misionGeneral= new MisionGeneral("NOMBRE DE MISION GENERAL", 25);
        assertEquals("NOMBRE DE MISION GENERAL", misionGeneral.getNombre());
        assertEquals(25, misionGeneral.getXP());

    }
    @Test
    public void testMisionSemanalAgregadaExitosamente(){
        MisionSemanal misionSemanal = new MisionSemanal("NOMBRE DE MISION SEMANAL", 45);
        assertEquals("NOMBRE DE MISION SEMANAL", misionSemanal.getNombre());
        assertEquals(45, misionSemanal.getXP());
    }

    @Test
    public void testMisionConNombreNuloLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MisionGeneral(null, 100);
        });
    }

    @Test
    public void testMisionConNombreVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MisionGeneral("   ", 100);
        });
    }

    @Test
    public void testMisionConXpCeroLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MisionGeneral("Mision sin recompensa", 0);
        });
    }

    @Test
    public void testMisionConXpNegativaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MisionGeneral("Mision rota", -50);
        });
    }

    @Test
    public void testMisionGeneralNoAplicaBonus() {
        MisionGeneral mision = new MisionGeneral("Explorar el mapa", 80);

        assertEquals(80, mision.calcularXpFinal());
    }

    @Test
    public void testMisionSemanalDuplicaLaXp() {
        MisionSemanal mision = new MisionSemanal("Completar 5 misiones", 80);

        assertEquals(160, mision.calcularXpFinal());
    }
}



