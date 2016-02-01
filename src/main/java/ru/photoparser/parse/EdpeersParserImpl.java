package ru.photoparser.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.util.ParserManagement;

import java.util.ArrayList;
import java.util.List;

/*
*  For site:  http://www.edpeers.com
*/
public class EdpeersParserImpl implements Parser{
    private final String URL = "http://www.edpeers.com";
    private Portfolio portfolio ;

    public EdpeersParserImpl() {
        this.portfolio = new Portfolio();
        Document document = ParserManagement.getDocument(URL);
        portfolio.setUrl(URL);
        portfolio.setAuthor(document.title());
        portfolio.setAlbums(getAlbums());
    }

    @Override
    public Portfolio getPortfolio() {
        return portfolio;
    }

    @Override
    public List<Image> getAllImages() {
        List<Image> images = new ArrayList<Image>();

        for (Album album : portfolio.getAlbums()) {
            for (Image image : album.getImages()) {
                images.add(image);
            }
        }
        return images;
    }

    @Override
    public List<Album> getAlbums() {
        String url = portfolio.getUrl();
        Document document = ParserManagement.getDocument(url);
        List<Album> albums = new ArrayList<Album>();

        Elements links = document.select("span.read-more-wrap").select("a[href]");
        for (Element el: links ){
            albums.add(new Album(el.attr("href"), portfolio.getAuthor(), el.attr("title")));
        }

        for (Album album: albums){
            album.setImages(getImagesToAlbum(album));
        }

        return albums;
    }

    @Override
    public List<Image> getImagesToAlbum(Album album) {
        String albumUrl = album.getUrl();
        Document document = ParserManagement.getDocument(albumUrl);
        List<Image> listImages = new ArrayList<Image>();
        Elements images = document.getElementsByAttribute("data-lazyload-src");

        for (Element image: images){
            String link = image.attr("data-lazyload-src");
            String width = image.attr("width");
            String height = image.attr("height");
            String alt = image.attr("alt");
            if(link.endsWith(".jpg")) {
                listImages.add(new Image(link, portfolio.getAuthor(), width, height, alt));
            }
        }
        album.setImages(listImages);

        return listImages;
    }

}
