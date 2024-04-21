package com.example.P1.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.P1.model.LikedContent;
import com.example.P1.repository.LikedContentConnectionDB;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LikedContentServiceTest {
    @Mock
    private LikedContentConnectionDB likedContentConnectionDBMock;

    private LikedContentService likedContentService;

    /**
     * Sets up mocks and initializes the service before each test.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        likedContentService = new LikedContentService(likedContentConnectionDBMock);
    }

    /**
     * Tests retrieval of all liked content from the database.
     * Verifies that the correct repository method is called and that it returns the expected list.
     */
    @Test
    public void getAllLikedContentTest() {
        List<LikedContent> expected = Arrays.asList(
                new LikedContent("X", "user1", "content1"),
                new LikedContent("Y", "user2", "content2")
        );
        when(likedContentConnectionDBMock.findAll()).thenReturn(expected);

        List<LikedContent> actual = likedContentService.getAllLikedContent();

        verify(likedContentConnectionDBMock).findAll();
        assertEquals(expected, actual);
    }

    /**
     * Tests retrieval of all liked content by a specific user ID.
     * Ensures that the service returns the correct data for a given user.
     */
    @Test
    public void getAllLikedContentByUserIdTest() {
        List<LikedContent> expected = Arrays.asList(new LikedContent("X", "user1", "content1"));
        when(likedContentConnectionDBMock.findLikedContentByUserId("user1")).thenReturn(Optional.of(expected));

        List<LikedContent> actual = likedContentService.getAllLikedContentByUserId("user1");

        verify(likedContentConnectionDBMock).findLikedContentByUserId("user1");
        assertEquals(expected, actual);
    }

    /**
     * Tests retrieval of all liked content by a specific content ID.
     * Checks that the service correctly fetches data associated with a particular content.
     */
    @Test
    public void getAllLikedContentByContentIdTest() {
        List<LikedContent> expected = Arrays.asList(new LikedContent("X", "user1", "content1"));
        when(likedContentConnectionDBMock.findLikedContentByContentId("content1")).thenReturn(Optional.of(expected));

        List<LikedContent> actual = likedContentService.getAllLikedContentByContentId("content1");

        verify(likedContentConnectionDBMock).findLikedContentByContentId("content1");
        assertEquals(expected, actual);
    }

    /**
     * Tests the addition of a new liked content entry.
     * Verifies that the entry is saved in the database and returns the correct object.
     */
    @Test
    public void addLikedContentTest() {
        LikedContent likedContent = new LikedContent("X", "user1", "content1");
        when(likedContentConnectionDBMock.save(likedContent)).thenReturn(likedContent);

        LikedContent saved = likedContentService.addLikedContent(likedContent);

        verify(likedContentConnectionDBMock).save(likedContent);
        assertEquals(likedContent, saved);
    }

    /**
     * Tests the updating of an existing liked content entry.
     * Ensures that the updated content is correctly saved and retrieved.
     */
    @Test
    public void updateLikedContentTest() {
        LikedContent likedContent = new LikedContent("X", "user1", "content2");
        when(likedContentConnectionDBMock.save(likedContent)).thenReturn(likedContent);

        LikedContent updated = likedContentService.updateLikedContent(likedContent);

        verify(likedContentConnectionDBMock).save(likedContent);
        assertEquals(likedContent, updated);
    }

    /**
     * Tests fetching a specific liked content entry by its ID.
     * Verifies the service correctly finds and returns the requested content.
     */
    @Test
    public void getLikedContentByIdTest() {
        LikedContent expected = new LikedContent("X", "user1", "content1");
        when(likedContentConnectionDBMock.findLikedContentById("1")).thenReturn(Optional.of(expected));

        LikedContent actual = likedContentService.getLikedContentById("1");

        verify(likedContentConnectionDBMock).findLikedContentById("1");
        assertEquals(expected, actual);
    }

    /**
     * Tests fetching a liked content entry by both user and content IDs.
     * Confirms that the correct content is retrieved for the specified user and content combination.
     */
    @Test
    public void getLikedContentByUserContentIdTest() {
        LikedContent expected = new LikedContent("X", "user1", "content1");
        when(likedContentConnectionDBMock.findLikedContentByUserContentId("user1", "content1")).thenReturn(Optional.of(expected));

        LikedContent actual = likedContentService.getLikedContentByUserContentId("user1", "content1");

        verify(likedContentConnectionDBMock).findLikedContentByUserContentId("user1", "content1");
        assertEquals(expected, actual);
    }
}
