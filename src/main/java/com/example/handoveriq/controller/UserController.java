package com.example.handoveriq.controller;

import com.example.handoveriq.model.Document;
import com.example.handoveriq.model.Project;
import com.example.handoveriq.model.User;
import com.example.handoveriq.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/handoveriq")
@RequiredArgsConstructor
@Slf4j
//@CrossOrigin(origins = "*") // Is controller ke saare APIs par CORS enable ho jayega
public class UserController {
    private final UserService userService;

    // POST MAPPING FOR USER
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String userId = userService.createUser(user);
        return ResponseEntity.ok("User registered successfully: " + userId);
    }


    // POST MAPPING FOR LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        String userId = userService.loginIn(email, password);
        return ResponseEntity.ok(userId);

    }

    @GetMapping("/project/user/{id}")
    public ResponseEntity<List<Project>> getUser(@PathVariable String id) {
        List<Project> listOfProject = userService.getAllProjectList(id);
        return ResponseEntity.ok(listOfProject);
    }

    @GetMapping("/document/user/{id}")
    public ResponseEntity<List<Document>> getDocument(@PathVariable String id) {
        List<Document> listOfDocument = userService.getAllDocumentList(id);
        return ResponseEntity.ok(listOfDocument);
    }


}
