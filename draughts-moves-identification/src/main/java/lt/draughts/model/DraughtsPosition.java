package lt.draughts.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DraughtsPosition {
    private int row;
    private int column;
    private int type;
    private int removedDraughtRow;
    private int removedDraughtColumn;
}
