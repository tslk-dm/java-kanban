package tracker.exceptions;

public class ManagerSaveException extends RuntimeException {
    public ManagerSaveException() {
        super();
    }

    public ManagerSaveException(String message) {
        super(message);
    }

    public ManagerSaveException(Throwable cause) {
        super(cause);
    }

    public ManagerSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
