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

@Service("tinydotphotographyParser")
@Scope("singleton")
public class TinydotphotographyParserImpl implements Parser{
    private final String URL = "http://www.tinydotphotography.com/";
    private final Document document = ParserManagement.getDocument(URL);
    private final String author = document.title();


    @Qualifier("portfolio")
    @Autowired
    private Portfolio portfolio;

    public TinydotphotographyParserImpl() {
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

        List<Album> albums = new ArrayList<>();

        Elements categories = document.select("div#l").get(0).select("a[href]");
        for (Element category : categories) {
            String categoryUrl = category.attr("href");
            Document doc = ParserManagement.getDocument(categoryUrl);
            Elements albumsElements = doc.select("div.entry-thumb");
            for (Element albumElement : albumsElements) {
                String albumUrl = albumElement.select("a[href]").attr("href");
                String title = albumElement.select("img[alt]").attr("alt");

                Album album = new Album();
                album.setAuthor(author);
                album.setUrl(albumUrl);
                album.setTitle(title);
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

        Elements imageElements = doc.select("div#content").get(0).getElementsByTag("img");
        for (Element imageElement : imageElements) {
            String imageUrl = imageElement.attr("src");
            String alt = imageElement.attr("alt");
            String width = imageElement.attr("width");
            String height = imageElement.attr("height");

            Image image = new Image();
            image.setAlt(alt);
            image.setUrl(imageUrl);
            image.setHeight(height);
            image.setWidth(width);
            image.setAuthor(author);
            image.setAlbum(album);
            image.setPortfolio(portfolio);

            listImages.add(image);
        }

        return listImages;
    }
}
