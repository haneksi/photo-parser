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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Service("jerryghionisParser")
@Scope("singleton")
public class JerryghionisParser extends AbstractParserImpl {

    public JerryghionisParser() {
    }

    @Override
    public Portfolio parsing() {

        StringBuilder urlAlbums = new StringBuilder(getURL()).append("/wedding-albums");
        setDocument(ParserManagement.getDocument(urlAlbums.toString()));

        if (notNull(getDocument())) {
            Elements albumsElements = getDocument().select("table.GGResponsiveTable").get(0)
                                                   .getElementsByTag("a");

            if (notNull(albumsElements)) {
                for (Element albumElement : albumsElements) {
                    StringBuilder albumUrl = new StringBuilder(getURL());
                    String href = albumElement.attr("href");
                    String title = albumElement.attr("title");
                    albumUrl.append(href);

                    if (notNullAndNotIsEmpty(albumUrl.toString(), title)) {
                        Album album = new Album(albumUrl.toString(), getAuthor(), title, getPortfolio());
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

        Deque<Image> deque = new ArrayDeque<>();

        setImagesList(new ArrayList<Image>());
        setDocument(ParserManagement.getDocument(album.getUrl()));

        if (notNull(getDocument())) {
            while(true) {
                String currentImageUrl = getDocument().select("meta[content$=.jpg]").get(0)
                                                      .attr("content");

                if (notNullAndNotIsEmpty(currentImageUrl)) {
                    Image image = new Image(currentImageUrl, getAuthor(),"0","0","null",getPortfolio(),album);

                    if(deque.contains(image)){
                        break;
                    } else {
                        deque.add(image);
                        StringBuilder nextImageUrl = new StringBuilder();
                        nextImageUrl.append(getURL()).append(getDocument().select("a.next").get(0)
                                                                          .attr("href"));

                        setDocument(ParserManagement.getDocument(nextImageUrl.toString()));
                    }
                }
            }

            getImagesList().addAll(deque);
        }

        return getImagesList();
    }

    @PostConstruct
    @Override
    protected void init() {
        setURL("http://www.jerryghionisphotography.com");
        super.init();
    }
}
