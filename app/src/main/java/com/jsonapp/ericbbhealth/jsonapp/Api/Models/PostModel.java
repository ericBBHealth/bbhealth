package com.jsonapp.ericbbhealth.jsonapp.Api.Models;

/**
 * Created by eric_ on 16/09/2017.
 */

public class PostModel {

    String body;
    String title;
    int id;
    int userId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String email) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int postId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
