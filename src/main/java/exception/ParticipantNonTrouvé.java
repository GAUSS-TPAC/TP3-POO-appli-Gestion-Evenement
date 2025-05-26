package exception;

public class ParticipantNonTrouvé extends RuntimeException {
    public ParticipantNonTrouvé(String message) {
        super(message);
    }
}
