package com.blog_platform.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {
    // private final UserService userService;

    UserController(UserService userService) {
        // this.userService = userService;
    }

    // @GetMapping("/")
    // public List<User> getAllUsers() {
    // return new String();
    // }
    // public String registerUser(@RequestBody User user) {
    // if (userService.findByUsername(user.getUsername()) != null) {
    // return "Username already exists!";
    // }
    // userService.registerUser(user);
    // return "User registered successfully!";
    // }
}
