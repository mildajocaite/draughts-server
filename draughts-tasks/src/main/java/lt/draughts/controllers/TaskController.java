package lt.draughts.controllers;

import lt.draughts.entities.dto.CreateTask;
import lt.draughts.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/task")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<?> createTask(@Validated @RequestBody CreateTask createTask) {
        return ResponseEntity
                .ok()
                .body(taskService.createTask(createTask));
    }

    @GetMapping("/task")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<?> getTasks() {
        return ResponseEntity
                .ok()
                .body(taskService.getAll());
    }

    @GetMapping("/task/{id}")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<?> getTaskByID(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(taskService.getByID(id));
    }

    @DeleteMapping("/task/{id}")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<?> deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity
                .ok()
                .body("{\"message\": \"Task deleted successfully.\"}");
    }
}
