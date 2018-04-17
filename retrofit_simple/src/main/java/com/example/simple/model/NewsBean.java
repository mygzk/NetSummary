package com.example.simple.model;

/**
 * Created by guozhk on 2018/4/16.
 */

public class NewsBean {


    /**
     * id : 2
     * title : 长江流域首次发现海豚
     * date : 2016-08-12
     * likes : 505
     * views : 9800
     */

    private int id;
    private String title;
    private String date;
    private int likes;
    private int views;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
