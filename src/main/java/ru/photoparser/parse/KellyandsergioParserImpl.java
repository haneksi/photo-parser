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

@Service("kellyandsergioParser")
@Scope("singleton")
public class KellyandsergioParserImpl implements Parser{
    private final String URL = "http://www.kellyandsergio.com/blog/";
    private final Document document = ParserManagement.getDocument(URL);
    private final String author = document.title();

    @Qualifier("portfolio")
    @Autowired
    private Portfolio portfolio = new Portfolio();

    public KellyandsergioParserImpl() {
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

        Elements categories = document.select("ul.search-bar-cats").get(0).select("a[href]");
        for (Element category : categories) {
            String linkCategory = category.attr("href");

            Document doc = ParserManagement.getDocument(linkCategory);
            Elements albumsLinks = doc.select("section.post");
            for (Element link : albumsLinks) {
                Element albumLink = link.select("a[href]").get(0);
                String url = albumLink.attr("href");
                String title = albumLink.attr("title");

                Album album = new Album();
                album.setAuthor(author);
                album.setTitle(title);
                album.setUrl(url);
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

        Elements linkImages = doc.select("div.post-main").get(0).select("img[src$=.jpg]");
        for (Element linkImage : linkImages) {
            String url = linkImage.attr("src");
            String width = linkImage.attr("width");
            String height = linkImage.attr("height");
            String alt = linkImage.attr("alt");
            Image image = new Image();
            image.setAuthor(author);
            image.setHeight(height);
            image.setWidth(width);
            image.setUrl(url);
            image.setAlt(alt);
            image.setPortfolio(portfolio);
            image.setAlbum(album);

            listImages.add(image);
        }

        linkImages = doc.getElementsByAttribute("data-original");
        for (Element element : linkImages){
            String link = element.attr("data-original");
            String width = element.attr("width");
            String height = element.attr("height");
            String alt = element.attr("alt");
            if(link.endsWith(".jpg")) {
                Image image = new Image();
                image.setAlbum(album);
                image.setPortfolio(portfolio);
                image.setUrl(link);
                image.setWidth(width);
                image.setHeight(height);
                image.setAlt(alt);
                image.setAuthor(author);

                listImages.add(image);
            }
        }

        return listImages;
    }

}
