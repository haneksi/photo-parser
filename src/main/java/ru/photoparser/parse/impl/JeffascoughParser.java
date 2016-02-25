package ru.photoparser.parse.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.photoparser.entity.Album;
import ru.photoparser.entity.Image;
import ru.photoparser.entity.Portfolio;
import ru.photoparser.parse.AbstractParserImpl;
import ru.photoparser.util.ParserManagement;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service("jeffascoughParser")
@Scope("singleton")
public class JeffascoughParser extends AbstractParserImpl{

    public JeffascoughParser() {
    }

    @Override
    public Portfolio parsing() {
        Elements albumsElements = getDocument().select("div.text-below")
                                               .select("a[href]");
        if (notNull(albumsElements)) {
            for (Element albumElement : albumsElements) {
                String albumUrl = albumElement.attr("href");
                String title = albumElement.text();

                if (notNullAndNotIsEmpty(albumUrl, title)) {
                    Album album = new Album(albumUrl, getAuthor(), title, getPortfolio());
                    album.setImages(getImagesToAlbum(album));
                    getAlbumsList().add(album);
                }
            }
        }

        getPortfolio().setAlbums(getAlbumsList());
        return getPortfolio();
    }

    @Override
    protected List<Image> getImagesToAlbum(Album album) {

        setImagesList(new ArrayList<Image>());
        setDocument(ParserManagement.getDocument(album.getUrl()));

        if (notNull(getDocument())) {
            Elements imagesElements = getDocument().select("[data-role=content]").get(0)
                                                   .select("img[src]");

            if (notNull(imagesElements)) {
                for (Element imageElement : imagesElements) {

                    String imageUrl = imageElement.attr("src");
                    if(!imageUrl.endsWith(".jpg")){
                        imageUrl = imageElement.attr("data-lazyload-src");
                    }

                    String width = imageElement.attr("width");
                    String height = imageElement.attr("height");
                    String alt = imageElement.attr("alt");

                    if(notNullAndNotIsEmpty(imageUrl) && imageUrl.endsWith(".jpg")) {
                        getImagesList().add(new Image(imageUrl, getAuthor(), width, height, alt, getPortfolio(), album));
                    }
                }
            }
        }

        return getImagesList();
    }

    @PostConstruct
    @Override
    protected void init() {
        setURL("http://www.jeffascough.com/");
        super.init();
    }
}
