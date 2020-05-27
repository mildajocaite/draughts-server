package lt.draughts.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DraughtJump {
    private int [][] currentPosition;
    private int [][] positionToCheck;
    private int initialRow;
    private int initialColumn;
    private int row;
    private int column;
    private List<RemovedDraughtCoordinates> removedDraughtCoordinatesList = new ArrayList();
    private int type;
    private String move;
    private Boolean isFinished;
}



