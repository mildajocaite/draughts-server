package lt.draughts.repository;

import lt.draughts.entities.Board;

import java.util.List;

public interface BoardRepository {
    void saveBoard(Board board);

    List<Board> getAll();

    Board getByID(long id);

    Board getByCode(String code);

    void delete(long id);

    void updateBoard(Board board);
}
