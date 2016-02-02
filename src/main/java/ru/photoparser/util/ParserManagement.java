package ru.photoparser.util;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

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

    public static Object getBean(String beanName, Class clazz){
        return new ClassPathXmlApplicationContext(new String[] {"context.xml"}).getBean(beanName, clazz);
    }


}