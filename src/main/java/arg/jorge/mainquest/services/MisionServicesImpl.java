package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Mision;
import arg.jorge.mainquest.domain.MisionGeneral;
import arg.jorge.mainquest.domain.MisionSemanal;
import arg.jorge.mainquest.domain.ValidationUtils;
import arg.jorge.mainquest.forms.MisionForm;
import arg.jorge.mainquest.repositories.MisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MisionServicesImpl extends PersistableServiceImpl<Mision, MisionRepository>
        implements MisionServices {

    @Autowired
    private MisionRepository repositorio;

    @Override
    public void guardar(Mision mision) {
        this.repositorio.save(mision);
    }

    @Override
    public void eliminar(Long id) {
        if (!ValidationUtils.isValidID(id)) return;

        Mision mision = buscarmisionporid(id);
        mision.setEliminada(true);
        this.repositorio.save(mision);
    }

    @Override
    public Mision modificar(Mision mision) {
        buscarmisionporid(mision.getId());
        return this.repositorio.save(mision);
    }

    @Override
    public List<Mision> listarmisiones() {
        return this.repositorio.findAll();
    }

    @Override
    public Mision buscarmisionporid(Long id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe la mision"));
    }


    @Override
    public void crear(MisionForm form) {
        String nombre = form.getNombre();
        int xp = (form.getXp() == null) ? 0 : form.getXp();

        Mision mision;
        if (form.esSemanal()) {
            mision = new MisionSemanal(nombre, xp);
        } else {
            mision = new MisionGeneral(nombre, xp);
        }

        this.guardar(mision);
    }

    @Override
    public long cantidadEntidades() {
        return this.repositorio.countByEliminadaFalse();
    }


}