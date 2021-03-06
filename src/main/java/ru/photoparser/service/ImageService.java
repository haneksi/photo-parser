package ru.photoparser.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.photoparser.dao.ImageDaoImpl;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;

import java.util.List;

@Service("imageService")
public class ImageService {

    @Qualifier("imageDaoImpl")
    @Autowired
    private ImageDaoImpl imageDao;

    @Transactional
    public void create(Image image) {
        imageDao.create(image);
    }

    @Transactional
    public void delete(Image image) {
        imageDao.delete(image);;
    }

    @Transactional
    public void update(Image image) {
        imageDao.update(image);
    }

    @Transactional
    public List<Image> getAll() {
        return imageDao.findAll();
    }

    @Transactional
    public Image getById(Integer imageId) {
        return imageDao.getById(imageId);
    }
}
