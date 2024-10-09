package integrador.app.repositories;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface RepoBase<T,ID extends Serializable> extends org.springframework.data.repository.Repository<T,ID> {
    void delete( T deleted);

    List<T> findAll();

    Optional<T> findById(Long id);

    boolean existsById(Long id);

    T save( T persisted);
}