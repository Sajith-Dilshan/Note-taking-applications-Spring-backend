package lk.ijse.dep8.note.repostiory;

import lk.ijse.dep8.note.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends SuperEntity, ID extends Serializable> extends SuperRepository{

    T save(T entity);

    void deleteById(ID pk);

    boolean existsById(ID pk);

    Optional<T> findById(ID pk);

    List<T> findAll();

    long count();
}
