package lt.draughts.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DraughtMove {
    private int [][] newPosition;
    private String move;
}
