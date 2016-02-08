package ru.photoparser.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "album")
public class Album{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    Portfolio portfolio;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images;

    public Album() {
    }

    public Album(String url, String title) {
        this.url = url;
        this.title = title;
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
}
