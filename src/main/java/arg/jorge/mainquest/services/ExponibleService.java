package arg.jorge.mainquest.services;

import arg.jorge.mainquest.dtos.DTO;

import java.util.List;

public interface ExponibleService extends PersistableService {

    List<DTO> list();

    List<DTO> exponer();
}