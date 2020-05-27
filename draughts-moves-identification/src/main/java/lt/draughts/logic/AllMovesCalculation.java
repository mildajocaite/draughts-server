package lt.draughts.logic;

import lt.draughts.logic.jumps.AllBlackJumpsCalculation;
import lt.draughts.logic.jumps.AllWhiteJumpsCalculation;
import lt.draughts.logic.simpleMoves.BlackMovesCalculation;
import lt.draughts.logic.simpleMoves.WhiteMovesCalculation;
import lt.draughts.model.DraughtMove;
import lt.draughts.model.DraughtMoveWithCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllMovesCalculation {

    @Autowired
    AllWhiteJumpsCalculation allWhiteJumpsCalculation;

    @Autowired
    WhiteMovesCalculation whiteMovesCalculation;

    @Autowired
    AllBlackJumpsCalculation allBlackJumpsCalculation;

    @Autowired
    BlackMovesCalculation blackMovesCalculation;

    public List<DraughtMoveWithCoordinates> getAllWhiteMoves(int[][] position) {
        List<DraughtMoveWithCoordinates> moves = allWhiteJumpsCalculation.getAllWhiteJumps(position);
        if (moves.size() == 0) {
            moves = whiteMovesCalculation.getAllMoves(position);
        }
        return moves;
    }

    public List<DraughtMoveWithCoordinates> getWhiteMovesForOneDraught(int[][] position, int row, int column) {
        List<DraughtMoveWithCoordinates> moves = allWhiteJumpsCalculation.getAllWhiteJumpsForSpecificDraught(position, row, column);
        if (moves.size() == 0) {
            moves = position[row][column] == 1
                    ? whiteMovesCalculation.getDraughtMoves(position, row, column)
                    : whiteMovesCalculation.getAllKingMoves(position, row, column);
        }
        return moves;
    }

    public List<DraughtMoveWithCoordinates> getAllBlackMoves(int[][] position) {
        List<DraughtMoveWithCoordinates> moves = allBlackJumpsCalculation.getAllBlackJumpsWithCoordinates(position);
        if (moves.size() == 0) {
            moves = blackMovesCalculation.getAllMoves(position);
        }
        return moves;
    }
    public List<DraughtMoveWithCoordinates> getBlackMovesForOneDraught(int[][] position, int row, int column) {
        List<DraughtMoveWithCoordinates> moves = allBlackJumpsCalculation.getAllBlackJumpsForSpecificDraught(position, row, column);
        if (moves.size() == 0) {
            moves = position[row][column] == 2
                    ? blackMovesCalculation.getDraughtMoves(position, row, column)
                    : blackMovesCalculation.getAllKingMoves(position, row, column);
        }
        return moves;
    }

}
