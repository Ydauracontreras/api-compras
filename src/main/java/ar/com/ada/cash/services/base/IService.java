package ar.com.ada.cash.services.base;

import java.util.List;

public interface IService<T> {
    // crear
    boolean create(T entity);

    // actualizar
    T update(T entity);

    // buscarporId
    T finById(Integer id);

    // buscartodos
    List<T> findAll();

    // eliminar
    boolean delete(T entity);

    boolean detete(Integer id);

    Long count();

}
