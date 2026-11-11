package com.example.handoveriq.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT") // Description badi ho sakti hai
    private String description;

    private String repositoryUrl; // GitHub/GitLab link (Optional but useful)

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    // User ka koi reference nahi aayega (Unidirectional ki wajah se)
}
