package ru.photoparser.dao;

import org.hibernate.Session;
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
        Session currentSession = sessionFactory.getCurrentSession();
        Album album = currentSession.load(Album.class, id);
        return album;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Album> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Album> list = currentSession.createQuery("from Album").list();
        return list;
    }

    @Override
    public void create(Album entyty) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.persist(entyty);
    }

    @Override
    public void update(Album entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(entity);
    }

    @Override
    public void delete(Album entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(entity);
    }
}
