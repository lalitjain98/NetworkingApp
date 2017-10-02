package com.example.lalit.networkingapp;

/**
 * Created by jainl on 01-10-2017.
 */

public class Post {
    int post_id;
    String title,body;

    public Post(int post_id, String title, String body) {
        this.post_id = post_id;
        this.title = title;
        this.body = body;
    }
}
