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

import java.util.ArrayList;
import java.util.List;

@Service("twomannParser")
@Scope("singleton")
public class TwomannParserImpl implements Parser{
    private final String URL = "http://twomann.com/weddings/";
    private final Document document = ParserManagement.getDocument(URL);
    private final String author = document.title();


    @Qualifier("portfolio")
    @Autowired
    private Portfolio portfolio;

    public TwomannParserImpl() {
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
        Elements albumsElements = document.select("li.thumbnail");
        for (Element albumElement : albumsElements) {
            String albumUrl = albumElement.select("a[href]").get(0).attr("href");
            String title = albumElement.select("div.thumbnail-caption").get(0).text();

            Album album = new Album();
            album.setUrl(albumUrl);
            album.setTitle(title);
            album.setAuthor(author);
            album.setPortfolio(portfolio);
            album.setImages(getImagesToAlbum(album));
            albums.add(album);
        }
        portfolio.setAlbums(albums);
        return portfolio;
    }

    private List<Image> getImagesToAlbum(Album album) {
        Document doc = ParserManagement.getDocument(album.getUrl());
        ArrayList<Image> images = new ArrayList<>();
        Elements imagesElements = doc.getElementsByAttribute("data-src1320");
        for (Element imageElement : imagesElements) {
            String imageUrl = imageElement.attr("data-src1320");
            String title = imageElement.attr("title");
            String alt = imageElement.attr("alt");

            Image image = new Image();
            image.setUrl(imageUrl);
            image.setWidth("");
            image.setHeight("");
            image.setAlt(alt);
            image.setAuthor(author);
            image.setPortfolio(portfolio);
            image.setAlbum(album);

            images.add(image);
        }
        return images;
    }
}
