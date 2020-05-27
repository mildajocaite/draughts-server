package lt.draughts.repository;

import lt.draughts.entities.Board;
import lt.draughts.exceptions.BoardIsAssigned;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BoardRepositoryImplementation implements BoardRepository {
    @Autowired
    EntityManager entityManager;

    @Override
    public void saveBoard(Board board) {
        entityManager.persist(board);
    }

    @Override
    public List<Board> getAll() {
        return entityManager.createNamedQuery("Board.GetAll", Board.class)
                .getResultList();
    }

    @Override
    public Board getByID(long id) {
        return entityManager.find(Board.class, id);
    }

    @Override
    public Board getByCode(String code) {
        return entityManager
                .createNamedQuery("Board.GetByCode", Board.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    @Override
    public void delete(long id) {
        try {
            Board board = entityManager.find(Board.class, id);
            entityManager.remove(board);
        }catch (Exception exception){
            throw new BoardIsAssigned("Lenta yra priskirta naudotojui, todėl negali būti ištrinta.");
        }
    }

    @Override
    public void updateBoard(Board board) {
        entityManager.merge(board);
    }
}
