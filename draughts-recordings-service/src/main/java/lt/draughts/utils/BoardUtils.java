package lt.draughts.utils;

import lt.draughts.model.ThingSpeakFeed;
import org.springframework.stereotype.Component;

@Component
public class BoardUtils {

    public int[][] returnEmptyBoard() {
        return new int[][]{
                {6, 5, 6, 5, 6, 5, 6, 5},
                {5, 6, 5, 6, 5, 6, 5, 6},
                {6, 5, 6, 5, 6, 5, 6, 5},
                {5, 6, 5, 6, 5, 6, 5, 6},
                {6, 5, 6, 5, 6, 5, 6, 5},
                {5, 6, 5, 6, 5, 6, 5, 6},
                {6, 5, 6, 5, 6, 5, 6, 5},
                {5, 6, 5, 6, 5, 6, 5, 6}
        };
    }

    public int[][] returnInitialPosition() {
        return new int[][]{
                {6, 2, 6, 2, 6, 2, 6, 2},
                {2, 6, 2, 6, 2, 6, 2, 6},
                {6, 2, 6, 2, 6, 2, 6, 2},
                {5, 6, 5, 6, 5, 6, 5, 6},
                {6, 5, 6, 5, 6, 5, 6, 5},
                {1, 6, 1, 6, 1, 6, 1, 6},
                {6, 1, 6, 1, 6, 1, 6, 1},
                {1, 6, 1, 6, 1, 6, 1, 6}
        };
    }

    public int[][] returnPositionFromThingSpeakFeed(ThingSpeakFeed thingSpeakFeed) {
        int[][] position = returnEmptyBoard();
        int a1 = Integer.parseInt(thingSpeakFeed.getField1().charAt(0) + "");
        position[7][0] = a1;
        int c1 = Integer.parseInt(thingSpeakFeed.getField1().charAt(1) + "");
        position[7][2] = c1;
        int e1 = Integer.parseInt(thingSpeakFeed.getField1().charAt(2) + "");
        position[7][4] = e1;
        int g1 = Integer.parseInt(thingSpeakFeed.getField1().charAt(3) + "");
        position[7][6] = g1;
        int b2 = Integer.parseInt(thingSpeakFeed.getField2().charAt(0) + "");
        position[6][1] = b2;
        int d2 = Integer.parseInt(thingSpeakFeed.getField2().charAt(1) + "");
        position[6][3] = d2;
        int f2 = Integer.parseInt(thingSpeakFeed.getField2().charAt(2) + "");
        position[6][5] = f2;
        int h2 = Integer.parseInt(thingSpeakFeed.getField2().charAt(3) + "");
        position[6][7] = h2;
        int a3 = Integer.parseInt(thingSpeakFeed.getField3().charAt(0) + "");
        position[5][0] = a3;
        int c3 = Integer.parseInt(thingSpeakFeed.getField3().charAt(1) + "");
        position[5][2] = c3;
        int e3 = Integer.parseInt(thingSpeakFeed.getField3().charAt(2) + "");
        position[5][4] = e3;
        int g3 = Integer.parseInt(thingSpeakFeed.getField3().charAt(3) + "");
        position[5][6] = g3;
        int b4 = Integer.parseInt(thingSpeakFeed.getField4().charAt(0) + "");
        position[4][1] = b4;
        int d4 = Integer.parseInt(thingSpeakFeed.getField4().charAt(1) + "");
        position[4][3] = d4;
        int f4 = Integer.parseInt(thingSpeakFeed.getField4().charAt(2) + "");
        position[4][5] = f4;
        int h4 = Integer.parseInt(thingSpeakFeed.getField4().charAt(3) + "");
        position[4][7] = h4;
        int a5 = Integer.parseInt(thingSpeakFeed.getField5().charAt(0) + "");
        position[3][0] = a5;
        int c5 = Integer.parseInt(thingSpeakFeed.getField5().charAt(1) + "");
        position[3][2] = c5;
        int e5 = Integer.parseInt(thingSpeakFeed.getField5().charAt(2) + "");
        position[3][4] = e5;
        int g5 = Integer.parseInt(thingSpeakFeed.getField5().charAt(3) + "");
        position[3][6] = g5;
        int b6 = Integer.parseInt(thingSpeakFeed.getField6().charAt(0) + "");
        position[2][1] = b6;
        int d6 = Integer.parseInt(thingSpeakFeed.getField6().charAt(1) + "");
        position[2][3] = d6;
        int f6 = Integer.parseInt(thingSpeakFeed.getField6().charAt(2) + "");
        position[2][5] = f6;
        int h6 = Integer.parseInt(thingSpeakFeed.getField6().charAt(3) + "");
        position[2][7] = h6;
        int a7 = Integer.parseInt(thingSpeakFeed.getField7().charAt(0) + "");
        position[1][0] = a7;
        int c7 = Integer.parseInt(thingSpeakFeed.getField7().charAt(1) + "");
        position[1][2] = c7;
        int e7 = Integer.parseInt(thingSpeakFeed.getField7().charAt(2) + "");
        position[1][4] = e7;
        int g7 = Integer.parseInt(thingSpeakFeed.getField7().charAt(3) + "");
        position[1][6] = g7;
        int b8 = Integer.parseInt(thingSpeakFeed.getField8().charAt(0) + "");
        position[0][1] = b8;
        int d8 = Integer.parseInt(thingSpeakFeed.getField8().charAt(1) + "");
        position[0][3] = d8;
        int f8 = Integer.parseInt(thingSpeakFeed.getField8().charAt(2) + "");
        position[0][5] = f8;
        int h8 = Integer.parseInt(thingSpeakFeed.getField8().charAt(3) + "");
        position[0][7] = h8;
        return position;
    }

    public boolean checkIfPositionsEqual(int[][] firstPosition, int[][] secondPosition) {
        boolean samePosition = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (firstPosition[i][j] != secondPosition[i][j]) {
                    samePosition = false;
                }
            }
        }
        return samePosition;
    }
}
