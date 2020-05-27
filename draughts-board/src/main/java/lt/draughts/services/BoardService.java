package lt.draughts.services;

import lt.draughts.entities.dto.BoardDTO;
import lt.draughts.entities.dto.CreateBoardDTO;
import lt.draughts.entities.dto.EditBoardDTO;

import java.util.List;

public interface BoardService {
    BoardDTO createBoard(CreateBoardDTO createBoard);

    BoardDTO getByID(long id);

    List<BoardDTO> getAll();

    void deleteBoard(long id);

    BoardDTO updateBoard(EditBoardDTO editBoardDTO);
}
