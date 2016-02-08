package ru.photoparser.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.photoparser.entity.Image;

import java.util.List;

@Repository
public class ImageDaoImpl implements AbstractDao<Image>{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Image getById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Image image = currentSession.get(Image.class, id);
        return image;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Image> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        List <Image> list = currentSession.createQuery("from Image").list();
        return list;
    }

    @Override
    public void create(Image entyty) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.persist(entyty);
    }

    @Override
    public void update(Image entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(entity);
    }

    @Override
    public void delete(Image entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(entity);
    }
}
