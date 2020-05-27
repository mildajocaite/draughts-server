package lt.draughts.repositories;

import lt.draughts.entities.Game;
import lt.draughts.entities.RecordingStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class GameRepositoryImpl implements GameRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Game> getAll() {
        return entityManager
                .createNamedQuery("Game.GetAll", Game.class)
                .getResultList();
    }

    public List<Game> getRecordedGames() {
        return entityManager
                .createNamedQuery("Game.GetAll", Game.class)
                .getResultList()
                .stream()
                .filter(game -> game.getStatus() == RecordingStatus.RECORDED)
                .collect(Collectors.toList());
    }

    public void createRecording(Game game) {
        entityManager.persist(game);
    }

    public Game getGameByID(long id) {
        return entityManager.find(Game.class, id);
    }

    @Override
    public void update(Game game) {
        entityManager.merge(game);
    }

    @Override
    public void delete(long id) {
        Game game = getGameByID(id);
        entityManager.remove(game);
    }
}
