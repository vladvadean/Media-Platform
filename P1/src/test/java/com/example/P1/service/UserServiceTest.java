package com.example.P1.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import com.example.P1.model.BillingDetails;
import com.example.P1.model.Content;
import com.example.P1.model.ItemNotFoundException;
import com.example.P1.model.User;
import com.example.P1.service.notifications.Observable;
import com.example.P1.repository.UserConnectionDB;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceTest {
    @Mock
    private UserConnectionDB userConnectionDBMock;
    @Mock
    private Observable contentServiceMock;

    private UserService userService;

    /**
     * Sets up mocks and initializes the service before each test.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userConnectionDBMock, contentServiceMock);
    }

    /**
     * Tests the addUser method to ensure it correctly registers the user as an observer,
     * saves the user to the database, and returns the saved user object.
     */
    @Test
    public void addUserTest() {
        User user = new User("username", "password", "email@example.com", null);
        user.setId(UUID.randomUUID().toString());

        when(userConnectionDBMock.save(any(User.class))).thenReturn(user);

        User savedUser = userService.addUser(user);

        verify(contentServiceMock).registerObserver(user);
        verify(userConnectionDBMock).save(user);
        assertEquals(user, savedUser);
    }

    /**
     * Tests the updateUser method to ensure it correctly saves the updated user information
     * to the database and returns the updated user object.
     */
    @Test
    public void updateUserTest() {
        User user = new User("username", "password", "email@example.com", null);
        user.setId(UUID.randomUUID().toString());

        when(userConnectionDBMock.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(user);

        verify(userConnectionDBMock).save(user);
        assertEquals(user, updatedUser);
    }

    /**
     * Tests the getUserById method to verify it correctly retrieves a user by ID from the database
     * and returns the corresponding user object.
     */
    @Test
    public void getUserByIdNormalTest() {
        User expectedUser = new User("username", "password", "email@example.com", null);
        expectedUser.setId("1");

        when(userConnectionDBMock.findUserById("1")).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.getUserById("1");

        verify(userConnectionDBMock).findUserById("1");
        assertEquals(expectedUser, actualUser);
    }

    /**
     * Tests the getUserById method to ensure it throws an ItemNotFoundException when no user
     * is found with the specified ID.
     */
    @Test
    public void getUserByIdExceptTest() {
        when(userConnectionDBMock.findUserById("1")).thenReturn(Optional.empty());

        Exception exception = assertThrows(ItemNotFoundException.class, () -> userService.getUserById("1"));

        assertEquals("User by id 1 was not found", exception.getMessage());
    }

    /**
     * Tests the deleteUserById method to ensure it correctly removes a user from the database
     * and also unregisters the user from being an observer.
     */
    @Test
    public void deleteUserByIdTest() {
        User user = new User("username", "password", "email@example.com", null);
        user.setId("1");

        when(userConnectionDBMock.findUserById("1")).thenReturn(Optional.of(user));

        userService.deleteUserById("1");

        verify(contentServiceMock).removeObserver(user);
        verify(userConnectionDBMock).deleteById("1");
    }

    /**
     * Tests the getAllUsers method to verify it retrieves all users from the database
     * and returns a list of these users.
     */
    @Test
    public void getAllUsersTest() {
        List<User> expectedUsers = Arrays.asList(
                new User("username1", "password1", "email1@example.com", null),
                new User("username2", "password2", "email2@example.com", null)
        );

        when(userConnectionDBMock.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.getAllUsers();

        verify(userConnectionDBMock).findAll();
        assertEquals(expectedUsers, actualUsers);
    }

    /**
     * Tests the getLastPaymentOfUser method to verify it retrieves the last payment details
     * of a specified user from the database and returns these details.
     */
    @Test
    public void getLastPaymentOfUserNormalTest() {
        BillingDetails expectedDetails = new BillingDetails("1", "123456789012", "123", "John Doe", null);

        when(userConnectionDBMock.getLastPaymentOfUser("1")).thenReturn(Optional.of(expectedDetails));

        BillingDetails actualDetails = userService.getLastPaymentOfUser("1");

        verify(userConnectionDBMock).getLastPaymentOfUser("1");
        assertEquals(expectedDetails, actualDetails);
    }

    /**
     * Tests the getLastPaymentOfUser method to ensure it throws an ItemNotFoundException
     * when no payment records are found for the specified user.
     */
    @Test
    public void getLastPaymentOfUserExceptionTest() {
        when(userConnectionDBMock.getLastPaymentOfUser("1")).thenReturn(Optional.empty());

        Exception exception = assertThrows(ItemNotFoundException.class, () -> userService.getLastPaymentOfUser("1"));

        assertEquals("User by id 1 has no recorded payments", exception.getMessage());
    }

    /**
     * Tests the getAllLikedContentByUser method to verify it correctly retrieves all content
     * liked by a specified user from the database and returns a list of this content.
     */
    @Test
    public void getAllLikedContentByUserNormalTest() {
        List<Content> expectedContent = Arrays.asList(
                new Content("adminId1", "Title1", "link1", null, null, 4.5f),
                new Content("adminId2", "Title2", "link2", null, null, 4.0f)
        );

        when(userConnectionDBMock.getAllLikedContentByUser("1")).thenReturn(Optional.of(expectedContent));

        List<Content> actualContent = userService.getAllLikedContentByUser("1");

        verify(userConnectionDBMock).getAllLikedContentByUser("1");
        assertEquals(expectedContent, actualContent);
    }

    /**
     * Tests the getAllLikedContentByUser method to ensure it throws an ItemNotFoundException
     * when no liked content is found for the specified user.
     */
    @Test
    public void getAllLikedContentByUserExceptionTest() {
        when(userConnectionDBMock.getAllLikedContentByUser("1")).thenReturn(Optional.empty());

        Exception exception = assertThrows(ItemNotFoundException.class, () -> userService.getAllLikedContentByUser("1"));

        assertEquals("User by id 1 did not leave any like", exception.getMessage());
    }

}
