package repository;

import java.util.List;

public interface CrudDTO<T> {
    List<T> list();
    T get();
    void create();
    void update();
    void delete();
}