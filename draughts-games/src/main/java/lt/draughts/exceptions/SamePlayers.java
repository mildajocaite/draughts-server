package lt.draughts.exceptions;

public class SamePlayers extends RuntimeException {
    public SamePlayers(String reason) {
        super(reason);
    }
}
