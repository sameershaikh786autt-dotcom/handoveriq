package com.example.handoveriq.controller;

import com.example.handoveriq.model.Project;
import com.example.handoveriq.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/handoveriq")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/detail/{userId}")
    public ResponseEntity<String> projectDetails(
            @PathVariable String userId,
            @RequestBody Project project
    ) {
        String projectId = projectService.projectDetails(project, userId);
        return ResponseEntity.ok(projectId);

    }


    @GetMapping("/project/{id}")
    ResponseEntity<Project> getProject(@PathVariable String id) {
        Project response = projectService.getProjectId(id);
        return ResponseEntity.ok(response);
    }


}
