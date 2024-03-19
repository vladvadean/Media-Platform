package com.example.P1.service;

import com.example.P1.model.Admin;
import com.example.P1.model.AdminNotFoundException;
import com.example.P1.model.User;
import com.example.P1.model.UserNotFoundException;
import com.example.P1.repository.AdminConnection;
import com.example.P1.repository.UserConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminService {
    private final AdminConnection adminConnection;

    @Autowired
    public AdminService(AdminConnection adminConnection) {
        this.adminConnection = adminConnection;
    }

    public Admin getAdminById(String id) {
        return adminConnection.findAdminById(id).orElseThrow(() -> new AdminNotFoundException("Admin by id " + id + " was not found"));
    }

    public List<Admin> getAllAdmins() {
        return adminConnection.findAll();
    }

    public Admin addAdmin(Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        return adminConnection.save(admin);
    }

    public void deleteAdminById(String id) {
        adminConnection.deleteById(id);
    }

    public Admin updateAdmin(Admin admin) {
        return adminConnection.save(admin);
    }
}