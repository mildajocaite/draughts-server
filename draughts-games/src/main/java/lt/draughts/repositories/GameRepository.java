package lt.draughts.repositories;

import lt.draughts.entities.Game;

import java.util.List;

public interface GameRepository {
    void createRecording(Game game);

    void update(Game game);

    void delete(long id);

    Game getGameByID(long id);

    List<Game> getAll();

    List<Game> getRecordedGames();
}
