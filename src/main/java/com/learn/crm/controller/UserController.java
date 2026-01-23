package com.learn.crm.controller;

import com.learn.crm.model.User;
import com.learn.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/public/register")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>("User added.", HttpStatus.CREATED);
    }


    @GetMapping
    public String admin() {
        return "Admin access granted";
    }


}
