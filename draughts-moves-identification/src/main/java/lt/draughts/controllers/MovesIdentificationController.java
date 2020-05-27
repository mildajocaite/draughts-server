package lt.draughts.controllers;

import lt.draughts.model.DraughtMove;
import lt.draughts.model.DraughtMoveWithCoordinates;
import lt.draughts.model.RequestPossibleMoves;
import lt.draughts.services.MovesIdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MovesIdentificationController {

    @Autowired
    MovesIdentificationService movesIdentificationService;


    @PutMapping("/api/possible-moves")
    public ResponseEntity<?> getPossibleMoves(@Validated @RequestBody RequestPossibleMoves requestPossibleMoves) {
        List<DraughtMoveWithCoordinates> moves = movesIdentificationService.getAllPossibleMoves(
                requestPossibleMoves.getPosition(),
                requestPossibleMoves.getIsWhite());
        return ResponseEntity
                .ok()
                .body(moves);
    }
}