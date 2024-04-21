package com.example.P1.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.P1.model.Admin;
import com.example.P1.repository.AdminConnectionDB;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AdminServiceTest {
    @Mock
    private AdminConnectionDB contractRepositoryMock;

    private AdminService adminService;

    /**
     * Sets up mocks and initializes the service before each test.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.adminService = new AdminService(contractRepositoryMock);
    }

    /**
     * Tests the addAdmin method to ensure it interacts with the database to save an admin.
     * Verifies that the repository's save method is called.
     */
    @Test
    public void addAdminTest() {
        Admin admin = new Admin("v", "v", "v");

        adminService.addAdmin(admin);
        verify(contractRepositoryMock).save(admin);
    }

    /**
     * Tests the updateAdmin method to confirm that the admin's information is updated correctly.
     * Verifies that the repository's save method is invoked to update the admin.
     */
    @Test
    public void updateAdminTest() {
        Admin admin = new Admin("v", "v", "v");

        adminService.updateAdmin(admin);
        verify(contractRepositoryMock).save(admin);
    }

    /**
     * Tests the getAdminById method to check if the correct admin is retrieved by their ID.
     * Verifies that the repository's findAdminById method is called and the correct admin is returned.
     */
    @Test
    public void selectAdminTest() {
        Admin expectedAdmin = new Admin("v", "v", "v");
        expectedAdmin.setId("X");

        when(contractRepositoryMock.findAdminById("X")).thenReturn(Optional.of(expectedAdmin));
        Admin actualAdmin = adminService.getAdminById("X");
        verify(contractRepositoryMock).findAdminById("X");
        assertEquals("The admin retrieved should match the expected admin.", expectedAdmin, actualAdmin);
    }

    /**
     * Tests the getAllAdmins method to ensure it retrieves a list of all admins.
     * Verifies that the repository's findAll method is used and returns the expected list of admins.
     */
    @Test
    public void getAllAdminsTest() {
        List<Admin> expectedAdmins = Arrays.asList(
                new Admin("a", "a@a.com", "admin1"),
                new Admin("b", "b@b.com", "admin2"),
                new Admin("c", "c@c.com", "admin3")
        );

        when(contractRepositoryMock.findAll()).thenReturn(expectedAdmins);
        List<Admin> actualAdmins = adminService.getAllAdmins();
        verify(contractRepositoryMock).findAll();
        assertEquals("The list of admins retrieved should match the expected list.", expectedAdmins, actualAdmins);
    }

    /**
     * Tests the deleteAdminById method to ensure that an admin is properly deleted from the database.
     * Verifies that the repository's deleteById method is called with the correct ID.
     */
    @Test
    public void deleteAdminTest() {
        String adminId = "X";
        adminService.deleteAdminById(adminId);
        verify(contractRepositoryMock).deleteById(adminId);
    }
}
