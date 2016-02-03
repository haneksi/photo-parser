package ru.photoparser.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.photoparser.entity.Portfolio;

import java.util.List;

@Repository
public class PortfolioDaoImpl implements AbstractDao<Portfolio>{

//    @Autowired
//    private SessionFactory sessionFactory;

    @Override
    public Portfolio getById(Long id) {
        return null;
    }

    @Override
    public List<Portfolio> findAll() {
        return null;
    }

    @Override
    public Portfolio create(Portfolio entyty) {
        return null;
    }

    @Override
    public Portfolio update(Portfolio entity) {
        return null;
    }

    @Override
    public void delete(Portfolio entity) {

    }
}
