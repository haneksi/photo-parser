package ru.photoparser.util;


import org.jsoup.Connection;
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
        String userAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";

        while(doc==null){
            try {
                doc = Jsoup.connect(url).userAgent(userAgent).get();
                Thread.sleep(100);
            } catch (IOException e) {
                //TODO Added log4j
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return doc;
    }

}
