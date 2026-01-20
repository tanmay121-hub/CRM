package com.learn.crm.controller;

import com.learn.crm.model.User;
import com.learn.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
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

    @PostMapping("/public/register")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>("User added.", HttpStatus.CREATED);
    }

    @GetMapping("/public")
    public String publicApi() {
        return "This is public";
    }

    @GetMapping("/user")
    public String userApi() {
        return "Hello USER";
    }

    @GetMapping("/admin")
    public String adminApi() {
        return "Hello ADMIN";
    }
}
