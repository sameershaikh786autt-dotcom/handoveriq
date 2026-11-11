package com.example.handoveriq;

import com.example.handoveriq.model.Document;
import com.example.handoveriq.model.Project;

public class PromptBuilder {
    public static String buildComprehensivePrompt(Project project, Document document) {
        return "You are an expert Brand Governance AI.\n\n" +

                "=== PROJECT DETAILS (BRAND GUIDELINES) ===\n" +
                "Project Name: " + project.getName() + "\n" +
                "Project Description (Treat this description as the strict brand guidelines and rules to follow): " + project.getDescription() + "\n\n" +

                "=== DOCUMENT DETAILS ===\n" +
                "File Name: " + document.getFileName() + "\n" +
                "File Type: " + document.getFileType() + "\n" +
                "Document Content: " + document.getExtractedText() + "\n\n" +

                "=== YOUR TASK ===\n" +
                "Please provide a structured 2-part response:\n" +
                "Part 1 (Project Analysis): Read the Project Description and give a brief 2-line summary of what this brand stands for and its core rules.\n" +
                "Part 2 (Document Validation): Check the Document Content against the rules mentioned in the Project Description. Clearly explain any violations. If there are no violations, explicitly state that the document complies with the project guidelines.";
    }
}
