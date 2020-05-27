package lt.draughts.exceptions;

public class BoardIsNotAssigned extends RuntimeException {
    public BoardIsNotAssigned(String reason) {
        super(reason);
    }
}
