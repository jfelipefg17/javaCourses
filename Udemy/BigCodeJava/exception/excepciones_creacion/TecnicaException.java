public class TecnicaException extends RuntimeException {
    public TecnicaException(String message) {
        super(message);
    }

    public TecnicaException(String message, Throwable cause) {
        super(message, cause);
    }
}
