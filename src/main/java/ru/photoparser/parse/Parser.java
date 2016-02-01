package ru.photoparser.parse;


import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.entity.Portfolio;

import java.util.List;

public interface Parser {

    Portfolio getPortfolio();
    List<Image> getAllImages();
    List<Album> getAlbums();
    List<Image> getImagesToAlbum(Album album);

}
