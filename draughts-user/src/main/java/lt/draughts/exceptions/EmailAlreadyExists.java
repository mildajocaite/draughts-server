package lt.draughts.exceptions;

public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String reason) {
        super(reason);
    }
}
