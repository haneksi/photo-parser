package ru.photoparser.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.photoparser.entity.Portfolio;

import java.util.List;

@Repository
public class PortfolioDaoImpl implements AbstractDao<Portfolio>{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Portfolio getById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Portfolio portfolio = currentSession.load(Portfolio.class, id);
        return portfolio;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Portfolio> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        List <Portfolio> list = currentSession.createQuery("from Portfolio").list();
        return list;
    }

    @Override
    public void create(Portfolio entyty) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.persist(entyty);
    }

    @Override
    public void update(Portfolio entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(entity);
    }

    @Override
    public void delete(Portfolio entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(entity);
    }
}
