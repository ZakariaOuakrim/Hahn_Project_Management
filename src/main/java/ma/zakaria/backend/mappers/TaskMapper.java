package ma.zakaria.backend.mappers;

import ma.zakaria.backend.dtos.TaskResponse;
import ma.zakaria.backend.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getCreatedDate(),
                task.isCompleted(),
                task.getProject().getId()
        );
    }
}