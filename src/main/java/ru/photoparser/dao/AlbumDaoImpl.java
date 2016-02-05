package ru.photoparser.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.photoparser.entity.Album;

import java.util.List;

@Repository
public class AlbumDaoImpl implements AbstractDao <Album> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Album getById(Long id) {
        return null;
    }

    @Override
    public List<Album> findAll() {
        return null;
    }

    @Override
    public void create(Album entyty) {

    }

    @Override
    public void update(Album entity) {

    }

    @Override
    public void delete(Album entity) {

    }
}
