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

}
