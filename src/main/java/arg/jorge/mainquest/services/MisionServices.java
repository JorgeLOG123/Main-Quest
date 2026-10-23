package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Mision;
import arg.jorge.mainquest.forms.MisionForm;

import java.util.List;

public interface MisionServices extends PersistableService {

    void guardar(Mision mision);

    void eliminar(Long id);

    Mision modificar(Mision mision);

    List<Mision> listarmisiones();

    Mision buscarmisionporid(Long id);

    void crear(MisionForm form);
}