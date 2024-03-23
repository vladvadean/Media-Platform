package com.example.P1.service;

import com.example.P1.model.Admin;
import com.example.P1.model.AdminNotFoundException;
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

    @Override
    public Admin getAdminById(String id) {
        return adminConnectionDB.findAdminById(id).orElseThrow(() -> new AdminNotFoundException("Admin by id " + id + " was not found"));
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminConnectionDB.findAll();
    }

    @Override
    public Admin addAdmin(Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        return adminConnectionDB.save(admin);
    }

    @Override
    public void deleteAdminById(String id) {
        adminConnectionDB.deleteById(id);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return adminConnectionDB.save(admin);
    }
}