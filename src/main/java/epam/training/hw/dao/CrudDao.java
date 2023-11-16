package epam.training.hw.dao;

import java.util.List;

public interface CrudDao<E> {
     E findById(int id);
     List<E> findAll();
     void add(E entity);
     void update(E entity);
     void delete(int id);
}
