package com.farteaga.arkam.msvc_users.controller;

import com.farteaga.arkam.msvc_users.model.User;
import com.farteaga.arkam.msvc_users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User createdUser = userService.newUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // localhost:8080/api/users/1
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) { //id=1
        User user = userService.getUser(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // localhost:8080/api/users/1
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user){
        User updateUser = userService.updateUser(id, user);
        if (updateUser == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    // localhost:8080/api/users/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/orderFirstname")
    public ResponseEntity<List<User>> orderNameAsc(){
        List<User> newList = userService.findAllOrder();
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchName (@RequestParam String name){
        List<User> users = userService.searchUsersByName(name);
        return users.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
    }






}