package ru.photoparser.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "album")
public class Album{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = Portfolio.class)
    @JoinColumn(name = "portfolio_id")
    Portfolio portfolio;

    @OneToMany(targetEntity = Image.class,
               mappedBy = "album",
               cascade = CascadeType.ALL,
               fetch = FetchType.EAGER)
    private List<Image> images;

    public Album() {
    }

    public Album(String url, String author, String title, Portfolio portfolio) {
        this.url = url;
        this.author = author;
        this.title = title;
        this.portfolio = portfolio;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", images=" + images +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;


        if (!url.equals(album.url)) return false;
        if (!author.equals(album.author)) return false;
        if (!title.equals(album.title)) return false;
        if (!portfolio.equals(album.portfolio)) return false;
        return images.equals(album.images);

    }

    @Override
    public int hashCode() {
        int result = url.hashCode();

        result = 31 * result + author.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + portfolio.hashCode();
        result = 31 * result + images.hashCode();
        return result;
    }
}
