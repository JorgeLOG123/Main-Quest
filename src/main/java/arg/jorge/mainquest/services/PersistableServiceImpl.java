package arg.jorge.mainquest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class PersistableServiceImpl<T, R extends JpaRepository<T, Long>>
        implements PersistableService {

    @Autowired
    private R repository;

    @Override
    public long cantidadEntidades() {
        return this.repository.count();
    }

    protected R repository() {
        return this.repository;
    }
}