package arg.jorge.mainquest.Enums;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum Rol {
        // ⚠️ NO cambiar el orden ni insertar en el medio: el ordinal se guarda en la base (ROLES.ID).
        //    Constantes nuevas, siempre al final.
        ADMINISTRADOR(new Permiso[] {Permiso.ADMINISTRAR_MISIONES, Permiso.ADMINISTRAR_USUARIOS}),
        JUGADOR(new Permiso[] {Permiso.COMPLETAR_MISIONES}),
        ARBITRO(new Permiso[] {Permiso.ADMINISTRAR_MISIONES});

        private Permiso[] permisos;

        private Rol(Permiso[] permisos) {
            this.permisos = permisos;
        }

        public Permiso[] getPermisos() {
            return this.permisos.clone();
        }

        public String getSecurityName() {
            return "ROLE_" + name();
        }

        public Collection<GrantedAuthority> getSecurityPermissions() {
            List<GrantedAuthority> permissions = new ArrayList<GrantedAuthority>();

            for (Permiso permiso : getPermisos()) {
                permissions.add(new SimpleGrantedAuthority(permiso.name()));
            }

            return permissions;
        }
    }

