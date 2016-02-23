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

@Service("ryanbrenizerParser")
@Scope("singleton")
public class RyanbrenizerParser extends AbstractParserImpl{
    public RyanbrenizerParser() {
    }

    @Override
    public Portfolio parsing() {
        Elements albumsElements = getDocument().select("div.text-wrap");

        if(notNull(albumsElements)){
            for (Element albumElement : albumsElements) {
                String albumUrl = albumElement.select("a[href]").get(0)
                                              .attr("href");
                String title = albumElement.select("a[href]").get(0)
                                           .text();

                if (!title.startsWith("permalink to Private")) {
                    if (notNullAndNotIsEmpty(albumUrl,title)) {
                        Album album = new Album(albumUrl, getAuthor(), title, getPortfolio());
                        album.setImages(getImagesToAlbum(album));
                        getAlbumsList().add(album);
                    }
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

    @PostConstruct
    @Override
    protected void init() {
        setURL("http://ryanbrenizer.com/weddings/");
        super.init();
    }
}
