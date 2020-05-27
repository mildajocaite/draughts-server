package lt.draughts.exceptions;

public class BoardIsAssigned extends RuntimeException {
    public BoardIsAssigned(String reason) {
        super(reason);
    }
}
