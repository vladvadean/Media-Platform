package com.example.P1.controller;

import com.example.P1.model.Admin;
import com.example.P1.model.Content;
import com.example.P1.service.AdminService;
import com.example.P1.service.ContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminResources {
    private final AdminService adminService;

    public AdminResources(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.FOUND);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") String id) {
        Admin admin = adminService.getAdminById(id);
        return new ResponseEntity<>(admin, HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Admin> addContent(@RequestBody Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        Admin admin1 = adminService.addAdmin(admin);

        return new ResponseEntity<>(admin1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> updateUser(@RequestBody Admin admin) {
        Admin admin1 = adminService.updateAdmin(admin);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") String id) {
        adminService.deleteAdminById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
