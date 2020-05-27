package lt.draughts.logic.jumps;

import lt.draughts.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlackDraughtJumpsCalculation {

    public List<DraughtsPosition> getPossibleBlackDraughtJumps(int[][] position, int row, int column) {
        List<DraughtsPosition> possibleMoveList = new ArrayList<>();
        possibleMoveList = this.getSimpleBlackJump(possibleMoveList, position, row, column, -2, -2);
        possibleMoveList = this.getSimpleBlackJump(possibleMoveList, position, row, column, 2, -2);
        possibleMoveList = this.getSimpleBlackJump(possibleMoveList, position, row, column, -2, 2);
        possibleMoveList = this.getSimpleBlackJump(possibleMoveList, position, row, column, 2, 2);
        return possibleMoveList;
    }

    public List<DraughtsPosition> getPossibleBlackKingJumps(int[][] position, int row, int column) {
        List<DraughtsPosition> possibleMoveList = new ArrayList<>();
        possibleMoveList.addAll(this.getKingBlackJumps(position, row, column, -1, -1));
        possibleMoveList.addAll(this.getKingBlackJumps(position, row, column, -1, 1));
        possibleMoveList.addAll(this.getKingBlackJumps(position, row, column, 1, -1));
        possibleMoveList.addAll(this.getKingBlackJumps(position, row, column, 1, 1));
        return possibleMoveList;
    }

    private List<DraughtsPosition> getSimpleBlackJump(List<DraughtsPosition> possibleMoveList, int[][] position, int row, int column, int changeRow, int changeColumn){
        boolean notExceedsBoundaries = (
                (changeRow < 0 && row + changeRow >= 0)
                        || (changeRow > 0 && row + changeRow < 8)) && (
                (changeColumn < 0 && column + changeColumn >= 0)
                        || (changeColumn > 0 && column + changeColumn < 8));
        if (notExceedsBoundaries &&
                (position[row + (changeRow/2)][column + (changeColumn/2)] == 1
                    || position[row + (changeRow/2)][column + (changeColumn/2)] == 3
                )
                && position[row + changeRow][column + changeColumn] == 5) {
            DraughtsPosition draughtsPosition = new DraughtsPosition();
            draughtsPosition.setRow(row + changeRow);
            draughtsPosition.setColumn(column + changeColumn);
            draughtsPosition.setRemovedDraughtRow(row + (changeRow/2));
            draughtsPosition.setRemovedDraughtColumn(column + (changeColumn/2));
            possibleMoveList.add(draughtsPosition);
        }
        return possibleMoveList;
    }

    private List<DraughtsPosition> getKingBlackJumps(int[][] position, int row, int column, int rowDirection, int columnDirection){
        List<DraughtsPosition> possibleMoveList = new ArrayList<>();
        boolean isEmpty = true;
        int rowNumberNotEmpty=0;
        int columnNumberNotEmpty=0;
        int columnNumberToCheck = columnDirection > 0 ? 7 - column : column;
        for (int i = 1; i <= columnNumberToCheck; i++) {
            boolean notExceedsBoundaries = (
                    (rowDirection < 0 && row + (i * rowDirection) >= 0)
                            || (rowDirection > 0 && row + (i * rowDirection) < 8)) && (
                    (columnDirection < 0 && column + (i * columnDirection) >= 0)
                            || (columnDirection > 0 && column + (i * columnDirection) < 8));
            if (!isEmpty && notExceedsBoundaries && position[row + (i * rowDirection)][column + (i * columnDirection)] == 5) {
                DraughtsPosition draughtsPosition = new DraughtsPosition();
                draughtsPosition.setRow(row + (i * rowDirection));
                draughtsPosition.setColumn(column + i * columnDirection);
                draughtsPosition.setRemovedDraughtRow(rowNumberNotEmpty);
                draughtsPosition.setRemovedDraughtColumn(columnNumberNotEmpty);
                possibleMoveList.add(draughtsPosition);
            }
            if(!isEmpty && notExceedsBoundaries && position[row + (i * rowDirection)][column + (i * columnDirection)] != 5){
                return possibleMoveList;
            }
            if (notExceedsBoundaries
                    && position[row + (i * rowDirection)][column + (i * columnDirection)] != 5 && isEmpty) {
                if (position[row + (i * rowDirection)][column + (i * columnDirection)] == 1
                        || position[row + (i * rowDirection)][column + (i * columnDirection)] == 3) {
                    rowNumberNotEmpty = row + (i * rowDirection);
                    columnNumberNotEmpty = column + (i * columnDirection);
                    isEmpty = false;
                } else {
                    return possibleMoveList;
                }
            }

        }
        return possibleMoveList;
    }
}
