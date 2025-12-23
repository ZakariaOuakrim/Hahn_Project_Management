package ma.zakaria.backend.controllers;

import jakarta.validation.Valid;
import ma.zakaria.backend.dtos.CreateTaskRequest;
import ma.zakaria.backend.dtos.TaskResponse;
import ma.zakaria.backend.entities.Task;
import ma.zakaria.backend.mappers.TaskMapper;
import ma.zakaria.backend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    // Create a new task
    @PostMapping
    public ResponseEntity<?> createTask(
            @PathVariable Long projectId,
            @Valid @RequestBody CreateTaskRequest request,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();

            Task task = taskService.createTask(
                    projectId,
                    request.getTitle(),
                    request.getDescription(),
                    request.getDueDate(),
                    userEmail
            );

            TaskResponse response = taskMapper.toResponse(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating task: " + e.getMessage());
        }
    }

    // Get all tasks for a project
    @GetMapping
    public ResponseEntity<?> getProjectTasks(
            @PathVariable Long projectId,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();

            List<Task> tasks = taskService.getProjectTasks(projectId, userEmail);

            List<TaskResponse> responses = tasks.stream()
                    .map(taskMapper::toResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(responses);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching tasks: " + e.getMessage());
        }
    }

    // Mark task as completed
    @PutMapping("/{taskId}/complete")
    public ResponseEntity<?> markTaskAsCompleted(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();

            Task task = taskService.markTaskAsCompleted(taskId, userEmail);
            TaskResponse response = taskMapper.toResponse(task);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating task: " + e.getMessage());
        }
    }

    // Delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();

            taskService.deleteTask(taskId, userEmail);

            return ResponseEntity.ok("Task deleted successfully");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting task: " + e.getMessage());
        }
    }
}
