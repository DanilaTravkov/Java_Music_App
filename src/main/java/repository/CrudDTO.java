package repository;

import java.util.List;

public interface CrudDTO<T> {
    List<T> list();
    T get(int id);
    void create(T newModel);
    void update(T newModel, int id);
    void delete(int id);
}
