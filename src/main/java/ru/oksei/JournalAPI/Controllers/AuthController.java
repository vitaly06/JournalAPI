package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.Services.JwtService;
import ru.oksei.JournalAPI.Models.User;
import ru.oksei.JournalAPI.Services.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController{
    private final UserService userService;
    private JwtService jwtService;
    @Autowired
    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request) {
        User user = userService.getUserByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user != null && user.getPassword().equals(request.getPassword())) {
            String token = jwtService.generateToken(user.getLogin());
            return ResponseEntity.ok().body(token);
        }
        return ResponseEntity.status(401).body("Invalid login or password");
    }
}