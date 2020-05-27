package lt.draughts.services;

import lt.draughts.logic.AllMovesCalculation;
import lt.draughts.logic.MoveIdentifierGreedyBestFirstSearch;
import lt.draughts.model.DraughtMove;
import lt.draughts.model.DraughtMoveWithCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovesIdentificationServiceImplementation implements MovesIdentificationService {

    @Autowired
    AllMovesCalculation allMovesCalculation;

    @Autowired
    MoveIdentifierGreedyBestFirstSearch moveIdentifier;

    @Override
    public List<DraughtMoveWithCoordinates> getAllPossibleMoves(int[][] position, Boolean isWhite) {
        if (isWhite) {
            return allMovesCalculation.getAllWhiteMoves(position);
        } else {
            return allMovesCalculation.getAllBlackMoves(position);
        }
    }

    @Override
    public List<DraughtMoveWithCoordinates> getPossibleMoves(int row, int column, int[][] position) {
        int draughtType = position[row][column];
        if (draughtType == 1 || draughtType == 3) {
            return allMovesCalculation.getWhiteMovesForOneDraught(position, row, column);
        }
        if (draughtType == 2 || draughtType == 4) {
            return allMovesCalculation.getBlackMovesForOneDraught(position, row, column);
        }
        return null;
    }

    @Override
    public List<String> getMoveWhichWasDone(int[][] previousPosition, int[][] currentPosition, boolean isWhiteMove) {
        return moveIdentifier.identifyMove(previousPosition, currentPosition, isWhiteMove);
    }
}
