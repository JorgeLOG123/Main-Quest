package arg.jorge.mainquest.services;

import arg.jorge.mainquest.domain.Exponible;
import arg.jorge.mainquest.dtos.DTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ExponibleServiceImpl<T extends Exponible, R extends JpaRepository<T, Long>>
        extends PersistableServiceImpl<T, R>
        implements ExponibleService {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public List<DTO> list() {
        return repository().findAll().stream()
                .map(entidad -> mapper.map(entidad, entidad.dtoForList()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTO> exponer() {
        return repository().findAll().stream()
                .map(entidad -> mapper.map(entidad, entidad.dtoForAPI()))
                .collect(Collectors.toList());
    }
}