package ma.zakaria.backend.mappers;

import ma.zakaria.backend.dtos.ProjectResponse;
import ma.zakaria.backend.entities.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectResponse toResponse(Project project) {
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