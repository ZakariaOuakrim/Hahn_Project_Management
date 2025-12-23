package ma.zakaria.backend.services;

import ma.zakaria.backend.entities.Project;
import ma.zakaria.backend.entities.Task;
import ma.zakaria.backend.repositories.ProjectRepository;
import ma.zakaria.backend.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Create a new task
    public Task createTask(Long projectId, String title, String description, Date dueDate, String userEmail) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Check if the project belongs to the user
        if (!project.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized access to project");
        }

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setCreatedDate(new Date());
        task.setCompleted(false);
        task.setProject(project);

        return taskRepository.save(task);
    }

    // Get all tasks for a project
    public List<Task> getProjectTasks(Long projectId, String userEmail) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Check if the project belongs to the user
        if (!project.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized access to project");
        }

        return taskRepository.findByProject(project);
    }

    // Mark a task as completed
    public Task markTaskAsCompleted(Long taskId, String userEmail) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Check if the task's project belongs to the user
        if (!task.getProject().getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized access to task");
        }

        task.setCompleted(true);
        return taskRepository.save(task);
    }

    // Delete a task
    public void deleteTask(Long taskId, String userEmail) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Check if the task's project belongs to the user
        if (!task.getProject().getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized access to task");
        }

        taskRepository.delete(task);
    }
}