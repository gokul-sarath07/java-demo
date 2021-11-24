package com.example.demo.controller;

import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User result = userDAO.insert(user);
        if (result != null) {
            return new ResponseEntity<User>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("User not created.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userDAO.findAll();
        if(users.size() > 0) {
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No users avaliable.", HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserWithId(@PathVariable("id") String id) {
        Optional<User> result = userDAO.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<User>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("User with id doesn't exist.", HttpStatus.BAD_REQUEST);
    }
}
