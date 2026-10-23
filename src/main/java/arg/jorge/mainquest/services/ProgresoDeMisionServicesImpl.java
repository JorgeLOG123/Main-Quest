package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.domain.Mision;
import arg.jorge.mainquest.domain.ProgresoDeMision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import arg.jorge.mainquest.repositories.ProgresoDeMisionRepository;

import java.util.List;

@Service
public class ProgresoDeMisionServicesImpl implements ProgresoDeMisionServices {

    @Autowired
    private ProgresoDeMisionRepository repositorio;

    @Override
    public ProgresoDeMision asignarMision(Jugador jugador, Mision mision) {
        ProgresoDeMision progreso = new ProgresoDeMision(jugador, mision);
        return this.repositorio.save(progreso);
    }

    @Override
    public ProgresoDeMision completarMision(Long id) {
        ProgresoDeMision progreso = buscarPorId(id);
        progreso.completar();
        return this.repositorio.save(progreso);
    }

    @Override
    public ProgresoDeMision buscarPorId(Long id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un progreso con ese id"));
    }

    @Override
    public List<ProgresoDeMision> listarPorJugador(Jugador jugador) {
        return this.repositorio.findByJugador(jugador);
    }

    @Override
    public List<ProgresoDeMision> listarTodos() {
        return this.repositorio.findAll();
    }

    @Override
    public void eliminar(Long id) {
        buscarPorId(id);
        this.repositorio.deleteById(id);
    }
}