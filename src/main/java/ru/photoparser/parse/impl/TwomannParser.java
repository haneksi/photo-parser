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
import java.util.List;

@Service("twomannParser")
@Scope("singleton")
public class TwomannParser extends AbstractParserImpl {

    public TwomannParser() {
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

        setDocument(ParserManagement.getDocument(album.getUrl()));

        if (notNull(getDocument())) {
            Elements imagesElements = getDocument().select("div.entry-content").get(0)
                                                   .getElementsByAttribute("data-src1320");

            if (notNull(imagesElements)) {
                for (Element imageElement : imagesElements) {
                    String imageUrl = imageElement.attr("data-src1320");
                    String title = imageElement.attr("title");
                    String alt = imageElement.attr("alt");

                    if (notNullAndNotIsEmpty(imageUrl)) {
                        getImagesList().add(new Image(imageUrl, getAuthor(), "", "", "", getPortfolio(), album));
                    }
                }
            }
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
