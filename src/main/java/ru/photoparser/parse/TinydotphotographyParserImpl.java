package ru.photoparser.parse;


import org.jsoup.nodes.Document;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.util.ParserManagement;

public class TinydotphotographyParserImpl implements Parser{
    private final String URL = "http://www.tinydotphotography.com/";
    private final Document document = ParserManagement.getDocument(URL);
    private final String author = document.title();

    @Override
    public Portfolio parsing() {
        return null;
    }
}
