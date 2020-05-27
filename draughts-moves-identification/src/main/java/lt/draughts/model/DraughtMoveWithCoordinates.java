package lt.draughts.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DraughtMoveWithCoordinates {
    private int row;
    private int column;
    private int newRow;
    private int newColumn;
    private int [][] newPosition;
    private String move;
}
