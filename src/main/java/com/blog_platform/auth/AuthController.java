package com.blog_platform.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_platform.ResponseMessage;
import com.blog_platform.user.User;
import com.blog_platform.user.UserService;

// @CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<ResponseMessage> registerUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(new ResponseMessage("Username already exists!"), HttpStatus.BAD_REQUEST);
        }

        userService.registerUser(user);
        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    // public String createToken(@RequestBody User authRequest) throws Exception {
    // try {
    // authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
    // authRequest.getPassword()));
    // } catch (AuthenticationException e) {
    // throw new Exception("Invalid username/password");
    // }
    // final User userDetails =
    // userService.findByUsername(authRequest.getUsername());
    // return jwtUtil.generateToken(userDetails.getUsername(),
    // userDetails.getRole());
    // }
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            User user = userService.authenticateUser(authRequest.getUsername(), authRequest.getPassword());
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole()); // Create a JWT token
            return ResponseEntity.ok(new AuthResponse(token));
            // return new ResponseEntity<>(new ResponseMessage(token), HttpStatus.OK);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ResponseMessage("Invalid credentials!"), HttpStatus.UNAUTHORIZED);

            // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid
            // credentials");
        }
    }

}
