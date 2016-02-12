package ru.photoparser.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "portfolio")
public class Portfolio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "author")
    private String author;

    @OneToMany(targetEntity = Album.class,
               mappedBy = "portfolio",
               cascade = CascadeType.ALL,
               fetch = FetchType.LAZY)
    private List<Album> albums;

    public Portfolio() {
    }

    public Portfolio(String author, String url) {
        this.author = author;
        this.url = url;
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

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }


    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", albums=" + albums +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Portfolio portfolio = (Portfolio) o;


        if (!url.equals(portfolio.url)) return false;
        if (!author.equals(portfolio.author)) return false;
        return albums.equals(portfolio.albums);

    }

    @Override
    public int hashCode() {
        int result = url.hashCode();

        result = 31 * result + author.hashCode();
        result = 31 * result + albums.hashCode();
        return result;
    }
}
