package lt.draughts.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPossibleMoves {
    private Boolean isWhite;
    private int[][] position;
}



