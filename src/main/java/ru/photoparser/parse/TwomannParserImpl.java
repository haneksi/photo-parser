package ru.photoparser.parse;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.util.ParserManagement;

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
        return null;
    }
}
