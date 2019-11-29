package repository;

import java.util.List;

public interface Repository<T>{
    boolean create(T entity);

    boolean update(int id);

    boolean delete(int id);

    T getById(int id);

    List<T> getAll();
}