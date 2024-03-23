package com.example.P1.contract;

import com.example.P1.model.LikedContent;

import java.util.List;

public interface LikedContentConnectionContract {
    public List<LikedContent> getAllLikedContent();

    public List<LikedContent> getAllLikedContentByUserId(String userId);

    List<LikedContent> getAllLikedContentByContentId(String contentId);

    public LikedContent getLikedContentByUserContentId(String userId,String contentId);

    public LikedContent addLikedContent(LikedContent likedContent);

    public void deleteUserByLikedContentId(String id);

    public LikedContent updateLikedContent(LikedContent likedContent);

    LikedContent getLikedContentById(String id);
}
