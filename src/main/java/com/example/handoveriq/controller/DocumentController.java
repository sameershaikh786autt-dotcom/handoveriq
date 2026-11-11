package com.example.handoveriq.controller;

import com.example.handoveriq.model.Document;
import com.example.handoveriq.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/handoveriq")
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/document/{userId}")
    ResponseEntity<String> detailsDocument(@PathVariable String userId,
                                           @RequestBody Document document
    ) {
        String response = documentService.documentDetails(userId, document);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{documentId}")
    ResponseEntity<Document> getDocument(@PathVariable String documentId) {
        Document response = documentService.getDocumentDetailsById(documentId);
        return ResponseEntity.ok(response);
    }

}
