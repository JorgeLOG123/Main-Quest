package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Imagen;
import arg.jorge.mainquest.domain.ValidationUtils;
import arg.jorge.mainquest.repositories.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenServiceImpl implements ImagenService {

    @Autowired
    private ImagenRepository repositorio;

    @Override
    public Imagen obtener(Long id) {
        if (!ValidationUtils.isValidID(id)) return null;
        return this.repositorio.findById(id).orElse(null);
    }
}