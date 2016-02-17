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
import java.util.List;

@Service("tinydotphotographyParser")
@Scope("singleton")
public class TinydotphotographyParser extends AbstractParserImpl {

    public TinydotphotographyParser() {
    }

    @Override
    public Portfolio parsing() {

        Elements categoriesElements = getDocument().select("div#l")
                                                   .select("a[href]");

        if (notNull(categoriesElements)) {
            for (Element category : categoriesElements) {
                String categoryUrl = category.attr("href");

                if (notNullAndNotIsEmpty(categoryUrl)) {

                    setDocument(ParserManagement.getDocument(categoryUrl));

                    Elements albumsElements = getDocument().select("div.entry-thumb");

                    if (notNull(albumsElements)) {
                        for (Element albumElement : albumsElements) {

                            String albumUrl = albumElement.select("a[href]")
                                                          .attr("href");

                            String title = albumElement.select("img[alt]")
                                                       .attr("alt");

                            if (notNullAndNotIsEmpty(albumUrl,title)) {
                                Album album = new Album(albumUrl, getAuthor(), title, getPortfolio());
                                album.setImages(getImagesToAlbum(album));
                                getAlbumsList().add(album);
                            }
                        }
                    }
                }
            }
        }

        getPortfolio().setAlbums(getAlbumsList());
        return getPortfolio();
    }

    @Override
    protected List<Image> getImagesToAlbum(Album album) {

        setDocument(ParserManagement.getDocument(album.getUrl()));

        if (notNull(getDocument())) {
            Elements imagesElements = getDocument().select("div#content").get(0)
                                                   .getElementsByTag("img");
            if (notNull(imagesElements)) {
                for (Element imageElement : imagesElements) {
                    String imageUrl = imageElement.attr("src");
                    String alt = imageElement.attr("alt");
                    String width = imageElement.attr("width");
                    String height = imageElement.attr("height");

                    if (notNullAndNotIsEmpty(imageUrl)) {
                        getImagesList().add(new Image(imageUrl, getAuthor(), width, height, alt, getPortfolio(), album));
                    }
                }
            }
        }

        return getImagesList();
    }

    @Override
    @PostConstruct
    protected void init() {
        this.setURL("http://www.tinydotphotography.com/");
        super.init();
    }
}
