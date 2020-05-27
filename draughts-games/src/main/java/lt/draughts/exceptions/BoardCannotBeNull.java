package lt.draughts.exceptions;

public class BoardCannotBeNull extends RuntimeException {
    public BoardCannotBeNull(String reason) {
        super(reason);
    }
}
