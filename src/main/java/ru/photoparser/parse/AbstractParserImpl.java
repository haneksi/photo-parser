package ru.photoparser.parse;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
    private ArrayList<Image> imagesList;

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


    protected abstract List<Image> getImagesToAlbum(Album album);

    protected List<Image> getImagesToAlbum(Album album, Portfolio portfolio, String author){

        setImagesList(new ArrayList<Image>());
        setDocument(ParserManagement.getDocument(album.getUrl()));

        if (notNull(getDocument()) && notNull(portfolio) && notNullAndNotIsEmpty(author)) {
            Elements imagesElements = getDocument().select("div#content").get(0)
                                                   .select("img[src$=.jpg]");

            addImagesToAlbum(imagesElements, "src", "width", "height", "alt", album);

            imagesElements = getDocument().getElementsByAttribute("data-lazyload-src");
            addImagesToAlbum(imagesElements, "data-lazyload-src", "width", "height", "alt", album);
        }

        return getImagesList();
    }

    protected void addImagesToAlbum(Elements imagesElements,
                                    String imageUrlAttr,
                                    String widthAttr,
                                    String heightAttr,
                                    String altAttr,
                                    Album album){
        if (notNull(imagesElements)) {
            for (Element element : imagesElements){
                String imageUrl = element.attr(imageUrlAttr);
                String width = element.attr(widthAttr);
                String height = element.attr(heightAttr);
                String alt = element.attr(altAttr);

                if(notNullAndNotIsEmpty(imageUrl) && imageUrl.endsWith(".jpg")) {
                    if(!notNullAndNotIsEmpty(width,height,alt)){
                        width = "0";
                        height = "0";
                        alt = "null";
                    }
                    getImagesList().add(new Image(imageUrl, getAuthor(), width, height, alt, getPortfolio(), album));
                }
            }
        }


    }
}
