package ru.photoparser.parse.impl;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.parse.Parser;
import ru.photoparser.util.ParserManagement;

import java.util.ArrayList;
import java.util.List;

@Service("levkupermanParser")
@Scope("singleton")
public class LevkupermanParserImpl implements Parser {
    private final String URL = "http://www.levkuperman.com";
    private final Document document = ParserManagement.getDocument(URL);
    private final String author = document.select("meta[property=og:title]").get(0).attr("content");

    @Qualifier("portfolio")
    @Autowired
    private Portfolio portfolio;

    public LevkupermanParserImpl() {
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getURL() {
        return URL;
    }

    @Override
    public Portfolio parsing() {
        portfolio.setUrl(URL);
        portfolio.setAuthor(author);

        List<Album> albums = new ArrayList<Album>();

        StringBuilder linkToWeddingAlbums = new StringBuilder(URL).append("/featured-weddings/");
        StringBuilder linkToEngagementAlbums = new StringBuilder(URL).append("/featured-engagement-sessions/");

        Document doc = null;
        for (int i = 0; i < 2; i++) {
            if (i==0) {
                doc = ParserManagement.getDocument(linkToWeddingAlbums.toString());
            }

            Elements select = doc.select("div.text-wrap");
            for (Element element : select) {
                Element link = element.select("a[href]").get(0);
                String url = link.attr("href");
                String title = link.attr("title");

                Album album = new Album();
                album.setUrl(url);
                album.setTitle(title);
                album.setAuthor(author);
                album.setPortfolio(portfolio);
                List<Image> images = ParserManagement.getImagesToAlbumWP(album, portfolio, author);
                album.setImages(images);
                albums.add(album);
            }

            doc = ParserManagement.getDocument(linkToEngagementAlbums.toString());
        }

        portfolio.setAlbums(albums);
        return portfolio;
    }

}
