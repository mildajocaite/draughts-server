package lt.draughts.utils;

import lt.draughts.model.DraughtsPosition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PositionUtils {
    public boolean checkIfPositionsEqual(int[][] firstPosition, int[][] secondPosition) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (firstPosition[i][j] != secondPosition[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] copyBoard(int[][] boardToCopy) {
        int[][] newPosition = new int[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(boardToCopy[i], 0, newPosition[i], 0, boardToCopy[i].length);
        }
        return newPosition;
    }

    public List<DraughtsPosition> getWhites(int[][] position) {
        List<DraughtsPosition> whiteDraughts = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (position[i][j] == 1 || position[i][j] == 3) {
                    DraughtsPosition draughtsPosition = new DraughtsPosition();
                    draughtsPosition.setType(position[i][j]);
                    draughtsPosition.setRow(i);
                    draughtsPosition.setColumn(j);
                    whiteDraughts.add(draughtsPosition);
                }
            }
        }
        return whiteDraughts;
    }

    public List<DraughtsPosition> getBlacks(int[][] position) {
        List<DraughtsPosition> whiteDraughts = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (position[i][j] == 2 || position[i][j] == 4) {
                    DraughtsPosition draughtsPosition = new DraughtsPosition();
                    draughtsPosition.setRow(i);
                    draughtsPosition.setColumn(j);
                    draughtsPosition.setType(position[i][j]);
                    whiteDraughts.add(draughtsPosition);
                }
            }
        }
        return whiteDraughts;
    }
}
