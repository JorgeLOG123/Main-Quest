package arg.jorge.mainquest.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GENERAL")
public class MisionGeneral extends Mision {
    // Solo para hibernate
    protected MisionGeneral() { }

    public MisionGeneral(String nombre, int XP){
        super(nombre, XP);
    }

    @Override
    public int calcularXpFinal() {
        return getXP();   // la XP tal cual, sin bonus
    }

}