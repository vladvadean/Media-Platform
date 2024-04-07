package com.example.P1.controller;

import com.example.P1.contract.UserConnectionContract;
import com.example.P1.model.BillingDetails;
import com.example.P1.model.Content;
import com.example.P1.model.User;
import com.example.P1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * communication between the http requests and application
 * responsible for user type requests
 */
@RestController
@RequestMapping("/user")
public class UserResources {
    /**
     * class attribute needed for the CRUD methods implementation
     */
    private final UserConnectionContract userService;

    public UserResources(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        User user1 = userService.addUser(user);

        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User user1 = userService.updateUser(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param id id of the user
     * @return  a response for the query of getting the last payment of the user that has the id id
     */
    @GetMapping("/getLastPayment/{id}")
    public ResponseEntity<?> getLastPaymentOfUser(@PathVariable("id") String id) {
        BillingDetails latestBillingDetails = userService.getLastPaymentOfUser(id);
        return new ResponseEntity<>(latestBillingDetails, HttpStatus.FOUND);
    }

    @GetMapping("/getAllLikedContent/{userId}")
    public ResponseEntity<?> getAllLikedContent(@PathVariable("userId") String userId) {
        List<Content> contentList = userService.getAllLikedContentByUser(userId);
        return new ResponseEntity<>(contentList, HttpStatus.FOUND);
    }
}
