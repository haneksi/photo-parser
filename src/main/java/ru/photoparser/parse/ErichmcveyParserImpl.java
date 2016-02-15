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

@Service("erichmcveyParser")
@Scope("singleton")
public class ErichmcveyParserImpl implements Parser{
    private final String URL = "http://www.erichmcvey.com";
    private final Document document = ParserManagement.getDocument(URL);
    private final String author = document.title();

    @Qualifier("portfolio")
    @Autowired
    private Portfolio portfolio;

    public ErichmcveyParserImpl() {
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
        Elements albumsElements = document.select("div#gallery-4").get(0).select("a[href]");
        for (Element albumElement : albumsElements) {
            String albumUrl = albumElement.attr("href");
            String title = albumElement.select("img[src]").get(0).attr("alt");

            if(!albumUrl.endsWith(".jpg")) {
                Album album = new Album();
                album.setTitle(title);
                album.setUrl(albumUrl);
                album.setAuthor(author);
                album.setPortfolio(portfolio);
                album.setImages(getImagesToAlbum(album));
                albums.add(album);
            }
        }

        portfolio.setAlbums(albums);
        return portfolio;
    }

    private List<Image> getImagesToAlbum(Album album) {
        Document doc = ParserManagement.getDocument(album.getUrl());
        List<Image> listImages = new ArrayList<Image>();

        Elements imagesElements = doc.select("div.slideshow_content").get(0).select("img[src]");
        for (Element imageElement : imagesElements) {
            String imageUrl = imageElement.attr("src");
            String width = imageElement.attr("width");
            String height = imageElement.attr("height");
            String alt = imageElement.attr("alt");

            Image image = new Image();
            image.setUrl(imageUrl);
            image.setWidth(width);
            image.setHeight(height);
            image.setAlt(alt);
            image.setAuthor(author);
            image.setPortfolio(portfolio);
            image.setAlbum(album);

            listImages.add(image);
        }
        return listImages;
    }
}
