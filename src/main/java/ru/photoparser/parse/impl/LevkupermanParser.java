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

@Service("levkupermanParser")
@Scope("singleton")
public class LevkupermanParser extends AbstractParserImpl {

    public LevkupermanParser() {
    }

    @Override
    public Portfolio parsing() {

        StringBuilder linkToWeddingAlbums = new StringBuilder(getURL()).append("/featured-weddings/");
        StringBuilder linkToEngagementAlbums = new StringBuilder(getURL()).append("/featured-engagement-sessions/");

        if (notNullAndNotIsEmpty(linkToWeddingAlbums.toString(), linkToEngagementAlbums.toString())) {

            for (int i = 0; i < 2; i++) {
                if (i==0) {
                    setDocument(ParserManagement.getDocument(linkToWeddingAlbums.toString()));
                }

                if (notNull(getDocument())) {
                    Elements albumsElements = getDocument().select("div.text-wrap");

                    if (notNull(albumsElements)) {
                        for (Element albumElement : albumsElements) {

                            String albumUrl = albumElement.select("a[href]").get(0)
                                                          .attr("href");

                            String title = albumElement.select("a[href]").get(0)
                                                       .attr("title");

                            if (notNullAndNotIsEmpty(albumUrl,title)) {
                                Album album = new Album(albumUrl, getAuthor(), title, getPortfolio());
                                album.setImages(getImagesToAlbum(album));
                                getAlbumsList().add(album);
                            }
                        }
                    }
                }

                setDocument(ParserManagement.getDocument(linkToEngagementAlbums.toString()));
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
        this.setURL("http://www.levkuperman.com");
        super.init();
        this.setAuthor(getDocument().select("meta[property=og:title]").get(0).attr("content"));
    }
}
