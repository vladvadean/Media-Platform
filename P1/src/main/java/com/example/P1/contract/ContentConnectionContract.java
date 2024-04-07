package com.example.P1.contract;

import com.example.P1.model.Content;
import com.example.P1.model.User;

import java.util.List;

/**
 * a contract interface to make sure the methods
 * to make CRUD content operations are implemented
 */
public interface ContentConnectionContract {
    public Content getContentById(String id);

    public List<Content> getAllContent();

    public Content addContent(Content content);

    public void deleteContentById(String id);

    public Content updateContent(Content content);

    /**
     *
     * @param contentId id of the content
     * @return the list of users that liked the content
     */
    public List<User> getAllUsersThatLiked(String contentId);
}
