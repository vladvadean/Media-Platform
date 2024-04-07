package com.example.P1.notifications;

import com.example.P1.model.Content;

/**
 * interface needed for user entity to notify them
 */
public interface Observer {
    void update(Content content);
}
