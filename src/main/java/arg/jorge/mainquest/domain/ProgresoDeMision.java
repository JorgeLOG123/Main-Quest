package arg.jorge.mainquest.domain;

import arg.jorge.mainquest.Enums.EstadoMision;
import jakarta.persistence.*;

@Entity
@Table(name = "progreso_mision")
public class ProgresoDeMision extends Persistible {

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_mision")
    private Mision mision;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoMision estado;

    // Solo para hibernate
    protected ProgresoDeMision() { }

    public ProgresoDeMision(Jugador jugador, Mision mision) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador es obligatorio");
        }
        if (mision == null) {
            throw new IllegalArgumentException("La misión es obligatoria");
        }
        this.jugador = jugador;
        this.mision = mision;
        this.estado = EstadoMision.EN_CURSO;
    }

    public void completar() {
        if (estado == EstadoMision.COMPLETADA) {
            throw new IllegalStateException("La misión ya está completada");
        }
        this.estado = EstadoMision.COMPLETADA;
    }

    public boolean estaCompleta() {
        return estado == EstadoMision.COMPLETADA;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Mision getMision() {
        return mision;
    }

    public EstadoMision getEstado() {
        return estado;
    }
}