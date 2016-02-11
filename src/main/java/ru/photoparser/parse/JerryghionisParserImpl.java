package ru.photoparser.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.util.ParserManagement;

import java.util.List;

public class JerryghionisParserImpl implements Parser{
    private final String URL = "http://www.jerryghionisphotography.com";
    private final Document document = ParserManagement.getDocument(URL);
    private final String author = document.title();

//    @Qualifier("portfolio")
//    @Autowired
    private Portfolio portfolio = new Portfolio();

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
        this.portfolio.setUrl(URL);
        this.portfolio.setUrl(author);
        StringBuilder urlAlbums = new StringBuilder(URL).append("/wedding-gallery");
        Document docAlbums = ParserManagement.getDocument(urlAlbums.toString());
        Element tableAlbums = docAlbums.select("div.GGRedactorContent GGTextContent  SelectionEnabled").get(0);
        Elements albums = tableAlbums.getElementsByTag("a");
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
        }
        return portfolio;
    }

    private List<Image> getImagesToAlbum(Album album) {
        Document document = ParserManagement.getDocument(album.getUrl());

        Element linkToImage = document.select("meta[content$=.jpg]").get(0);
        String linkCurrentImage = linkToImage.val();


        return null;
    }

    public static void main(String[] args) {
        JerryghionisParserImpl parser = new JerryghionisParserImpl();
        parser.parsing().toString();
    }
}
