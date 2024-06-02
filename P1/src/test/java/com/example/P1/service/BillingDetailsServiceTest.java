package com.example.P1.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import com.example.P1.model.BillingDetails;
import com.example.P1.model.ItemNotFoundException;
import com.example.P1.repository.BillingDetailsConnectionDB;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BillingDetailsServiceTest {
    @Mock
    private BillingDetailsConnectionDB billingDetailsConnectionDBMock;

    private BillingDetailsService billingDetailsService;

    /**
     * Sets up mocks and initializes the service before each test.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        billingDetailsService = new BillingDetailsService(billingDetailsConnectionDBMock);
    }



    /**
     * Tests retrieval of all billing details.
     * Verifies that all billing details are fetched correctly from the database.
     */
    @Test
    public void getAllBillingDetailsTest() {
        List<BillingDetails> expected = Arrays.asList(
                new BillingDetails("user1", "1234567890123456", "123", "John Doe", LocalDateTime.now()),
                new BillingDetails("user2", "2345678901234567", "456", "Jane Roe", LocalDateTime.now())
        );

        when(billingDetailsConnectionDBMock.findAll()).thenReturn(expected);

        List<BillingDetails> actual = billingDetailsService.getAllBillingDetails();

        verify(billingDetailsConnectionDBMock).findAll();
        assertEquals(expected, actual);
    }

    /**
     * Tests the addition of new billing details.
     * Verifies that the billing details are saved in the database correctly.
     */
    @Test
    public void addBillingDetailsTest() {
        BillingDetails billingDetails = new BillingDetails("user1", "1234567890123456", "123", "John Doe", LocalDateTime.now());
        billingDetails.setId("1");

        when(billingDetailsConnectionDBMock.save(billingDetails)).thenReturn(billingDetails);

        BillingDetails saved = billingDetailsService.addBillingDetails(billingDetails);

        verify(billingDetailsConnectionDBMock).save(billingDetails);
        assertEquals(billingDetails, saved);
    }

    /**
     * Tests the updating of existing billing details.
     * Ensures that the updated billing details are correctly saved and returned.
     */
    @Test
    public void updateBillingDetailsTest() {
        BillingDetails billingDetails = new BillingDetails("user1", "1234567890123456", "123", "John Updated", LocalDateTime.now());
        billingDetails.setId("1");

        when(billingDetailsConnectionDBMock.save(billingDetails)).thenReturn(billingDetails);

        BillingDetails updated = billingDetailsService.updateBillingDetails(billingDetails);

        verify(billingDetailsConnectionDBMock).save(billingDetails);
        assertEquals(billingDetails, updated);
    }

    /**
     * Tests the deletion of billing details by user ID.
     * Ensures that the billing details are removed correctly from the database.
     */
    @Test
    public void deleteBillingDetailsByIdTest() {
        String userId = "1";

        doNothing().when(billingDetailsConnectionDBMock).deleteById(userId);
        billingDetailsService.deleteBillingDetailsById(userId);

        verify(billingDetailsConnectionDBMock).deleteById(userId);
    }
}
