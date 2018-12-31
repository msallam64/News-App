package com.example.mohamedsallam.newsapp;

/**
 * Created by Mohamed Sallam on 27-Feb-18.
 */

public class News {
    private String title;
    private String section;
    private String author;
    private String date;
    private String newsUrl;

    public News(String title, String section, String author, String date, String newsUrl) {
        this.title = title;
        this.section = section;
        this.author = author;
        this.date = date;
        this.newsUrl = newsUrl;
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

    public String getDate() {
        return date;
    }

    public String getNewsUrl() {
        return newsUrl;
    }
}
