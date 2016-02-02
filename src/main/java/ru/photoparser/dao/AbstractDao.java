package ru.photoparser.dao;


import java.util.List;

public interface AbstractDao<T> {

    T getById(Long id);

    List<T> findAll();

    T create(T entyty);

    T update(T entity);

    void delete(T entity);

}
