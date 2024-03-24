package com.example.P1.contract;

import com.example.P1.model.LikedContent;

import java.util.List;
/**
 * a contract interface to make sure the methods
 * to make CRUD liked content operations are implemented
 */
public interface LikedContentConnectionContract {
    public List<LikedContent> getAllLikedContent();

    /**
     * get all the entries of liked content of a certain user
     * @param userId user id
     * @return a List<LikedContent>
     */
    public List<LikedContent> getAllLikedContentByUserId(String userId);

    /**
     * get all the entries of a certain content in the liked content table
     * @param contentId content id
     * @return a List<LikedContent>
     */
    List<LikedContent> getAllLikedContentByContentId(String contentId);

    /**
     * get a single entry of certain user and a certain content
     * @param userId user id
     * @param contentId content id
     * @return a LikedContent object
     */
    public LikedContent getLikedContentByUserContentId(String userId,String contentId);

    public LikedContent addLikedContent(LikedContent likedContent);

    public void deleteUserByLikedContentId(String id);

    public LikedContent updateLikedContent(LikedContent likedContent);

    LikedContent getLikedContentById(String id);
}
