package ru.photoparser.parse.impl;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.parse.AbstractParserImpl;
import ru.photoparser.util.ParserManagement;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service("afistfullofboltsParser")
@Scope("singleton")
public class AfistfullofboltsParser extends AbstractParserImpl{
    public AfistfullofboltsParser() {
    }

    @Override
    public Portfolio parsing() {
        Elements albumsElements = getDocument().select("div#projectPages").get(0).select("div[data-url]");

        if(notNull(albumsElements)){
            for (Element albumElement : albumsElements) {
                String albumUrl = "http://www.afistfullofbolts.com" + albumElement.attr("data-url");
                String title = albumElement.select("h2.project-title").text();

                if (notNullAndNotIsEmpty(albumUrl,title)) {
                    Album album = new Album(albumUrl, getAuthor(), title, getPortfolio());
                    album.setImages(getImagesToAlbum(album));
                    getAlbumsList().add(album);
                }
            }
        }

        getPortfolio().setAlbums(getAlbumsList());
        return getPortfolio();
    }

    @Override
    protected List<Image> getImagesToAlbum(Album album) {

        setImagesList(new ArrayList<Image>());
        setDocument(ParserManagement.getDocument(album.getUrl()));

        if(notNull(getDocument())){
            Elements imagesElements = getDocument().select("div.image-list").get(0)
                                                   .select("img[data-src]");
            addImagesToAlbum(imagesElements, "data-src","width","height","alt",album);
        }

        return getImagesList();
    }

    @PostConstruct
    @Override
    protected void init() {
        setURL("http://www.afistfullofbolts.com/galleries/");
        super.init();
    }
}
