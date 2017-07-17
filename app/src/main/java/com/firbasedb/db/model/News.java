package com.firbasedb.db.model;


import java.util.Date;

/**
 * Created by dima on 6/29/17.
 */
public class News {

    private int id;
    private String title;
    private String news;
    private String preview;
    private int ownerId;
    private Date createdDate;

    private String someAwesomeFiled;

    public String getSomeAwesomeFiled() {
        return someAwesomeFiled;
    }

    public void setSomeAwesomeFiled(String someAwesomeFiled) {
        this.someAwesomeFiled = someAwesomeFiled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
