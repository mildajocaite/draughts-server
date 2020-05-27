package lt.draughts.exceptions;

public class BoardIsBusy extends RuntimeException {
    public BoardIsBusy(String reason) {
        super(reason);
    }
}
