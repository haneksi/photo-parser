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

@Service("twomannParser")
@Scope("singleton")
public class TwomannParser extends AbstractParserImpl {

    public TwomannParser() {
        super();
    }

    @Override
    public Portfolio parsing() {
        Elements albumsElements = getDocument().select("div.entry-content").get(0)
                                               .select("li.thumbnail");

        if (notNull(albumsElements)) {
            for (Element albumElement : albumsElements) {

                String albumUrl = albumElement.select("a.thumbnail-glass").get(0)
                                              .attr("href");

                String title = albumElement.select("div.thumbnail-caption").get(0)
                                           .text();

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

        setImagesList(new ArrayList<Image>());
        setDocument(ParserManagement.getDocument(album.getUrl()));

        if (notNull(getDocument())) {
            Elements imagesElements = getDocument().select("div.entry-content").get(0)
                                                   .getElementsByAttribute("data-src1320");

            addImagesToAlbum(imagesElements, "data-src1320", "width", "height", "alt", album);
        }
        return getImagesList();
    }

    @Override
    @PostConstruct
    protected void init() {

        this.setURL("http://twomann.com/weddings/");
        super.init();

    }
}
