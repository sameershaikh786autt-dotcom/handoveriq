package com.example.handoveriq.service;

import com.example.handoveriq.model.Document;
import com.example.handoveriq.model.Project;
import com.example.handoveriq.model.User;
import com.example.handoveriq.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }
        User setValues = User.builder()
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .projects(user.getProjects())
                .documents(user.getDocuments())
                .build();
         User getUserId = userRepository.save(setValues);
        return getUserId.getId();
    }

    public String loginIn(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));

        if (user.getPassword().equals(password) && user.getEmail().equals(email)) {
            return user.getId();
        }else  {
            throw new RuntimeException("Invalid username or password");
        }

    }

    public List<Project> getAllProjectList(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with id " + id + " not found");
        }

        User user = userRepository.findById(id).get();
        return user.getProjects();
    }

    public List<Document> getAllDocumentList(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with id " + id + " not found");
        }

        User user = userRepository.findById(id).get();
        return user.getDocuments();
    }
}
