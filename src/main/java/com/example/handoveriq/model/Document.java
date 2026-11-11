package com.example.handoveriq.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String fileName; // e.g., "architecture_v1.pdf"

    @Column(nullable = false)
    private String filePath; // e.g., "uploads/architecture_v1.pdf" (Server par kahan save hai)

    private String fileType; // e.g., "application/pdf"

    private long fileSize; // File ka size bytes mein (UI mein dikhane ke liye accha hai)

    @Column(columnDefinition = "TEXT") // TEXT lagana bohot zaroori hai, warna choti string size limit par error dega
    private String extractedText; // PDFBox se nikala hua raw text yahan aayega

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime uploadedDate;
}
