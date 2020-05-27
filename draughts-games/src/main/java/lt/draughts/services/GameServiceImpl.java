package lt.draughts.services;

import lt.draughts.entities.*;
import lt.draughts.entities.request.CreateRecording;
import lt.draughts.entities.request.GameDTO;
import lt.draughts.entities.request.UpdateRecording;
import lt.draughts.exceptions.BoardCannotBeNull;
import lt.draughts.exceptions.BoardIsBusy;
import lt.draughts.exceptions.BoardIsNotAssigned;
import lt.draughts.exceptions.SamePlayers;
import lt.draughts.mappers.GameMapper;
import lt.draughts.repositories.GameRepository;
import lt.draughts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameServiceImpl implements GameServices {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameMapper gameMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecordingService recordingService;

    public List<GameDTO> getAll(String email) {
        User user = userRepository.findByEmail(email);
        List<Game> games;
        if (user.getRole().getName() == RoleName.ROLE_COACH) {
            games = gameRepository.getAll();
        } else {
            games = gameRepository.getAll().stream().filter((elem -> {
                return elem.getPlayer1().getEmail().equals(email) || elem.getPlayer2().getEmail().equals(email);
            })).collect(Collectors.toList());
        }
        games.forEach(game -> {
            if (game.getStatus() == RecordingStatus.RECORDING) {
                this.getMovesForGame(game);
            }
        });
        List<GameDTO> gamesDTO = games.stream()
                .map((game) -> gameMapper.convertGameToGameDTO(game))
                .collect(Collectors.toList());
        gamesDTO.sort(Comparator.comparing(GameDTO::getStartRecording).reversed());
        return gamesDTO;
    }

    public List<GameDTO> getRecordedGames(String email) {
        User user = userRepository.findByEmail(email);
        List<Game> games;
        if (user.getRole().getName() == RoleName.ROLE_COACH) {
            games = gameRepository.getRecordedGames();
        } else {
            games = gameRepository.getRecordedGames().stream().filter((elem -> {
                return elem.getPlayer1().getEmail().equals(email) || elem.getPlayer2().getEmail().equals(email);
            })).collect(Collectors.toList());
        }
        List<GameDTO> gamesDTO = games.stream().map((game) -> gameMapper.convertGameToGameDTO(game))
                .collect(Collectors.toList());
        gamesDTO.sort(Comparator.comparing(GameDTO::getStartRecording).reversed());
        return gamesDTO;
    }

    @Override
    public GameDTO createRecording(String email, CreateRecording createRecording) {
        User user = userRepository.findByEmail(email);
        if(createRecording.getPlayer1().equals(createRecording.getPlayer2())){
            throw new SamePlayers("Tas pats žaidėjas negali žaisti ir juodais, ir baltais");
        }
        if (user.getRole().getName() == RoleName.ROLE_STUDENT) {
            if (((Student) user).getBoard() == null) {
                throw new BoardIsNotAssigned("Interaktyvi lenta nėra priskirta.");
            }
            createRecording.setBoardCode(((Student) user).getBoard().getCode());
        } else {
            if (createRecording.getBoardCode() == null) {
                throw new BoardCannotBeNull("Interaktyvi lenta negali būti nepasirinkta.");
            }
        }
        if(gameRepository.getAll()
                .stream()
                .anyMatch(recording ->
                        recording.getStatus()==RecordingStatus.RECORDING
                                && recording.getBoard().getCode().equals(createRecording.getBoardCode()))
        ){
            throw new BoardIsBusy("Kita partija įrašoma ant šios lentos.");
        }
        Game game = gameMapper.convertCreateRecordingToGame(createRecording);
        gameRepository.createRecording(game);
        return gameMapper.convertGameToGameDTO(game);
    }

    @Override
    public GameDTO getByID(long id) {
        Game game = gameRepository.getGameByID(id);
        if (game.getStatus() == RecordingStatus.RECORDING) {
            this.getMovesForGame(game);
        }
        return gameMapper.convertGameToGameDTO(game);
    }

    @Override
    public GameDTO update(UpdateRecording updateRecording) {
        Game game = gameMapper.convertUpdateRecordingToGame(updateRecording);
        if (game.getStatus() == RecordingStatus.RECORDED) {
            game.setEndRecording(new Date());
            this.getMovesForGame(game);
        }
        gameRepository.update(game);
        return gameMapper.convertGameToGameDTO(game);
    }

    @Override
    public void deleteRecording(long id) {
        gameRepository.delete(id);
    }

    private void getMovesForGame(Game game) {
        Date startedDate = recordingService.getInitialPosition(game.getBoard().getCode(), game.getStartRecording());
        if (startedDate != null) {
            game.setStartRecording(startedDate);
            List<String> moves = recordingService.getMoves(game.getBoard().getCode(), startedDate);
            game.setMoves(moves);
        }
        else{
            game.setMoves(new ArrayList<>());
        }
    }
}
