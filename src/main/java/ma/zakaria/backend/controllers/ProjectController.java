package ma.zakaria.backend.controllers;

import jakarta.validation.Valid;
import ma.zakaria.backend.dtos.CreateProjectRequest;
import ma.zakaria.backend.dtos.ProjectResponse;
import ma.zakaria.backend.entities.Project;
import ma.zakaria.backend.mappers.ProjectMapper;
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

    @Autowired
    private ProjectMapper projectMapper;

    // Create a new project
    @PostMapping
    public ResponseEntity<?> createProject(
            @Valid @RequestBody CreateProjectRequest request,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();

            Project project = projectService.createProject(
                    request.getTitle(),
                    request.getDescription(),
                    userEmail
            );

            ProjectResponse response = projectMapper.toResponse(project);
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
                    .map(projectMapper::toResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(responses);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching projects: " + e.getMessage());
        }
    }

    // Get a specific project by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();

            Project project = projectService.getProjectById(id, userEmail);
            ProjectResponse response = projectMapper.toResponse(project);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching project: " + e.getMessage());
        }
    }
}