package arg.jorge.mainquest.repositories;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.domain.Mision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MisionRepository extends JpaRepository<Mision, Long> {

    List<Mision> findByNombre(String nombre);

    long countByEliminadaFalse();


}
