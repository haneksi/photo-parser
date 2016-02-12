package ru.photoparser.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "author")
    private String author;

    @Column(name = "width")
    private String width;

    @Column(name = "height")
    private String height;

    @Column(name = "alt")
    private String alt;

    @ManyToOne(targetEntity = Portfolio.class)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name = "album_id")
    private Album album;

    public Image() {
    }

    public Image(String url, String width, String height, String alt) {
        this.url = url;
        this.width = width;
        this.height = height;
        this.alt = alt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", alt='" + alt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;


        if (!url.equals(image.url)) return false;
        if (!author.equals(image.author)) return false;
        if (!width.equals(image.width)) return false;
        if (!height.equals(image.height)) return false;
        if (!alt.equals(image.alt)) return false;
        if (!portfolio.equals(image.portfolio)) return false;
        return album.equals(image.album);

    }

    @Override
    public int hashCode() {
        int result = url.hashCode();

        result = 31 * result + author.hashCode();
        result = 31 * result + width.hashCode();
        result = 31 * result + height.hashCode();
        result = 31 * result + alt.hashCode();
        result = 31 * result + portfolio.hashCode();
        result = 31 * result + album.hashCode();
        return result;
    }
}
