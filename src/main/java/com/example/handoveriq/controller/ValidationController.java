package com.example.handoveriq.controller;

import com.example.handoveriq.service.ValidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/handoveriq")
public class ValidationController {
    private final ValidateService validateService;

    // Dependency Injection
    public ValidationController(ValidateService validationService) {
        this.validateService = validationService;
    }

    // API Endpoint: POST http://localhost:8080/api/validation/check?projectId=1&documentId=1
    @PostMapping("/check")
    public ResponseEntity<String> validateDocument(
            @RequestParam String projectId,
            @RequestParam String documentId) {

        // Seedha service ko IDs bhej di
        String aiResult = validateService.validateDocumentForProject(projectId, documentId);

        return ResponseEntity.ok(aiResult);
    }
}
