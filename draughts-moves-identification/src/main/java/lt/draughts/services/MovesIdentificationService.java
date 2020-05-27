package lt.draughts.services;

import lt.draughts.model.DraughtMove;
import lt.draughts.model.DraughtMoveWithCoordinates;

import java.util.List;

public interface MovesIdentificationService {
    List<DraughtMoveWithCoordinates> getPossibleMoves (int row, int column, int[][] position);
    List<String> getMoveWhichWasDone(int [][] previousPosition, int [][] currentPosition, boolean isWhiteMove);
    List<DraughtMoveWithCoordinates> getAllPossibleMoves(int[][] position, Boolean isWhite);
}
