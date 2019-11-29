package services;

import java.util.List;

public interface Service<T> {
    boolean create(T entity);

    boolean update(T entity);

    boolean delete(T entity);

    boolean delete(int id);

    T getById(int id);

    List<T> getAll();
}
