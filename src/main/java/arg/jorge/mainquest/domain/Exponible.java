package arg.jorge.mainquest.domain;

import arg.jorge.mainquest.dtos.DTO;

public interface Exponible {

    Class<? extends DTO> dtoForList();

    Class<? extends DTO> dtoForAPI();
}