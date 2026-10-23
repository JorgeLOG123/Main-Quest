
package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.domain.Mision;
import arg.jorge.mainquest.domain.ProgresoDeMision;

import java.util.List;

public interface ProgresoDeMisionServices {

    ProgresoDeMision asignarMision(Jugador jugador, Mision mision);

    ProgresoDeMision completarMision(Long id);

    ProgresoDeMision buscarPorId(Long id);

    List<ProgresoDeMision> listarPorJugador(Jugador jugador);

    List<ProgresoDeMision> listarTodos();

    void eliminar(Long id);


}




