package ru.photoparser.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.photoparser.dao.AlbumDaoImpl;
import ru.photoparser.entity.Album;

import java.util.List;

@Service("albumService")
public class AlbumService {


    @Qualifier("albumDaoImpl")
    @Autowired
    private AlbumDaoImpl albumDao;

    @Transactional
    public void create(Album album) {
        albumDao.create(album);
    }

    @Transactional
    public void delete(Album album) {
        albumDao.delete(album);;
    }

    @Transactional
    public void update(Album album) {
        albumDao.update(album);
    }

    @Transactional
    public List<Album> getAll() {
        return albumDao.findAll();
    }

    @Transactional
    public Album getById(Long albumId) {
        return albumDao.getById(albumId);
    }
}
