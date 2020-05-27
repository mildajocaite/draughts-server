package lt.draughts.logic.simpleMoves;

import lt.draughts.model.*;
import lt.draughts.utils.MovesDecoding;
import lt.draughts.utils.PositionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlackMovesCalculation {

    @Autowired
    PositionUtils positionCalculation;

    @Autowired
    MovesDecoding moveDecoding;

    public List<DraughtMoveWithCoordinates> getAllMoves (int[][] position){
        List<DraughtsPosition> blackDraughts = positionCalculation.getBlacks(position);
        List<DraughtMoveWithCoordinates> allMovesList =  new ArrayList<>();
        for (DraughtsPosition blackDraught : blackDraughts
        ) {
            if(blackDraught.getType()==2){
                List<DraughtMoveWithCoordinates> draughtMovesList= this.getDraughtMoves(position, blackDraught.getRow(), blackDraught.getColumn());
                allMovesList.addAll(draughtMovesList);
            }
            else{
                List<DraughtMoveWithCoordinates> kingMovesList= this.getAllKingMoves(position, blackDraught.getRow(), blackDraught.getColumn());
                allMovesList.addAll(kingMovesList);
            }
        }
        return allMovesList;
    }

    public List<DraughtMoveWithCoordinates> getDraughtMoves(int[][] position, int row, int column) {
        List<DraughtMoveWithCoordinates> moves = new ArrayList<>();
        if (column >= 1 && row <= 6 && position[row + 1][column - 1] == 5) {
            moves.add(this.getDraughtMove(position, row, column, -1));
        }
        if (column <=6 && row <= 6 && position[row + 1][column + 1] == 5) {
            moves.add(this.getDraughtMove(position, row, column, 1));
        }
        return moves;
    }

    private DraughtMoveWithCoordinates getDraughtMove(int[][] position, int row, int column,  int columnDirection){
        DraughtMoveWithCoordinates draughtMove = new DraughtMoveWithCoordinates();
        int[][] copyPosition = positionCalculation.copyBoard(position);
        copyPosition[row][column] = 5;
        if (row == 7) {
            copyPosition[row + 1][column + columnDirection] = 4;
        } else {
            copyPosition[row + 1][column + columnDirection] = 2;
        }
        draughtMove.setNewPosition(copyPosition);
        draughtMove.setRow(row);
        draughtMove.setColumn(column);
        draughtMove.setNewRow(row + 1);
        draughtMove.setNewColumn(column + columnDirection);
        String move = moveDecoding.getSimpleMove(row, column, row + 1, column + columnDirection);
        draughtMove.setMove(move);
        return draughtMove;
    }

    public List<DraughtMoveWithCoordinates> getAllKingMoves (int[][] position,
                                               int row,
                                               int column){
        List<DraughtMoveWithCoordinates> moves = new ArrayList<>();
        moves.addAll(this.getKingMoves(position, row, column, 1, 1));
        moves.addAll(this.getKingMoves(position, row, column, 1, -1));
        moves.addAll(this.getKingMoves(position, row, column, -1, 1));
        moves.addAll(this.getKingMoves(position, row, column, -1, -1));
        return moves;
    }
    private List<DraughtMoveWithCoordinates> getKingMoves (int[][] position,
                                            int row,
                                            int column,
                                            int rowDirection,
                                            int columnDirection
    ){
        List<DraughtMoveWithCoordinates> moves = new ArrayList<>();
        int columnNumberToCheck = columnDirection > 0 ? 7 - column : column;
        for (int i = 1; i <= columnNumberToCheck; i++) {
            int rowChange = i * rowDirection;
            int columnChange = i * columnDirection;
            if (((rowDirection < 0 && row + rowChange >= 0) || (rowDirection > 0 && row + rowChange < 8))
                && ((columnDirection < 0 && column + columnChange>= 0) || (columnDirection > 0 && column + columnChange < 8))
                    && position[row + rowChange][column + columnChange] == 5
            )
            {
                DraughtMoveWithCoordinates draughtMove = new DraughtMoveWithCoordinates();
                int[][] copyPosition = positionCalculation.copyBoard(position);
                copyPosition[row][column] = 5;
                copyPosition[row + rowChange][column + columnChange] = 4;
                draughtMove.setNewPosition(copyPosition);
                draughtMove.setRow(row);
                draughtMove.setColumn(column);
                draughtMove.setNewRow(row + rowChange);
                draughtMove.setNewColumn(column + columnChange);
                String move = moveDecoding.getSimpleMove(row, column, row + rowChange, column + columnChange);
                draughtMove.setMove(move);
                moves.add(draughtMove);
            } else {
                return moves;
            }
        }
        return moves;
    }
}
