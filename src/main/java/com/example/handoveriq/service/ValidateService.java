package com.example.handoveriq.service;

import com.example.handoveriq.PromptBuilder;
import com.example.handoveriq.model.Document;
import com.example.handoveriq.model.Project;
import com.example.handoveriq.repository.DocumentRepository;
import com.example.handoveriq.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidateService {
    private final GeminiService geminiService;
    private final ProjectRepository projectRepository;
    private final DocumentRepository documentRepository;

    public ValidateService(GeminiService geminiService,
                           ProjectRepository projectRepository,
                           DocumentRepository documentRepository) {
        this.geminiService = geminiService;
        this.projectRepository = projectRepository;
        this.documentRepository = documentRepository;
    }

    public  String validateDocumentForProject(String projectId , String documentId){
        // 1. Fetching from DB
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        // PROMPT
        String prompt = PromptBuilder.buildComprehensivePrompt(project, document);


        return geminiService.getGeminiResponse(prompt);

    }
}
