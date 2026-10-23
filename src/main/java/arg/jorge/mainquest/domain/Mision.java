package arg.jorge.mainquest.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "MISIONES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
public abstract class Mision extends Persistible {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "XP")
    private int XP;

    @Column(name = "ELIMINADA")
    private boolean eliminada = false;

    // Solo para hibernate
    protected Mision() { }

    public Mision(String nombre, int XP) {
        setNombre(nombre);
        setXP(XP);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (ValidationUtils.isEmpty(nombre)) {
            throw new IllegalArgumentException("El nombre de la misión es obligatorio");
        }
        this.nombre = nombre;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        if (XP <= 0) {
            throw new IllegalArgumentException("La misión debe otorgar XP positiva");
        }
        this.XP = XP;
    }

    public boolean isEliminada() {
        return eliminada;
    }

    public void setEliminada(boolean eliminada) {
        this.eliminada = eliminada;
    }

    /** La uso en la vista para decidir qué mostrar. */
    public boolean estaActiva() {
        return !eliminada;
    }

    public boolean esSemanal() {
        return this instanceof MisionSemanal;
    }


    /**
     * Calcula la XP final que otorga la misión.
     * Cada tipo de misión lo implementa a su manera.
     * @return la XP final de la misión
     */
    public abstract int calcularXpFinal();
}

