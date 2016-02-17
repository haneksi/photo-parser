package ru.photoparser.parse;


import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.util.ParserManagement;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParserImpl implements Parser{
    private String URL;
    private Document document;
    private String author;
    private List<Album> albumsList = new ArrayList<Album>();
    private ArrayList<Image> imagesList = new ArrayList<>();

    @Qualifier("portfolio")
    @Autowired
    private Portfolio portfolio;

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Album> getAlbumsList() {
        return albumsList;
    }

    public void setAlbumsList(List<Album> albumsList) {
        this.albumsList = albumsList;
    }

    public ArrayList<Image> getImagesList() {
        return imagesList;
    }

    public void setImagesList(ArrayList<Image> imagesList) {
        this.imagesList = imagesList;
    }

    protected abstract List<Image> getImagesToAlbum(Album album);

    protected void init(){

        this.setDocument(ParserManagement.getDocument(getURL()));
        this.setAuthor(getDocument().title());

        this.portfolio.setUrl(getURL());
        this.portfolio.setAuthor(getAuthor());
    };

    protected boolean notNullAndNotIsEmpty(String ...text){
        for (String word: text) {
            boolean validate = word!=null && !word.isEmpty();
            if (validate==false) return false;
        }
        return true;
    }

    protected boolean notNull(Object object){
        return object!=null;
    }
}
