package ru.photoparser.parse;

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
import ru.photoparser.util.ParserManagement;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Service("jerryghionisParser")
@Scope("singleton")
public class JerryghionisParserImpl implements Parser{
    private final String URL = "http://www.jerryghionisphotography.com";
    private final Document document = ParserManagement.getDocument(URL);
    private final String author = document.title();

    @Qualifier("portfolio")
    @Autowired
    private Portfolio portfolio;

    public JerryghionisParserImpl() {
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
        List<Album> albumsList = new ArrayList<Album>();
        this.portfolio.setUrl(URL);
        this.portfolio.setAuthor(author);

        StringBuilder urlAlbums = new StringBuilder(URL).append("/wedding-albums");
        Document docAlbums = ParserManagement.getDocument(urlAlbums.toString());
        Elements albums = docAlbums.select("table.GGResponsiveTable").get(0).getElementsByTag("a");

        for (Element element : albums) {
            StringBuilder urlAlbum = new StringBuilder(URL);
            String href = element.attr("href");
            String title = element.attr("title");
            urlAlbum.append(href);
            Album album = new Album();
            album.setUrl(urlAlbum.toString());
            album.setTitle(title);
            album.setAuthor(author);
            album.setPortfolio(portfolio);
            List<Image> images = getImagesToAlbum(album);
            album.setImages(images);
            albumsList.add(album);
        }
        portfolio.setAlbums(albumsList);
        return portfolio;
    }

    private List<Image> getImagesToAlbum(Album album) {
        List<Image> list = new ArrayList<>();
        Deque<Image> deque = new ArrayDeque<>();

        Document document = ParserManagement.getDocument(album.getUrl());
        while(true) {
            String currentImageLink = document.select("meta[content$=.jpg]").get(0).attr("content");
            Image image = new Image();
            image.setAuthor(author);
            image.setAlbum(album);
            image.setPortfolio(portfolio);
            image.setAlt("");
            image.setHeight("");
            image.setWidth("");
            image.setUrl(currentImageLink);
            if(deque.contains(image)){
                break;
            } else {
                deque.add(image);
                StringBuilder nextImageLink = new StringBuilder();
                nextImageLink.append(URL).append(document.select("a.next").get(0).attr("href"));
                document = ParserManagement.getDocument(nextImageLink.toString());
            }
        }

        list.addAll(deque);

        return list;
    }

}
