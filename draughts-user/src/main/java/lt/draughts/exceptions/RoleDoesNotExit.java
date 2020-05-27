package lt.draughts.exceptions;

public class RoleDoesNotExit extends RuntimeException {
    public RoleDoesNotExit(String reason) {
        super(reason);
    }
}