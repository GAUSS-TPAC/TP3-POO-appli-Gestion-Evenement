package exception;

public class ParticipantDejaInscrit extends RuntimeException {
    public ParticipantDejaInscrit(String message) {
        super(message);
    }
}
