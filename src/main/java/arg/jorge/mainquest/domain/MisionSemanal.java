package arg.jorge.mainquest.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SEMANAL")
public class MisionSemanal extends Mision{
    // Solo para hibernate
    protected MisionSemanal() { }

    public MisionSemanal(String nombre, int XP){
        super(nombre, XP);
    }

    @Override
    public int calcularXpFinal(){
        return getXP() * 2;
    }
}