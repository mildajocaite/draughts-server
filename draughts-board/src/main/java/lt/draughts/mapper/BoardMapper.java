package lt.draughts.mapper;

import lt.draughts.entities.Board;
import lt.draughts.entities.dto.BoardDTO;
import lt.draughts.entities.dto.CreateBoardDTO;
import lt.draughts.entities.dto.EditBoardDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BoardMapper {
    public Board convertCreateBoardDTOToBoard(CreateBoardDTO createBoardDTO) {
        Board board = new Board();
        board.setCode(createBoardDTO.getCode());
        board.setName(createBoardDTO.getName());
        board.setCreatedDate(new Date());
        return board;
    }

    public BoardDTO convertBoardToBoardDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setCode(board.getCode());
        boardDTO.setName(board.getName());
        boardDTO.setCreatedDate(board.getCreatedDate());
        return boardDTO;
    }

    public Board convertBoardDTOToBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setId(boardDTO.getId());
        board.setCode(boardDTO.getCode());
        board.setName(boardDTO.getName());
        board.setCreatedDate(boardDTO.getCreatedDate());
        return board;
    }

    public Board convertEditBoardToBoard(EditBoardDTO editBoardDTO) {
        Board board = new Board();
        board.setId(editBoardDTO.getId());
        board.setCode(editBoardDTO.getCode());
        board.setName(editBoardDTO.getName());
        return board;
    }
}
