package lt.draughts.services;

import lt.draughts.entities.request.CreateRecording;
import lt.draughts.entities.request.GameDTO;
import lt.draughts.entities.request.UpdateRecording;

import java.util.List;

public interface GameServices {
    List<GameDTO> getAll(String email);

    List<GameDTO> getRecordedGames(String email);

    GameDTO createRecording(String email, CreateRecording createRecording);

    GameDTO getByID(long id);

    GameDTO update(UpdateRecording updateRecording);

    void deleteRecording(long id);
}
