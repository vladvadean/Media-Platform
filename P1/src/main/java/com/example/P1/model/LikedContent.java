package com.example.P1.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class LikedContent {
    @Id
    private String id;
    private String userId;
    private String contentId;
    public LikedContent(){

    }
    public LikedContent(String id,String userId,String contentId){
        this.id = id;
        this.userId = userId;
        this.contentId = contentId;
    }

    public String getContentId() {
        return contentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
