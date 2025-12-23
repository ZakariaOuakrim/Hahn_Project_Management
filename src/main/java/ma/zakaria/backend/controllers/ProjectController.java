package ma.zakaria.backend.controllers;

import jakarta.validation.Valid;
import ma.zakaria.backend.dtos.CreateProjectRequest;
import ma.zakaria.backend.dtos.ProjectResponse;
import ma.zakaria.backend.entities.Project;
import ma.zakaria.backend.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> createProject(
            @Valid @RequestBody CreateProjectRequest request,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName(); // Get email from JWT token

            Project project = projectService.createProject(
                    request.getTitle(),
                    request.getDescription(),
                    userEmail
            );

            ProjectResponse response = convertToResponse(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating project: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getUserProjects(Authentication authentication) {
        try {
            String userEmail = authentication.getName();

            List<Project> projects = projectService.getUserProjects(userEmail);

            List<ProjectResponse> responses = projects.stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(responses);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching projects: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();

            Project project = projectService.getProjectById(id, userEmail);
            ProjectResponse response = convertToResponse(project);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching project: " + e.getMessage());
        }
    }

    private ProjectResponse convertToResponse(Project project) {
        int totalTasks = project.getTasks() != null ? project.getTasks().size() : 0;
        int completedTasks = project.getTasks() != null
                ? (int) project.getTasks().stream().filter(task -> task.isCompleted()).count()
                : 0;
        double progressPercentage = totalTasks > 0
                ? (completedTasks * 100.0) / totalTasks
                : 0.0;

        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getCreatedDate(),
                totalTasks,
                completedTasks,
                progressPercentage
        );
    }
}