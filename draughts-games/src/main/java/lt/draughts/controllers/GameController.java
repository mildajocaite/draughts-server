package lt.draughts.controllers;

import lt.draughts.entities.request.CreateRecording;
import lt.draughts.entities.request.UpdateRecording;
import lt.draughts.exceptions.BoardCannotBeNull;
import lt.draughts.exceptions.BoardIsBusy;
import lt.draughts.exceptions.BoardIsNotAssigned;
import lt.draughts.exceptions.SamePlayers;
import lt.draughts.services.GameServices;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class GameController {

    @Autowired
    GameServices gameServices;

    @GetMapping("/game")
    public ResponseEntity<?> getGames() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity
                .ok()
                .body(gameServices.getRecordedGames(email));
    }

    @PostMapping("/recording")
    public ResponseEntity<?> createRecording(@Validated @RequestBody CreateRecording createRecordedGame) {
       try{
           String email = SecurityContextHolder.getContext().getAuthentication().getName();
           return ResponseEntity
                   .ok()
                   .body(gameServices.createRecording(email, createRecordedGame));
       }
       catch (BoardCannotBeNull | BoardIsNotAssigned | BoardIsBusy | SamePlayers exception){
           JSONObject jsonObject = new JSONObject();
           jsonObject.put("message", exception.getMessage());
           jsonObject.put("status", "400");
           return ResponseEntity
                   .badRequest()
                   .body(jsonObject.toString());
       }
    }

    @GetMapping("/recording")
    public ResponseEntity<?> getRecording() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity
                .ok()
                .body(gameServices.getAll(email));
    }

    @DeleteMapping("/recording/{id}")
    public ResponseEntity<?> deleteRecording(@PathVariable long id) {
        gameServices.deleteRecording(id);
        return ResponseEntity
                .ok()
                .body("{\"message\": \"Recording deleted successfully.\"}");
    }

    @GetMapping("/recording/{id}")
    public ResponseEntity<?> getRecordingByID(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(gameServices.getByID(id));
    }

    @PutMapping("/recording")
    public ResponseEntity<?> updateRecording(@Validated @RequestBody UpdateRecording updateRecording) {
        return ResponseEntity
                .ok()
                .body(gameServices.update(updateRecording));
    }
}
