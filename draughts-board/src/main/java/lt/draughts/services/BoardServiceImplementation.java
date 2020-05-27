package lt.draughts.services;

import lt.draughts.entities.Board;
import lt.draughts.entities.dto.BoardDTO;
import lt.draughts.entities.dto.CreateBoardDTO;
import lt.draughts.entities.dto.EditBoardDTO;
import lt.draughts.exceptions.BoardIsAssigned;
import lt.draughts.mapper.BoardMapper;
import lt.draughts.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BoardServiceImplementation implements BoardService {

    @Autowired
    BoardMapper boardMapper;

    @Autowired
    BoardRepository boardRepository;

    @Override
    public BoardDTO createBoard(CreateBoardDTO createBoard) {
        Board board = boardMapper.convertCreateBoardDTOToBoard(createBoard);
        boardRepository.saveBoard(board);
        return boardMapper.convertBoardToBoardDTO(board);
    }

    @Override
    public BoardDTO getByID(long id) {
        Board board = boardRepository.getByID(id);
        return boardMapper.convertBoardToBoardDTO(board);
    }

    @Override
    public List<BoardDTO> getAll() {
        return boardRepository.getAll().stream().map(board -> boardMapper.convertBoardToBoardDTO(board)).collect(Collectors.toList());
    }

    @Override
    public void deleteBoard(long id) {
        try {
            boardRepository.delete(id);
        }catch (Exception exception){
            throw new BoardIsAssigned("Lenta yra priskirta naudotojui, todėl negali būti ištrinta.");
        }
    }

    @Override
    public BoardDTO updateBoard(EditBoardDTO editBoardDTO) {
        Board board = boardMapper.convertEditBoardToBoard(editBoardDTO);
        Board oldBoard = boardRepository.getByID(board.getId());
        board.setCreatedDate(oldBoard.getCreatedDate());
        boardRepository.updateBoard(board);
        return boardMapper.convertBoardToBoardDTO(board);
    }
}
