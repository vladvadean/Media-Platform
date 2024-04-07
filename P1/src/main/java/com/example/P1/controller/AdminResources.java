package com.example.P1.controller;

import com.example.P1.contract.AdminConnectionContract;
import com.example.P1.model.Admin;
import com.example.P1.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * communication between the http requests and application
 * responsible for admin type requests
 */
@RestController
@RequestMapping("/admin")
public class AdminResources {
    /**
     * class attribute needed for the CRUD methods implementation
     */
    private final AdminConnectionContract adminService;

    @Autowired
    public AdminResources(AdminConnectionContract adminService) {
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
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        Admin admin1 = adminService.addAdmin(admin);

        return new ResponseEntity<>(admin1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
        Admin admin1 = adminService.updateAdmin(admin);
        return new ResponseEntity<>(admin1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdminById(@PathVariable("id") String id) {
        adminService.deleteAdminById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param adminId the id of the admin
     * @return get a response of the query for getting all the content that the admin with the id adminId added
     */
    @GetMapping("/getContent/{adminId}")
    public ResponseEntity<?> getAllContentByAdminId(@PathVariable("adminId") String adminId) {
        List<Content> contentList = adminService.getAllContentByAdminId(adminId);
        return new ResponseEntity<>(contentList, HttpStatus.FOUND);
    }
}
