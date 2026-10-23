package arg.jorge.mainquest.repositories;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.domain.ProgresoDeMision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgresoDeMisionRepository extends JpaRepository<ProgresoDeMision, Long> {

    List<ProgresoDeMision> findByJugador(Jugador jugador);
}