package lt.draughts.controllers;

import lt.draughts.entities.dto.CreateBoardDTO;
import lt.draughts.entities.dto.EditBoardDTO;
import lt.draughts.exceptions.BoardIsAssigned;
import lt.draughts.services.BoardService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    @PostMapping("/board")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<?> createBoard(@Validated @RequestBody CreateBoardDTO createBoard) {
        return ResponseEntity
                .ok()
                .body(boardService.createBoard(createBoard));
    }

    @PutMapping("/board")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<?> editBoard(@Validated @RequestBody EditBoardDTO editBoardDTO) {
        return ResponseEntity
                .ok()
                .body(boardService.updateBoard(editBoardDTO));
    }

    @GetMapping("/board")
    public ResponseEntity<?> getBoards() {
        return ResponseEntity
                .ok()
                .body(boardService.getAll());
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoardByID(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(boardService.getByID(id));
    }

    @DeleteMapping("/board/{id}")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<?> deleteBoard(@PathVariable long id) {
        try{
            boardService.deleteBoard(id);
            return ResponseEntity
                    .ok()
                    .body("{\"message\": \"Board deleted successfully.\"}");
        }catch (BoardIsAssigned exception){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", exception.getMessage());
            jsonObject.put("status", "400");
            return ResponseEntity
                    .badRequest()
                    .body(jsonObject.toString());
        }

    }
}
