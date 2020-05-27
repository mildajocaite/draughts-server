package lt.draughts.controllers;

import lt.draughts.services.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RecordingsController {

    @Autowired
    RecordingService recordingService;

    @GetMapping("/api/moves-thingSpeak/{boardID}")
    public ResponseEntity<?> getMoves(@PathVariable long boardID) {
        List<String> moves = recordingService.getMoves(boardID + "", null);
        return ResponseEntity
                .ok()
                .body(moves);
    }
}