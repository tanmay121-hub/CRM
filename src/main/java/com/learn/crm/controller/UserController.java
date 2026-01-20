package com.learn.crm.controller;

import com.learn.crm.model.User;
import com.learn.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String userSecurity(){
        return """
                <!DOCTYPE html>
                <html>
                <head>
                </head>
                <body>
                    <h1>WEB PAGE</h1>
                </body>
                </html>""";
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>("User added.", HttpStatus.CREATED);
    }
}
