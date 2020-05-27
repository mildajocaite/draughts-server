package lt.draughts.controllers;

import lt.draughts.entities.User;
import lt.draughts.entities.dto.UserDTO;
import lt.draughts.entities.request.RegistrationForm;
import lt.draughts.exceptions.BoardIsAssigned;
import lt.draughts.exceptions.EmailAlreadyExists;
import lt.draughts.mapper.UserMapper;
import lt.draughts.services.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @PostMapping("/user")
    @PreAuthorize("hasRole('ROLE_COACH')")
    @ExceptionHandler({EmailAlreadyExists.class})
    public ResponseEntity<?> createUser(@Validated @RequestBody RegistrationForm registrationForm) {
        try{
            User createdUser = userService.createUser(registrationForm);
            return ResponseEntity
                    .ok()
                    .body(userMapper.convertUserToUserDTO(createdUser));
        }
        catch(EmailAlreadyExists | BoardIsAssigned exception){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", exception.getMessage());
            jsonObject.put("status", "400");
            return ResponseEntity
                    .badRequest()
                    .body(jsonObject.toString());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity
                .ok()
                .body(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(userService.getUserById(id));
    }

    @PutMapping("/user")
    @PreAuthorize("hasRole('ROLE_COACH')")
    public ResponseEntity<?> updateUser(@Validated @RequestBody UserDTO userDTO) {
        try{
            return ResponseEntity
                    .ok()
                    .body(userService.updateUser(userDTO));
        }
        catch(EmailAlreadyExists | BoardIsAssigned exception){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", exception.getMessage());
            jsonObject.put("status", "400");
            return ResponseEntity
                    .badRequest()
                    .body(jsonObject.toString());
        }
    }

    @GetMapping("/coach")
    @PreAuthorize("hasRole('ROLE_COACH')")
    public ResponseEntity<?> getCoaches() {
        return ResponseEntity
                .ok()
                .body(userService.getCoaches());
    }

    @GetMapping("/student")
    @PreAuthorize("hasRole('ROLE_COACH')")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity
                .ok()
                .body(userService.getStudents());
    }
}
