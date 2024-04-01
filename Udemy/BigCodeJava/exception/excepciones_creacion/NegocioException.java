public class NegocioException extends Exception {

    private ErrorCode errorCode;

    public NegocioException(ErrorCode errorCode, String detailMessage) {
        this(errorCode, detailMessage, null);
    }

    public NegocioException(ErrorCode errorCode, String detailMessage, Throwable cause) {
        super(generateMessage(errorCode, detailMessage), cause);
        this.errorCode = errorCode;
    }

    private static String generateMessage(ErrorCode errorCode, String detailMessage) {
        return errorCode + detailMessage;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    enum ErrorCode {
        NEGATIVO("El número recibido es negativo"),
        PAR("El número recibido es par");

        private String message;

        ErrorCode(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ERROR: " + message + ". Detalle: ";
        }
    }
}
