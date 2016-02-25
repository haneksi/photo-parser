package ru.photoparser.parse.impl;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.parse.AbstractParserImpl;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("tylerbranchphotoParser")
@Scope("singleton")
public class TylerbranchphotoParser extends AbstractParserImpl{

    public TylerbranchphotoParser() {
        super();
    }

    @Override
    public Portfolio parsing() {
        Elements albumsElements = getDocument().select("div#content").get(0)
                                               .select("div.text-wrap");

        if(notNull(albumsElements)){
            for (Element albumElement : albumsElements) {
                String albumUrl = albumElement.select("a[href]").get(0)
                                              .attr("href");
                String title = albumElement.select("a[href]").get(0)
                                              .attr("title");
                if(notNullAndNotIsEmpty(albumUrl,title)){
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

        return getImagesToAlbum(album,getPortfolio(),getAuthor());
    }

    @Override
    @PostConstruct
    protected void init() {

        this.setURL("http://tylerbranchphoto.com/");
        super.init();

    }
}
