package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.domain.Mision;
import arg.jorge.mainquest.dtos.JugadorDTO;
import arg.jorge.mainquest.forms.DeletionForm;
import arg.jorge.mainquest.forms.JugadorAdminForm;
import arg.jorge.mainquest.forms.ProfileForm;

import java.util.List;

public interface JugadorServices extends ExponibleService {   // ← solo cambió esta línea

    public void guardar(Jugador jugador);

    Jugador modificar(Jugador jugador);

    void eliminar(Long id);

    Jugador buscarPorId(Long id);

    List<Jugador> listartodos();

    public void registrar(String nombre, String contrasena, String email);

    Jugador obtenerPorEmail(String email);

    boolean existePorNombre(String nombre);

    public JugadorAdminForm prepareEntity(Long id);

    Jugador obtenerPorEmail(String email, Long id);

    boolean existePorNombre(String nombre, Long id);

    void eliminar(DeletionForm form);

    ProfileForm prepareProfile(String email);

    void actualizarPerfil(ProfileForm form);

    void actualizar(JugadorAdminForm form);
}