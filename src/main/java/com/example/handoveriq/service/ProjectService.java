package com.example.handoveriq.service;

import com.example.handoveriq.model.Project;
import com.example.handoveriq.model.User;
import com.example.handoveriq.repository.ProjectRepository;
import com.example.handoveriq.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public String projectDetails(Project project, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist with ID: " + userId));

        Project requestProject = Project.builder()
                .name(project.getName())
                .description(project.getDescription())
                .repositoryUrl(project.getRepositoryUrl())
                .build();

        // Step 4: User ki list mein project add karo (Link establish ho gaya)
        user.getProjects().add(requestProject);

        // Step 5: User ko save karo (Cascade ki wajah se Project auto-save hoga aur FK ban jayegi)
        userRepository.save(user);

        return  user.getId();
    }

    public Project getProjectId(String id) {
        if (!projectRepository.existsById(id))  {
            throw new RuntimeException("Project does not exist with ID: " + id);
        }

        return projectRepository.findById(id).get();
    }
}
