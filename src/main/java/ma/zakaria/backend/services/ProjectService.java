package ma.zakaria.backend.services;

import ma.zakaria.backend.entities.Project;
import ma.zakaria.backend.entities.User;
import ma.zakaria.backend.repositories.ProjectRepository;
import ma.zakaria.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new project
    public Project createProject(String title, String description, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setCreatedDate(new Date());
        project.setUser(user);

        return projectRepository.save(project);
    }

    // Get all projects for a user
    public List<Project> getUserProjects(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return projectRepository.findByUser(user);
    }

    // Get a single project by ID
    public Project getProjectById(Long projectId, String userEmail) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized access to project");
        }

        return project;
    }
}