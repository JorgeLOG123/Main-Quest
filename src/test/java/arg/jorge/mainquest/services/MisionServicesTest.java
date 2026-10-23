package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Mision;
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
public class MisionServicesTest {

    @Autowired
    private MisionServices servicio;

    @Test
    public void guardarMisionCorrectamente() {
        Mision mision = EntityHelper.crearMisionValida();

        this.servicio.guardar(mision);

        List<Mision> misiones = this.servicio.listarmisiones();
        assertEquals(1, misiones.size());
        assertEquals(mision.getNombre(), misiones.get(0).getNombre());
    }
}