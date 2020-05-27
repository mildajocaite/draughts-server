package lt.draughts.controllers;

import lt.draughts.exceptions.BoardDoesNotExist;
import lt.draughts.services.TaskForUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TaskForUsersController {

    @Autowired
    TaskForUserService taskForUserService;

    @GetMapping("/taskForUser")
    public ResponseEntity<?> getAll() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity
                .ok()
                .body(taskForUserService.getAll(email));
    }

    @GetMapping("/taskForUserGetOrCreate/{id}")
    public ResponseEntity<?> getOrCreate(@PathVariable long id) {
        try{
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            return ResponseEntity
                    .ok()
                    .body(taskForUserService.createTaskForUser(id, email));
        }
        catch (BoardDoesNotExist exception){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", exception.getMessage());
            jsonObject.put("status", "400");
            return ResponseEntity
                    .badRequest()
                    .body(jsonObject.toString());
        }
    }

    @GetMapping("/taskForUser/{id}")
    public ResponseEntity<?> getByID(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(taskForUserService.getByID(id));
    }

    @GetMapping("/checkStartPosition/{id}")
    public ResponseEntity<?> checkStartPosition(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(taskForUserService.checkIfStartPositionCorrect(id));
    }

    @GetMapping("/checkIfSolutionRight/{id}")
    public ResponseEntity<?> checkIfSolutionRight(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(taskForUserService.checkIfSolutionRight(id));
    }

    @GetMapping("/taskForUserByTask/{id}")
    public ResponseEntity<?> getByTaskID(@PathVariable long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity
                .ok()
                .body(taskForUserService.getByTaskIDEmail(email, id));
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        taskForUserService.deleteUser(id);
        return ResponseEntity
                .ok()
                .body("{\"message\": \"Naudotojas ištrintas sėkmingai.\"}");
    }
}
