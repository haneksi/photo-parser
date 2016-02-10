package ru.photoparser.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.photoparser.dao.PortfolioDaoImpl;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Portfolio;

import java.util.List;

@Service("portfolioService")
public class PortfolioService {

    @Qualifier("portfolioDaoImpl")
    @Autowired
    private PortfolioDaoImpl portfolioDao;

    @Transactional
    public void create(Portfolio portfolio) {
        portfolioDao.create(portfolio);
    }

    @Transactional
    public void delete(Portfolio portfolio) {
        portfolioDao.delete(portfolio);;
    }

    @Transactional
    public void update(Portfolio portfolio) {
        portfolioDao.update(portfolio);
    }

    @Transactional
    public List<Portfolio> getAll() {
        List<Portfolio> listPortfolio = portfolioDao.findAll();
        for (Portfolio portfolio : listPortfolio) {
            List<Album> albums = portfolio.getAlbums();
            Hibernate.initialize(albums);
            for (Album album : albums) {
                Hibernate.initialize(album.getImages());
            }
        }
        return listPortfolio;
    }

    @Transactional
    public Portfolio getById(Integer portfolioId) {
        Portfolio portfolio = portfolioDao.getById(portfolioId);
        List<Album> albums = portfolio.getAlbums();

        Hibernate.initialize(albums);
        for (Album album : albums) {
            Hibernate.initialize(album.getImages());
        }
        return portfolio;
    }

}
