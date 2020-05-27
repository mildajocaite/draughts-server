package lt.draughts.exceptions;

public class BoardDoesNotExist extends RuntimeException {
    public BoardDoesNotExist(String reason) {
        super(reason);
    }
}
