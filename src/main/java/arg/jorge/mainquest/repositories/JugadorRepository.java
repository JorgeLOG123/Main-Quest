package arg.jorge.mainquest.repositories;

import arg.jorge.mainquest.domain.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    List<Jugador> findByNombre(String nombre);
    List<Jugador> findByEmail(String email);
    List<Jugador> findByEmailAndIdNot(String email, Long id);
    List<Jugador> findByNombreAndIdNot(String nombre, Long id);

}
