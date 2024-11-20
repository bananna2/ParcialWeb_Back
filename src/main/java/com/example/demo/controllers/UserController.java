package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.services.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create a new user
     * @param userDTO the user data
     * @return ResponseEntity<Void> with status 200
     */
    @PostMapping("/create")
    public void createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
    }

    /**
     * Get a user by ID
     * @param userId the ID of the user
     * @return ResponseEntity<UserDTO> with user data
     */
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Get all users
     * @return ResponseEntity<List<UserDTO>> with all users
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Update a user
     * @param userId the ID of the user to update
     * @param userDTO the updated user data
     * @return ResponseEntity<Void> with status 200
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        userService.updateUser(userId, userDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Delete a user
     * @param userId the ID of the user to delete
     * @return ResponseEntity<Void> with status 200
     */
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}