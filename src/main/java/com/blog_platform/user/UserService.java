package com.blog_platform.user;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        user.setRole("ROLE_USER"); // Default role for normal users
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public String getUserIdString(User user) {
        return userRepository.findByUsername(user.getUsername()).getId();
    }

    public boolean checkPassword(User user, String rawPassword) {
        if (user != null) {
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !checkPassword(user, password)) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return user;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

}
