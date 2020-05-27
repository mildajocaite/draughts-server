package lt.draughts.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DraughtMovesResultHeuristic {
    private int [][] positionToCheck;
    private List<String> moves = new ArrayList<>();
    private DraughtColor lastCheck;
    private int heuristicResult;
}

