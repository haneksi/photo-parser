package ru.photoparser.dao;


import java.util.List;

public interface AbstractDao<T> {

    T getById(Integer id);

    List<T> findAll();

    void create(T entyty);

    void update(T entity);

    void delete(T entity);

}
