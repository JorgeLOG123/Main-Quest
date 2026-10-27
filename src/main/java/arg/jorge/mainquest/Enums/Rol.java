package arg.jorge.mainquest.Enums;

public enum Rol {
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
