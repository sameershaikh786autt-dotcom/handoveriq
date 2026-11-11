package com.example.handoveriq.service;

import com.example.handoveriq.model.Document;
import com.example.handoveriq.model.User;
import com.example.handoveriq.repository.DocumentRepository;
import com.example.handoveriq.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;

    public String documentDetails(String userId, Document document) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist with ID: " + userId));

        Document response = Document.builder()
                .fileName(document.getFileName())
                .filePath(document.getFilePath())
                .fileSize(document.getFileSize())
                .fileType(document.getFileType())
                .extractedText(document.getExtractedText())
                .build();

        user.getDocuments().add(response);
        documentRepository.save(document);

        return "Document details saved successfully";
    }

    public Document getDocumentDetailsById(String documentId) {
        if(!documentRepository.existsById(documentId)) {
            throw new RuntimeException("Document does not exist with ID: " + documentId);
        }

        return documentRepository.findById(documentId).get();
    }
}
