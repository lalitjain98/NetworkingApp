package com.example.lalit.networkingapp;

/**
 * Created by jainl on 01-10-2017.
 */

public class Comment {
    int post_id, comment_id;
    String name, email, body;

    public Comment(int post_id, int comment_id, String name, String email, String body) {
        this.post_id = post_id;
        this.comment_id = comment_id;
        this.name = name;
        this.email = email;
        this.body = body;
    }
}
