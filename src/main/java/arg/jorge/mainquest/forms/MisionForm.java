package arg.jorge.mainquest.forms;

import arg.jorge.mainquest.Enums.MisionType;

public class MisionForm extends EntityForm {

    private MisionType type;
    private String nombre;
    private Integer xp;

    public MisionForm() {
        super();
    }

    public MisionForm(MisionType type) {
        this.type = type;
    }

    public boolean esGeneral() {
        return MisionType.GENERAL.equals(type);
    }

    public boolean esSemanal() {
        return MisionType.SEMANAL.equals(type);
    }

    public MisionType getType() { return type; }
    public void setType(MisionType type) { this.type = type; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getXp() { return xp; }
    public void setXp(Integer xp) { this.xp = xp; }
}