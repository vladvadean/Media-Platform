package com.example.P1.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.P1.model.Content;
import com.example.P1.model.User;
import com.example.P1.service.notifications.Observer;
import com.example.P1.repository.ContentConnectionDB;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ContentServiceTest {
    @Mock
    private ContentConnectionDB contentConnectionDBMock;
    @Mock
    private Observer observerMock;

    private ContentService contentService;

    /**
     * Sets up mocks and initializes the service before each test.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        contentService = new ContentService(contentConnectionDBMock);
        contentService.registerObserver(observerMock);
    }

    /**
     * Tests the retrieval of a single content item by its ID.
     * Ensures that the content is fetched correctly from the database.
     */
    @Test
    public void getContentByIdTest() {
        Content expectedContent = new Content("admin1", "Title1", "link1", null, null, 4.5f);
        expectedContent.setId("1");

        when(contentConnectionDBMock.findContentById("1")).thenReturn(Optional.of(expectedContent));

        Content actualContent = contentService.getContentById("1");

        verify(contentConnectionDBMock).findContentById("1");
        assertEquals(expectedContent, actualContent);
    }

    /**
     * Tests the retrieval of all content.
     * Verifies that all content entries are fetched correctly from the database.
     */
    @Test
    public void getAllContentTest() {
        List<Content> expectedContents = Arrays.asList(
                new Content("admin1", "Title1", "link1", null, null, 4.5f),
                new Content("admin2", "Title2", "link2", null, null, 3.5f)
        );

        when(contentConnectionDBMock.findAll()).thenReturn(expectedContents);

        List<Content> actualContents = contentService.getAllContent();

        verify(contentConnectionDBMock).findAll();
        assertEquals(expectedContents, actualContents);
    }

    /**
     * Tests the addition of a new content item.
     * Verifies that the content is saved in the database and that all observers are notified.
     */
    @Test
    public void addContentTest() {
        Content content = new Content("admin1", "Title1", "link1", null, null, 4.5f);
        content.setId(UUID.randomUUID().toString());

        when(contentConnectionDBMock.save(content)).thenReturn(content);

        Content savedContent = contentService.addContent(content);

        verify(contentConnectionDBMock).save(content);
        verify(observerMock).update(content);
        assertEquals(content, savedContent);
    }

    /**
     * Tests the updating of an existing content item.
     * Ensures that the updated content is correctly saved and returned.
     */
    @Test
    public void updateContentTest() {
        Content content = new Content("admin1", "Updated Title", "link1", null, null, 4.6f);
        content.setId("1");

        when(contentConnectionDBMock.save(content)).thenReturn(content);

        Content updatedContent = contentService.updateContent(content);

        verify(contentConnectionDBMock).save(content);
        assertEquals(content, updatedContent);
    }

    /**
     * Tests the deletion of a content item by its ID.
     * Ensures that the content is removed correctly from the database.
     */
    @Test
    public void deleteContentByIdTest() {
        String contentId = "1";

        doNothing().when(contentConnectionDBMock).deleteById(contentId);
        contentService.deleteContentById(contentId);

        verify(contentConnectionDBMock).deleteById(contentId);
    }

    /**
     * Tests the retrieval of all users that liked a particular content item.
     * Ensures that the method returns a list of users or throws if no likes are found.
     */
    @Test
    public void getAllUsersThatLikedTest() {
        List<User> expectedUsers = Arrays.asList(
                new User("user1", "pass1", "user1@example.com", null),
                new User("user2", "pass2", "user2@example.com", null)
        );

        when(contentConnectionDBMock.getAllUserThatLiked("1")).thenReturn(Optional.of(expectedUsers));

        List<User> actualUsers = contentService.getAllUsersThatLiked("1");

        verify(contentConnectionDBMock).getAllUserThatLiked("1");
        assertEquals(expectedUsers, actualUsers);
    }
}
