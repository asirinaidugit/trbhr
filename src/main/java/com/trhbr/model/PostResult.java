package com.trhbr.model;

import lombok.Data;

import java.io.Serializable;


public class PostResult implements Serializable {
    private int post_id;
    private String post_title;
    private String post_body;
    private int total_number_of_comments;

    public PostResult() {
    }

    public PostResult(int post_id, String post_title, String post_body, int total_number_of_comments) {
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_body = post_body;
        this.total_number_of_comments = total_number_of_comments;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_body() {
        return post_body;
    }

    public void setPost_body(String post_body) {
        this.post_body = post_body;
    }

    public int getTotal_number_of_comments() {
        return total_number_of_comments;
    }

    public void setTotal_number_of_comments(int total_number_of_comments) {
        this.total_number_of_comments = total_number_of_comments;
    }
}
