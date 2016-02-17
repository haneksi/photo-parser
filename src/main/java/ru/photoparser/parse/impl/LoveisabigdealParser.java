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

@Service("loveisabigdealParser")
@Scope("singleton")
public class LoveisabigdealParser extends AbstractParserImpl {

    public LoveisabigdealParser() {
    }


    @Override
    public Portfolio parsing() {
        Elements categoriesElements = getDocument().select("li[class^=cat-item cat-item-]");

        if (notNull(categoriesElements)) {
            for (Element categoryElement : categoriesElements) {

                String categoryUrl = categoryElement.select("a[href]").get(0)
                                                    .attr("href");

                if (notNullAndNotIsEmpty(categoryUrl)) {

                    setDocument(ParserManagement.getDocument(categoryUrl));

                    if (notNull(getDocument())) {

                        Elements albumsElements = getDocument().select("div.text-below")
                                                               .select("h3")
                                                               .select("a[href]");

                        if (notNull(albumsElements)) {
                            for (Element albumElement : albumsElements) {
                                String albumUrl = albumElement.attr("href");
                                String title = albumElement.attr("title");

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
        }
        return getPortfolio();
    }

    @Override
    protected List<Image> getImagesToAlbum(Album album) {

        setDocument(ParserManagement.getDocument(album.getUrl()));

        if (notNull(getDocument())) {
            Elements imagesElements = getDocument().select("[data-role=content]").get(0)
                                                   .select("img[src]");

            if (notNull(imagesElements)) {
                for (Element imageElement : imagesElements) {
                    String imageUrl = imageElement.attr("src");
                    String width = imageElement.attr("width");
                    String height = imageElement.attr("height");
                    String alt = imageElement.attr("alt");

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
    protected void init(){

        this.setURL("http://www.loveisabigdeal.com/");
        super.init();
    }

}
