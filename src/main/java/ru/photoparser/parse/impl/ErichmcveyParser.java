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

@Service("erichmcveyParser")
@Scope("singleton")
public class ErichmcveyParser extends AbstractParserImpl {

    public ErichmcveyParser() {
        super();
    }

    @Override
    public Portfolio parsing() {

        Elements albumsElements = getDocument().select("div#content a[href]");
        if (notNull(albumsElements)) {
            for (Element albumElement : albumsElements) {
                String albumUrl = albumElement.attr("href");

                String title = albumElement.select("img[src]").get(0)
                                           .attr("alt");

                if(!albumUrl.endsWith(".jpg") && notNullAndNotIsEmpty(albumUrl,title)) {
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
            Elements imagesElements = getDocument().select("div#content").get(0)
                                                   .select("img[src]");
            addImagesToAlbum(imagesElements, "src", "width", "height", "alt", album );
        }

        return getImagesList();
    }

    @PostConstruct
    @Override
    protected void init() {
        setURL("http://www.erichmcvey.com");
        super.init();
    }
}
