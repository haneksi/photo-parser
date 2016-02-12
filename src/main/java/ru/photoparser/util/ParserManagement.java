package ru.photoparser.util;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.entity.Portfolio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserManagement {

    private ParserManagement() {
    }

    public static Document getDocument(String url) {
        Document doc = null;
        String userAgent = "Chrome";

        try {
            doc = Jsoup.connect(url).userAgent(userAgent).get();
        } catch (IOException e) {
            //TODO Added log4j
        }
        return doc;
    }


    public static List<Image> getImagesToAlbumWP(Album album, Portfolio portfolio, String author){
        Document doc = ParserManagement.getDocument(album.getUrl());
        List<Image> listImages = new ArrayList<Image>();

        Elements linkImages = doc.select("div#content").get(0).select("img[src$=.jpg]");
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

        linkImages = doc.getElementsByAttribute("data-lazyload-src");
        for (Element element : linkImages){
            String link = element.attr("data-lazyload-src");
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
