package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.UserDAO;
import ru.oksei.JournalAPI.JwtService;
import ru.oksei.JournalAPI.Models.User;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request) {
        User user = userDAO.getUserByLogin(request.getLogin());
        if (user != null && user.getPassword().equals(request.getPassword())) {
            String token = jwtService.generateToken(user.getLogin());
            return ResponseEntity.ok().body(token);
        }
        return ResponseEntity.status(401).body("Invalid login or password");
    }
}