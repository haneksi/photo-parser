package ru.photoparser.dao;

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
    public Image getById(Long id) {
        return null;
    }

    @Override
    public List<Image> findAll() {
        return null;
    }

    @Override
    public void create(Image entyty) {

    }

    @Override
    public void update(Image entity) {

    }

    @Override
    public void delete(Image entity) {

    }
}
