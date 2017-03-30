package dao.api;

import java.util.List;

public interface Dao<T> {

    List<T> getAll();

    T getById(int key);

    void save(T entity);

    void delete(T entity);

}
