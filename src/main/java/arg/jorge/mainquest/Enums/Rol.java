package arg.jorge.mainquest.Enums;

public enum Rol {
    // ⚠️ NO cambiar el orden ni insertar en el medio: el ordinal se guarda en la base (ROLES.ID).
    //    Constantes nuevas, siempre al final.
    ADMINISTRADOR(new Permiso[] {Permiso.ADMINISTRAR_MISIONES, Permiso.ADMINISTRAR_USUARIOS}),
    JUGADOR(new Permiso[] {Permiso.COMPLETAR_MISIONES});

    private Permiso[] permisos;

    private Rol(Permiso[] permisos) {
        this.permisos = permisos;
    }

    public Permiso[] getPermisos() {
        return this.permisos.clone();
    }
}
