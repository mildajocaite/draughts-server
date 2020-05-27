package lt.draughts.services;

import lt.draughts.model.ThingSpeakFeed;
import lt.draughts.thingSpeakServices.ThingSpeakServiceImplementation;
import lt.draughts.utils.BoardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecordingServiceImplementation implements RecordingService {

    @Autowired
    ThingSpeakServiceImplementation thingSpeakServiceImplementation;

    @Autowired
    BoardUtils boardUtils;

    @Autowired
    MovesIdentificationService movesIdentificationService;

    @Override
    public List<String> getMoves(String boardCode, Date dateFrom) {
        List<ThingSpeakFeed> records = thingSpeakServiceImplementation.getRecordFromChannel(boardCode, dateFrom);
        List<int[][]> positions = records.stream().map(record -> boardUtils.returnPositionFromThingSpeakFeed(record)).collect(Collectors.toList());
        List<String> moves = new ArrayList<>();
        if (positions.size() == 0) {
            return null;
        }
        int[][] initialPositionToCheck = positions.get(0);
        boolean isWhite = true;
        for (int[][] position : positions) {
            if (!boardUtils.checkIfPositionsEqual(initialPositionToCheck, position)) {
                List<String> movesFromThingSpeak = movesIdentificationService.getMoveWhichWasDone(initialPositionToCheck, position, isWhite);
                if (movesFromThingSpeak != null && movesFromThingSpeak.size() != 0) {
                    isWhite = movesFromThingSpeak.size() % 2 == 1 ? !isWhite : isWhite;
                    initialPositionToCheck = position;
                    moves.addAll(movesFromThingSpeak);
                }
            }
        }
        return moves;
    }

    @Override
    public Date getStartPositionDate(String boardCode, Date dateFrom, int[][] position) {
        List<ThingSpeakFeed> records = thingSpeakServiceImplementation.getRecordFromChannel(boardCode, dateFrom);
        for (ThingSpeakFeed record : records) {
            int[][] positionToCheck = boardUtils.returnPositionFromThingSpeakFeed(record);
            if (boardUtils.checkIfPositionsEqual(positionToCheck, position)) {
                return record.getCreated_at();
            }
        }
        return null;
    }

    @Override
    public Date getInitialPosition(String boardCode, Date dateFrom) {
        List<ThingSpeakFeed> records = thingSpeakServiceImplementation.getRecordFromChannel(boardCode, dateFrom);
        if (records == null) {
            return null;
        }
        for (ThingSpeakFeed record : records) {
            int[][] positionToCheck = boardUtils.returnPositionFromThingSpeakFeed(record);
            if (boardUtils.checkIfPositionsEqual(positionToCheck, boardUtils.returnInitialPosition())) {
                return record.getCreated_at();
            }
        }
        return null;
    }
}
