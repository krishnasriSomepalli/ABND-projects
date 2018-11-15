package com.example.android.newsapp;

import java.util.Date;

public class NewsArticle {
    private String title;
    private String section;
    private String author;
    private Date publishDate;
    private String webURL;

    public NewsArticle(String title, String section, String author, Date publishDate, String webURL) {
        this.title = title;
        this.section = section;
        this.author = author;
        this.publishDate = publishDate;
        this.webURL = webURL;
    }
    public String getTitle() {
        return title;
    }
    public String getSection() {
        return section;
    }
    public String getAuthor() {
        return author;
    }
    public Date getPublishDate() {
        return publishDate;
    }
    public String getWebURL() {
        return webURL;
    }
}
