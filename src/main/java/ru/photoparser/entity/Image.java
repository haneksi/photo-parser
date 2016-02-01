package ru.photoparser.entity;


public class Image {
    private Long id;
    private String url;
    private String author;
    private String width ;
    private String height ;
    private String alt ;

    public Image() {
    }

    public Image(String url, String author, String width, String height, String alt) {
        this.url = url;
        this.author = author;
        this.width = width;
        this.height = height;
        this.alt = alt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
