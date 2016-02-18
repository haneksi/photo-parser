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


@Service("edpeersParser")
@Scope("singleton")
public class EdpeersParser extends AbstractParserImpl {

    public EdpeersParser() {
    }


    @Override
    public Portfolio parsing(){
        Elements albumsElments = getDocument().select("span.read-more-wrap")
                                              .select("a[href]");

        if (notNull(albumsElments)) {
            for (Element albumElement : albumsElments ){

                String albumUrl = albumElement.attr("href");
                String title = albumElement.attr("title");

                if (notNullAndNotIsEmpty(albumUrl, title)) {
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
        return getImagesToAlbum(album, getPortfolio(), getAuthor());
    }

    @PostConstruct
    @Override
    protected void init() {
        setURL("http://www.edpeers.com");
        super.init();
    }

}
