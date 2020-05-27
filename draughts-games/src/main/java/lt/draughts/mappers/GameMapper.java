package lt.draughts.mappers;

import lt.draughts.entities.Board;
import lt.draughts.entities.Game;
import lt.draughts.entities.RecordingStatus;
import lt.draughts.entities.User;
import lt.draughts.entities.request.CreateRecording;
import lt.draughts.entities.request.GameDTO;
import lt.draughts.entities.request.UpdateRecording;
import lt.draughts.mapper.BoardMapper;
import lt.draughts.mapper.UserMapper;
import lt.draughts.repositories.GameRepository;
import lt.draughts.repository.BoardRepository;
import lt.draughts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GameMapper {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BoardMapper boardMapper;

    @Autowired
    GameRepository gameRepository;

    public Game convertCreateRecordingToGame(CreateRecording createRecording) {
        Game game = new Game();
        User player1 = userRepository.findByEmail(createRecording.getPlayer1());
        User player2 = userRepository.findByEmail(createRecording.getPlayer2());
        Board board = boardRepository.getByCode(createRecording.getBoardCode());
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        game.setBoard(board);
        game.setStatus(RecordingStatus.RECORDING);
        game.setStartRecording(new Date());
        return game;
    }

    public GameDTO convertGameToGameDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setPlayer1(userMapper.convertUserToUserDTO(game.getPlayer1()));
        gameDTO.setPlayer2(userMapper.convertUserToUserDTO(game.getPlayer2()));
        gameDTO.setBoard(boardMapper.convertBoardToBoardDTO(game.getBoard()));
        gameDTO.setStartRecording(game.getStartRecording());
        gameDTO.setEndRecording(game.getEndRecording());
        gameDTO.setResult(game.getResult());
        gameDTO.setStatus(game.getStatus());
        gameDTO.setMoves(game.getMoves());
        return gameDTO;
    }

    public Game convertGameDTOToGame(GameDTO gameDTO) {
        Game game = new Game();
        game.setId(gameDTO.getId());
        game.setPlayer1(userMapper.convertUserDTOToUser(gameDTO.getPlayer1()));
        game.setPlayer2(userMapper.convertUserDTOToUser(gameDTO.getPlayer2()));
        game.setBoard(boardMapper.convertBoardDTOToBoard(gameDTO.getBoard()));
        game.setStartRecording(gameDTO.getStartRecording());
        game.setResult(gameDTO.getResult());
        game.setStatus(gameDTO.getStatus());
        game.setMoves(gameDTO.getMoves());
        return game;
    }

    public Game convertUpdateRecordingToGame(UpdateRecording updateRecording) {
        Game game = gameRepository.getGameByID(updateRecording.getId());
        game.setId(updateRecording.getId());
        User player1 = userRepository.findByEmail(updateRecording.getPlayer1());
        User player2 = userRepository.findByEmail(updateRecording.getPlayer2());
        Board board = boardRepository.getByCode(updateRecording.getBoard());
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        game.setBoard(board);
        game.setResult(updateRecording.getResult());
        game.setStatus(updateRecording.getStatus());
        return game;
    }
}
