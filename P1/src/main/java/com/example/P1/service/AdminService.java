package com.example.P1.service;

import com.example.P1.model.Admin;
import com.example.P1.model.Content;
import com.example.P1.model.ItemNotFoundException;
import com.example.P1.repository.AdminConnectionDB;
import com.example.P1.contract.AdminConnectionContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * this class implements the CRUD admin contract
 * and calls all the methods needed already implemented
 * in the AdminConnectionDB interface
 */
@Service
public class AdminService implements AdminConnectionContract {
    /**
     * interface that contains references to the JPA CRUD operations
     * and used for interface dependency injection
     */
    private final AdminConnectionDB adminConnectionDB;

    @Autowired
    public AdminService(AdminConnectionDB adminConnectionDB) {
        this.adminConnectionDB = adminConnectionDB;
    }

    /**
     * Retrieves an {@link Admin} entity based on the provided identifier.
     * If the admin does not exist, it throws an {@link ItemNotFoundException}.
     *
     * @param id the unique identifier of the admin to be retrieved
     * @return the admin corresponding to the provided id
     * @throws ItemNotFoundException if no admin is found with the provided id
     */
    @Override
    public Admin getAdminById(String id) {
        return adminConnectionDB.findAdminById(id).orElseThrow(() -> new ItemNotFoundException("Admin by id " + id + " was not found"));
    }

    /**
     * Retrieves all {@link Admin} entities stored in the database.
     *
     * @return a list of all admins
     */
    @Override
    public List<Admin> getAllAdmins() {
        return adminConnectionDB.findAll();
    }

    /**
     * Adds a new {@link Admin} to the database. Generates and sets a new unique identifier for the admin before saving.
     *
     * @param admin the admin entity to be added
     * @return the saved admin entity with an assigned id
     */
    @Override
    public Admin addAdmin(Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        return adminConnectionDB.save(admin);
    }

    /**
     * Deletes an {@link Admin} from the database based on the provided identifier.
     * If the admin with the specified id does not exist, this operation does not perform any action.
     *
     * @param id the unique identifier of the admin to be deleted
     */
    @Override
    public void deleteAdminById(String id) {
        adminConnectionDB.deleteById(id);
    }

    /**
     * Updates the information of an existing {@link Admin} in the database.
     * Assumes the admin's identifier exists in the database; otherwise, it will create a new record.
     *
     * @param admin the admin entity with updated information to save
     * @return the updated admin entity
     */
    @Override
    public Admin updateAdmin(Admin admin) {
        return adminConnectionDB.save(admin);
    }

    /**
     * Retrieves all {@link Content} items added by a specific admin identified by {@code adminId}.
     * Throws {@link ItemNotFoundException} if the admin does not exist or if the admin has not added any content.
     *
     * @param adminId the unique identifier of the admin whose content is to be retrieved
     * @return a list of content items added by the specified admin
     * @throws ItemNotFoundException if the admin has not added any content or does not exist
     */
    @Override
    public List<Content> getAllContentByAdminId(String adminId) {
        return adminConnectionDB.getAllContentByAdminId(adminId).orElseThrow(() -> new ItemNotFoundException("Admin by id " + adminId + " did not insert any content"));
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminConnectionDB.getAdminByUsername(username).orElseThrow(() -> new ItemNotFoundException("Admin by username " + username + " was not found"));
    }

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        return adminConnectionDB.getAdminByUsernameAndPassword(username,password).orElseThrow(() -> new ItemNotFoundException("Admin with those credentials not found"));
    }
}